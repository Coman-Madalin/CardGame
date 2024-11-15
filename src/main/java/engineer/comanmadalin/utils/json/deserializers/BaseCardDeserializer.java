package engineer.comanmadalin.utils.json.deserializers;

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
import java.util.ArrayList;
import java.util.HashMap;

public class BaseCardDeserializer extends StdDeserializer<BaseCard> {
    private static final HashMap<String, Class<?>> nameToMinionClass = new HashMap<>() {{
        put("Sentinel", Sentinel.class);
        put("Warden", Warden.class);
        put("Berserker", Berserker.class);
        put("Goliath", Goliath.class);

        put("The Ripper", TheRipper.class);
        put("Miraj", Miraj.class);
        put("The Cursed One", TheCursedOne.class);
        put("Disciple", Disciple.class);
    }};
    private static final HashMap<String, Class<?>> nameToHeroClass = new HashMap<>() {{
        put("Lord Royce", LordRoyce.class);
        put("Empress Thorina", EmpressThorina.class);
        put("King Mudface", KingMudface.class);
        put("General Kocioraw", GeneralKocioraw.class);
    }};

    public BaseCardDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public BaseCard deserialize(final JsonParser jsonParser, final DeserializationContext ctxt)
            throws IOException {
        final JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        final String name = root.get("name").asText();
        int health = 30;
        final int manaCost = root.get("mana").asInt();
        final String description = root.get("description").asText();
        final ArrayList<String> colors = new ArrayList<>();

        final ArrayNode colorsNode = (ArrayNode) root.get("colors");
        for (final JsonNode colorNode : colorsNode) {
            colors.add(colorNode.asText());
        }

        Class<?> clazz = nameToHeroClass.get(name);
        if (clazz == null) {
            clazz = nameToMinionClass.get(name);
            health = root.get("health").asInt();
        }

        final Object o;
        try {
            // This constructor is the one present in BaseCard.java
            o = clazz.getConstructor(int.class, int.class, String.class, ArrayList.class,
                            String.class)
                    .newInstance(manaCost, health, description, colors, name);

            // If the card is a minion type, we need to set the attack damage
            if (nameToMinionClass.containsKey(name)) {
                final int attackDamage = root.get("attackDamage").asInt();
                clazz.getMethod("setAttackDamage", int.class).invoke(o, attackDamage);
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        return (BaseCard) o;
    }
}
