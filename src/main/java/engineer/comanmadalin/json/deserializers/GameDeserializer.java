package engineer.comanmadalin.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;

import java.io.IOException;

/**
 * The type Game deserializer.
 */
public final class GameDeserializer extends StdDeserializer<Game> {
    /**
     * Instantiates a new Game deserializer.
     *
     * @param vc the vc
     */
    public GameDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Game deserialize(final JsonParser jsonParser, final DeserializationContext ctxt)
            throws IOException {
        final JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        final Game toReturnGame = new Game();
        final JsonNode startGame = root.get("startGame");
        toReturnGame.setShuffleSeed(startGame.get("shuffleSeed").asInt());
        toReturnGame.setStartingPlayer(startGame.get("startingPlayer").asInt());

        final ArrayNode actions = (ArrayNode) root.get("actions");
        for (final JsonNode action : actions) {
            final JsonParser jsonParserAction = action.traverse(jsonParser.getCodec());

            toReturnGame.getActions().add(jsonParserAction.readValueAs(BaseAction.class));
        }

        return toReturnGame;
    }
}
