package engineer.comanmadalin.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.cards.hero.specific.EmpressThorina;
import engineer.comanmadalin.cards.hero.specific.GeneralKocioraw;
import engineer.comanmadalin.cards.hero.specific.KingMudface;
import engineer.comanmadalin.cards.hero.specific.LordRoyce;
import engineer.comanmadalin.cards.minion.specials.specific.Disciple;
import engineer.comanmadalin.cards.minion.specials.specific.Miraj;
import engineer.comanmadalin.cards.minion.specials.specific.TheCursedOne;
import engineer.comanmadalin.cards.minion.specials.specific.TheRipper;
import engineer.comanmadalin.cards.minion.specific.Berserker;
import engineer.comanmadalin.cards.minion.specific.Goliath;
import engineer.comanmadalin.cards.minion.specific.Sentinel;
import engineer.comanmadalin.cards.minion.specific.Warden;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Base card deserializer.
 */
public final class BaseCardDeserializer extends StdDeserializer<BaseCard> {
    private static final Map<String, Class<?>> NAME_TO_MINION_CLASS = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Sentinel", Sentinel.class),
            new AbstractMap.SimpleEntry<>("Warden", Warden.class),
            new AbstractMap.SimpleEntry<>("Berserker", Berserker.class),
            new AbstractMap.SimpleEntry<>("Goliath", Goliath.class),

            new AbstractMap.SimpleEntry<>("The Ripper", TheRipper.class),
            new AbstractMap.SimpleEntry<>("Miraj", Miraj.class),
            new AbstractMap.SimpleEntry<>("The Cursed One", TheCursedOne.class),
            new AbstractMap.SimpleEntry<>("Disciple", Disciple.class)
    );
    private static final Map<String, Class<?>> NAME_TO_HERO_CLASS = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Lord Royce", LordRoyce.class),
            new AbstractMap.SimpleEntry<>("Empress Thorina", EmpressThorina.class),
            new AbstractMap.SimpleEntry<>("King Mudface", KingMudface.class),
            new AbstractMap.SimpleEntry<>("General Kocioraw", GeneralKocioraw.class)
    );

    private static final int DEFAULT_HEALTH_VALUE = 30;

    /**
     * Instantiates a new Base card deserializer.
     *
     * @param vc the vc
     */
    public BaseCardDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public BaseCard deserialize(final JsonParser jsonParser, final DeserializationContext ctxt)
            throws IOException {
        final JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        final String name = root.get("name").asText();
        int health = DEFAULT_HEALTH_VALUE;
        final int manaCost = root.get("mana").asInt();
        final String description = root.get("description").asText();
        final ArrayList<String> colors = new ArrayList<>();

        final ArrayNode colorsNode = (ArrayNode) root.get("colors");
        for (final JsonNode colorNode : colorsNode) {
            colors.add(colorNode.asText());
        }

        Class<?> clazz = NAME_TO_HERO_CLASS.get(name);
        if (clazz == null) {
            clazz = NAME_TO_MINION_CLASS.get(name);
            health = root.get("health").asInt();
        }

        final Object o;
        try {
            // This constructor is the one present in BaseCard.java
            o = clazz.getConstructor(int.class, int.class, String.class, List.class,
                            String.class)
                    .newInstance(manaCost, health, description, colors, name);

            // If the card is a minion type, we need to set the attack damage
            if (NAME_TO_MINION_CLASS.containsKey(name)) {
                final int attackDamage = root.get("attackDamage").asInt();
                clazz.getMethod("setAttackDamage", int.class).invoke(o, attackDamage);
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        return (BaseCard) o;
    }
}
