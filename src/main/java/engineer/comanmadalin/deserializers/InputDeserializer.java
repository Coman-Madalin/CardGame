package engineer.comanmadalin.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.deck.PlayerDecks;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.Input;

import java.io.IOException;

public class InputDeserializer extends StdDeserializer<Input> {
    public InputDeserializer(Class<?> vc) {
        super(vc);
    }

    protected InputDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected InputDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public Input deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        Input toReturnInput = new Input();

        PlayerDecks playerOneDecks = root.get("playerOneDecks").traverse(jsonParser.getCodec()).readValueAs(PlayerDecks.class);
        toReturnInput.getPlayers()[0].setDecks(playerOneDecks);
        PlayerDecks playerTwoDecks = root.get("playerTwoDecks").traverse(jsonParser.getCodec()).readValueAs(PlayerDecks.class);
        toReturnInput.getPlayers()[1].setDecks(playerTwoDecks);

        ArrayNode games = (ArrayNode) root.get("games");
        for (JsonNode game : games) {
            int playerOneDeckIndex = game.get("startGame").get("playerOneDeckIdx").asInt();
            int playerTwoDeckIndex = game.get("startGame").get("playerTwoDeckIdx").asInt();

            toReturnInput.getPlayers()[0].getDeckIndexForGame().add(playerOneDeckIndex);
            toReturnInput.getPlayers()[1].getDeckIndexForGame().add(playerTwoDeckIndex);

            BaseCard playerOneHero = game.get("startGame").get("playerOneHero")
                            .traverse(jsonParser.getCodec()).readValueAs(BaseCard.class);
            BaseCard playerTwoHero = game.get("startGame").get("playerTwoHero")
                            .traverse(jsonParser.getCodec()).readValueAs(BaseCard.class);

            toReturnInput.getPlayers()[0].getHero().add((BaseHero) playerOneHero);
            toReturnInput.getPlayers()[1].getHero().add((BaseHero) playerTwoHero);

            JsonParser jsonParserGame = game.traverse(jsonParser.getCodec());
            jsonParserGame.nextToken();
            toReturnInput.getGames().add(jsonParserGame.readValueAs(Game.class));
        }
        return toReturnInput;
    }
}
