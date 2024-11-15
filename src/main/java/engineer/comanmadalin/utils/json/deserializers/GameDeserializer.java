package engineer.comanmadalin.utils.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.GameConditions;

import java.io.IOException;

public final class GameDeserializer extends StdDeserializer<Game> {
    public GameDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Game deserialize(final JsonParser jsonParser, final DeserializationContext ctxt)
            throws IOException {
        final JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        final Game toReturnGame = new Game();
        toReturnGame.setGameConditions(root.get("startGame")
                .traverse(jsonParser.getCodec())
                .readValueAs(GameConditions.class));

        final ArrayNode actions = (ArrayNode) root.get("actions");
        for (final JsonNode action : actions) {
            final JsonParser jsonParserAction = action.traverse(jsonParser.getCodec());

            toReturnGame.getActions().add(jsonParserAction.readValueAs(BaseAction.class));
        }

        return toReturnGame;
    }
}
