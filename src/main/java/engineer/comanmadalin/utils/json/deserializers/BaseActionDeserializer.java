package engineer.comanmadalin.utils.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.actions.specific.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class BaseActionDeserializer extends StdDeserializer<BaseAction> {
    private static final HashMap<String, String[]> nameToArguments = new HashMap<>() {{
        put("getCardAtPosition", new String[]{"x", "y"});

        put("getCardsInHand", new String[]{"playerIdx"});
        put("getPlayerDeck", new String[]{"playerIdx"});
        put("getPlayerHero", new String[]{"playerIdx"});
        put("getPlayerMana", new String[]{"playerIdx"});

        put("cardUsesAbility", new String[]{"cardAttacker", "cardAttacked"});
        put("cardUsesAttack", new String[]{"cardAttacker", "cardAttacked"});
        put("useAttackHero", new String[]{"cardAttacker"});

        put("useHeroAbility", new String[]{"affectedRow"});

        put("placeCard", new String[]{"handIdx"});

        put("getFrozenCardsOnTable", new String[0]);
        put("getCardsOnTable", new String[0]);
        put("getPlayerTurn", new String[0]);
        put("endPlayerTurn", new String[0]);

        put("getPlayerOneWins", new String[0]);
        put("getPlayerTwoWins", new String[0]);
        put("getTotalGamesPlayed", new String[0]);
    }};

    private static final HashMap<String, Class<?>> nameToActionClass = new HashMap<>() {{
        put("getCardAtPosition", GetCardAtPosition.class);

        put("getCardsInHand", GetCardsInHand.class);
        put("getPlayerDeck", GetPlayerDeck.class);
        put("getPlayerHero", GetPlayerHero.class);
        put("getPlayerMana", GetPlayerMana.class);

        put("cardUsesAbility", CardUsesAbility.class);
        put("cardUsesAttack", CardUsesAttack.class);
        put("useAttackHero", UseAttackHero.class);

        put("useHeroAbility", UseHeroAbility.class);

        put("placeCard", PlaceCard.class);

        put("getFrozenCardsOnTable", GetFrozenCardsOnTable.class);
        put("getCardsOnTable", GetCardsOnTable.class);
        put("getPlayerTurn", GetPlayerTurn.class);
        put("endPlayerTurn", EndPlayerTurn.class);

        put("getPlayerOneWins", GetPlayerWins.class);
        put("getPlayerTwoWins", GetPlayerWins.class);
        put("getTotalGamesPlayed", GetTotalGamesPlayed.class);
    }};

    public BaseActionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public BaseAction deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        String command = root.get("command").asText();

        if (command.equals("getPlayerOneWins")) {
            System.out.println("TRSAED");
        }
        String[] argumentsNameArray = nameToArguments.get(command);
        Object[] argumentsArray = new Object[argumentsNameArray.length + 1];
        argumentsArray[0] = command;
        int index = 1;

        for (String s : argumentsNameArray) {
            argumentsArray[index] = root.get(s);
            index++;
        }

        Class<?> clazz = nameToActionClass.get(command);
        Object o;
        try {
            Class<?>[] argumentTypes = new Class<?>[argumentsNameArray.length + 1];
            Arrays.fill(argumentTypes, JsonNode.class);
            argumentTypes[0] = String.class;

            o = clazz.getConstructor(argumentTypes).newInstance(argumentsArray);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (BaseAction) o;
    }
}
