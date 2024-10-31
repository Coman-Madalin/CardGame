package engineer.comanmadalin.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.GameConditions;

import java.io.IOException;

public class GameDeserializer extends StdDeserializer<Game> {
    public GameDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Game deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        Game toReturnGame = new Game();
        toReturnGame.setGameConditions(root.get("startGame").traverse(jsonParser.getCodec()).readValueAs(GameConditions.class));

        ArrayNode actions = (ArrayNode) root.get("actions");
        for (JsonNode action : actions) {
            JsonParser jsonParserAction = action.traverse(jsonParser.getCodec());
            jsonParserAction.nextToken();
            toReturnGame.getActions().add(jsonParserAction.readValueAs(BaseAction.class));
        }

        return toReturnGame;
    }
}
