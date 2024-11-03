package engineer.comanmadalin.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
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

    @Override
    public Input deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        Input toReturnInput = new Input();

        PlayerDecks playerOneDecks = root.get("playerOneDecks").traverse(jsonParser.getCodec()).readValueAs(PlayerDecks.class);
        toReturnInput.getPlayersData()[0].setDecks(playerOneDecks);
        PlayerDecks playerTwoDecks = root.get("playerTwoDecks").traverse(jsonParser.getCodec()).readValueAs(PlayerDecks.class);
        toReturnInput.getPlayersData()[1].setDecks(playerTwoDecks);

        ArrayNode games = (ArrayNode) root.get("games");
        for (JsonNode game : games) {
            int playerOneDeckIndex = game.get("startGame").get("playerOneDeckIdx").asInt();
            int playerTwoDeckIndex = game.get("startGame").get("playerTwoDeckIdx").asInt();

            toReturnInput.getPlayersData()[0].getDeckIndexForGame().add(playerOneDeckIndex);
            toReturnInput.getPlayersData()[1].getDeckIndexForGame().add(playerTwoDeckIndex);

            BaseCard playerOneHero = game.get("startGame").get("playerOneHero")
                    .traverse(jsonParser.getCodec()).readValueAs(BaseCard.class);
            BaseCard playerTwoHero = game.get("startGame").get("playerTwoHero")
                    .traverse(jsonParser.getCodec()).readValueAs(BaseCard.class);

            toReturnInput.getPlayersData()[0].getHero().add((BaseHero) playerOneHero);
            toReturnInput.getPlayersData()[1].getHero().add((BaseHero) playerTwoHero);

            JsonParser jsonParserGame = game.traverse(jsonParser.getCodec());
            toReturnInput.getGames().add(jsonParserGame.readValueAs(Game.class));
        }
        return toReturnInput;
    }
}
