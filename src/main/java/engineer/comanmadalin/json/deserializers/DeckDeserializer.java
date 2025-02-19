package engineer.comanmadalin.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.cards.Deck;

import java.io.IOException;

/**
 * The type Deck deserializer.
 */
public final class DeckDeserializer extends StdDeserializer<Deck> {
    /**
     * Instantiates a new Deck deserializer.
     *
     * @param vc the vc
     */
    public DeckDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Deck deserialize(final JsonParser jsonParser, final DeserializationContext ctxt)
            throws IOException {
        final JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        final Deck toReturnDeck = new Deck();

        final ArrayNode decks = (ArrayNode) root;
        for (final JsonNode deck : decks) {
            final JsonParser jsonParserGame = deck.traverse(jsonParser.getCodec());
            toReturnDeck.getCards().add(jsonParserGame.readValueAs(BaseCard.class));
        }

        return toReturnDeck;
    }
}
