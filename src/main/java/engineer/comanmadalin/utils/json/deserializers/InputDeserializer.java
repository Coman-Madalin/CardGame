package engineer.comanmadalin.utils.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.deck.Deck;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.Input;

import java.io.IOException;

/**
 * The type Input deserializer.
 */
public final class InputDeserializer extends StdDeserializer<Input> {
    /**
     * Instantiates a new Input deserializer.
     *
     * @param vc the vc
     */
    public InputDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Input deserialize(final JsonParser jsonParser, final DeserializationContext ctxt)
            throws IOException {
        final JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        final Input toReturnInput = new Input();

        final ArrayNode oneDecks = (ArrayNode) root.get("playerOneDecks").get("decks");
        for (final JsonNode deck : oneDecks) {
            final Deck deck1 = jsonParser.getCodec().treeToValue(deck, Deck.class);
            toReturnInput.getPlayersData()[0].getDecks().add(deck1);
        }

        final ArrayNode twoDecks = (ArrayNode) root.get("playerTwoDecks").get("decks");
        for (final JsonNode deck : twoDecks) {
            final Deck deck1 = jsonParser.getCodec().treeToValue(deck, Deck.class);
            toReturnInput.getPlayersData()[1].getDecks().add(deck1);
        }

        final ArrayNode games = (ArrayNode) root.get("games");
        for (final JsonNode game : games) {
            final int playerOneDeckIndex = game.get("startGame").get("playerOneDeckIdx").asInt();
            final int playerTwoDeckIndex = game.get("startGame").get("playerTwoDeckIdx").asInt();

            toReturnInput.getPlayersData()[0].getDeckIndexForGame().add(playerOneDeckIndex);
            toReturnInput.getPlayersData()[1].getDeckIndexForGame().add(playerTwoDeckIndex);

            final BaseCard playerOneHero = game.get("startGame").get("playerOneHero")
                    .traverse(jsonParser.getCodec()).readValueAs(BaseCard.class);
            final BaseCard playerTwoHero = game.get("startGame").get("playerTwoHero")
                    .traverse(jsonParser.getCodec()).readValueAs(BaseCard.class);

            toReturnInput.getPlayersData()[0].getHero().add((BaseHero) playerOneHero);
            toReturnInput.getPlayersData()[1].getHero().add((BaseHero) playerTwoHero);

            final JsonParser jsonParserGame = game.traverse(jsonParser.getCodec());
            toReturnInput.getGames().add(jsonParserGame.readValueAs(Game.class));
        }
        return toReturnInput;
    }
}
