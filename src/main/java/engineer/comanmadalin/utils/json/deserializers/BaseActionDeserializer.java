package engineer.comanmadalin.utils.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.actions.debug.*;
import engineer.comanmadalin.actions.specific.*;
import engineer.comanmadalin.actions.statistics.GetPlayerWins;
import engineer.comanmadalin.actions.statistics.GetTotalGamesPlayed;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;

/**
 * The type Base action deserializer.
 */
public final class BaseActionDeserializer extends StdDeserializer<BaseAction> {
    private static final Map<String, String[]> NAME_TO_ARGUMENTS = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("getCardAtPosition", new String[]{"x", "y"}),

            new AbstractMap.SimpleEntry<>("getCardsInHand", new String[]{"playerIdx"}),
            new AbstractMap.SimpleEntry<>("getPlayerDeck", new String[]{"playerIdx"}),
            new AbstractMap.SimpleEntry<>("getPlayerHero", new String[]{"playerIdx"}),
            new AbstractMap.SimpleEntry<>("getPlayerMana", new String[]{"playerIdx"}),

            new AbstractMap.SimpleEntry<>("cardUsesAbility",
                    new String[]{"cardAttacker", "cardAttacked"}),
            new AbstractMap.SimpleEntry<>("cardUsesAttack",
                    new String[]{"cardAttacker", "cardAttacked"}),
            new AbstractMap.SimpleEntry<>("useAttackHero", new String[]{"cardAttacker"}),

            new AbstractMap.SimpleEntry<>("useHeroAbility", new String[]{"affectedRow"}),

            new AbstractMap.SimpleEntry<>("placeCard", new String[]{"handIdx"}),

            new AbstractMap.SimpleEntry<>("getFrozenCardsOnTable", new String[0]),
            new AbstractMap.SimpleEntry<>("getCardsOnTable", new String[0]),
            new AbstractMap.SimpleEntry<>("getPlayerTurn", new String[0]),
            new AbstractMap.SimpleEntry<>("endPlayerTurn", new String[0]),

            new AbstractMap.SimpleEntry<>("getPlayerOneWins", new String[0]),
            new AbstractMap.SimpleEntry<>("getPlayerTwoWins", new String[0]),
            new AbstractMap.SimpleEntry<>("getTotalGamesPlayed", new String[0])
    );

    private static final Map<String, Class<?>> NAME_TO_ACTION_CLASS = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("getCardAtPosition", GetCardAtPosition.class),

            new AbstractMap.SimpleEntry<>("getCardsInHand", GetCardsInHand.class),
            new AbstractMap.SimpleEntry<>("getPlayerDeck", GetPlayerDeck.class),
            new AbstractMap.SimpleEntry<>("getPlayerHero", GetPlayerHero.class),
            new AbstractMap.SimpleEntry<>("getPlayerMana", GetPlayerMana.class),

            new AbstractMap.SimpleEntry<>("cardUsesAbility", CardUsesAbility.class),
            new AbstractMap.SimpleEntry<>("cardUsesAttack", CardUsesAttack.class),
            new AbstractMap.SimpleEntry<>("useAttackHero", UseAttackHero.class),

            new AbstractMap.SimpleEntry<>("useHeroAbility", UseHeroAbility.class),

            new AbstractMap.SimpleEntry<>("placeCard", PlaceCard.class),

            new AbstractMap.SimpleEntry<>("getFrozenCardsOnTable", GetFrozenCardsOnTable.class),
            new AbstractMap.SimpleEntry<>("getCardsOnTable", GetCardsOnTable.class),
            new AbstractMap.SimpleEntry<>("getPlayerTurn", GetPlayerTurn.class),
            new AbstractMap.SimpleEntry<>("endPlayerTurn", EndPlayerTurn.class),

            new AbstractMap.SimpleEntry<>("getPlayerOneWins", GetPlayerWins.class),
            new AbstractMap.SimpleEntry<>("getPlayerTwoWins", GetPlayerWins.class),
            new AbstractMap.SimpleEntry<>("getTotalGamesPlayed", GetTotalGamesPlayed.class)
    );

    /**
     * Instantiates a new Base action deserializer.
     *
     * @param vc the vc
     */
    public BaseActionDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public BaseAction deserialize(final JsonParser jsonParser, final DeserializationContext ctxt)
            throws IOException {
        final JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        final String command = root.get("command").asText();

        final String[] argumentsNameArray = NAME_TO_ARGUMENTS.get(command);
        final Object[] argumentsArray = new Object[argumentsNameArray.length + 1];
        argumentsArray[0] = command;
        int index = 1;

        for (final String s : argumentsNameArray) {
            argumentsArray[index] = root.get(s);
            index++;
        }

        final Class<?> clazz = NAME_TO_ACTION_CLASS.get(command);
        final Object o;
        try {
            final Class<?>[] argumentTypes = new Class<?>[argumentsNameArray.length + 1];
            Arrays.fill(argumentTypes, JsonNode.class);
            argumentTypes[0] = String.class;

            o = clazz.getConstructor(argumentTypes).newInstance(argumentsArray);

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        return (BaseAction) o;
    }
}
