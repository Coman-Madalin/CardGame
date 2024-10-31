package engineer.comanmadalin.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.deck.Deck;
import engineer.comanmadalin.deck.PlayerDecks;

import java.io.IOException;

public class DeckDeserializer extends StdDeserializer<Deck> {


    public DeckDeserializer(Class<?> vc) {
        super(vc);
    }

    protected DeckDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected DeckDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public Deck deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        Deck toReturnDeck = new Deck();
        ArrayNode decks = (ArrayNode) root;
        for (JsonNode deck : decks) {
            JsonParser jsonParserGame = deck.traverse(jsonParser.getCodec());
            jsonParserGame.nextToken();
            toReturnDeck.getCards().add(jsonParserGame.readValueAs(BaseCard.class));
        }

        return toReturnDeck;
    }
}
