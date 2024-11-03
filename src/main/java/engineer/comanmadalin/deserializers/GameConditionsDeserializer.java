package engineer.comanmadalin.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import engineer.comanmadalin.game.GameConditions;

import java.io.IOException;

public class GameConditionsDeserializer extends StdDeserializer<GameConditions> {
    public GameConditionsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GameConditions deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        GameConditions toReturnGameConditions = new GameConditions();

        toReturnGameConditions.setShuffleSeed(root.get("shuffleSeed").asInt());
        toReturnGameConditions.setStartingPlayer(root.get("startingPlayer").asInt());
        return toReturnGameConditions;
    }
}
