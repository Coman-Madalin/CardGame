package engineer.comanmadalin.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import engineer.comanmadalin.deck.Deck;
import engineer.comanmadalin.deck.PlayerDecks;

import java.io.IOException;

public class PlayerDecksDeserializer extends StdDeserializer<PlayerDecks> {
    public PlayerDecksDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public PlayerDecks deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
//        jsonParser.nextToken();
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        PlayerDecks toReturnPlayerDecks = new PlayerDecks();
//        toReturnPlayerDecks.setDecks(root.get("nrDecks").asInt(), root.get("nrCardsInDeck").asInt());

        ArrayNode decks = (ArrayNode) root.get("decks");
        for (JsonNode deck : decks) {
            JsonParser jsonParserGame = deck.traverse(jsonParser.getCodec());

            toReturnPlayerDecks.getDecks().add(jsonParserGame.readValueAs(Deck.class));
        }

        return toReturnPlayerDecks;
    }
}
