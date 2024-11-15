package engineer.comanmadalin.utils.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import engineer.comanmadalin.game.GameConditions;

import java.io.IOException;

public class GameConditionsDeserializer extends StdDeserializer<GameConditions> {
    public GameConditionsDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public GameConditions deserialize(final JsonParser jsonParser,
                                      final DeserializationContext ctxt) throws IOException {
        final JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        final GameConditions toReturnGameConditions = new GameConditions();

        toReturnGameConditions.setShuffleSeed(root.get("shuffleSeed").asInt());
        toReturnGameConditions.setStartingPlayer(root.get("startingPlayer").asInt());
        return toReturnGameConditions;
    }
}
