package engineer.comanmadalin.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import engineer.comanmadalin.deck.Deck;
import engineer.comanmadalin.deck.PlayerDecks;
import engineer.comanmadalin.game.Game;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerDecksDeserializer extends StdDeserializer<PlayerDecks> {
    public PlayerDecksDeserializer(Class<?> vc) {
        super(vc);
    }

    protected PlayerDecksDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected PlayerDecksDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public PlayerDecks deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
//        jsonParser.nextToken();
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        PlayerDecks toReturnPlayerDecks = new PlayerDecks();
//        toReturnPlayerDecks.setDecks(root.get("nrDecks").asInt(), root.get("nrCardsInDeck").asInt());

        ArrayNode decks = (ArrayNode) root.get("decks");
        for (JsonNode deck : decks) {
            JsonParser jsonParserGame = deck.traverse(jsonParser.getCodec());
            jsonParserGame.nextToken();

            toReturnPlayerDecks.getDecks().add(jsonParserGame.readValueAs(Deck.class));
        }

        return toReturnPlayerDecks;
    }
}
