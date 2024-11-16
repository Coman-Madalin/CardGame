package engineer.comanmadalin.utils.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import engineer.comanmadalin.deck.Deck;
import engineer.comanmadalin.deck.PlayerDecks;

import java.io.IOException;

/**
 * The type Player decks deserializer.
 */
public final class PlayerDecksDeserializer extends StdDeserializer<PlayerDecks> {
    /**
     * Instantiates a new Player decks deserializer.
     *
     * @param vc the vc
     */
    public PlayerDecksDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public PlayerDecks deserialize(final JsonParser jsonParser, final DeserializationContext ctxt)
            throws IOException {
        final JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        final PlayerDecks toReturnPlayerDecks = new PlayerDecks();

        final ArrayNode decks = (ArrayNode) root.get("decks");
        for (final JsonNode deck : decks) {
            final JsonParser jsonParserGame = deck.traverse(jsonParser.getCodec());
            toReturnPlayerDecks.getDecks().add(jsonParserGame.readValueAs(Deck.class));
        }

        return toReturnPlayerDecks;
    }
}
