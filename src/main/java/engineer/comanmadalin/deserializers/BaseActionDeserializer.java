package engineer.comanmadalin.deserializers;

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
    public BaseActionDeserializer(Class<?> vc) {
        super(vc);
    }

    private static final HashMap<String, String[]> nameToArguments = new HashMap<>() {{
        put("getPlayerDeck", new String[]{"playerIdx"});
        put("getPlayerHero", new String[]{"playerIdx"});
        put("getPlayerTurn", new String[0]);
    }};

    private static final HashMap<String, Class<?>> nameToActionClass = new HashMap<>() {{
        put("getPlayerDeck", GetPlayerDeck.class);
        put("getPlayerHero", GetPlayerHero.class);
        put("getPlayerTurn", GetPlayerTurn.class);
    }};

    @Override
    public BaseAction deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        String command = root.get("command").asText();
        String[] argumentsNameArray = nameToArguments.get(command);
        String[] argumentsArray = new String[argumentsNameArray.length + 1];
        argumentsArray[0] = command;
        int index = 1;
        for (String s : argumentsNameArray) {
            argumentsArray[index] = root.get(s).asText();
            index++;
        }

        Class<?> clazz = nameToActionClass.get(command);
        Object o;
        try {
            Class<?>[] argumentTypes = new Class<?>[argumentsNameArray.length + 1];
            Arrays.fill(argumentTypes, String.class);

            o = clazz.getConstructor(argumentTypes).newInstance((Object[]) argumentsArray);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (BaseAction) o;
    }
}
