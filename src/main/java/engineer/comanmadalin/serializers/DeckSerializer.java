package engineer.comanmadalin.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.deck.Deck;

import java.io.IOException;

public class DeckSerializer extends StdSerializer<Deck> {
    public DeckSerializer(Class<Deck> t) {
        super(t);
    }

    @Override
    public void serialize(Deck value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartArray();
        for (BaseCard card : value.getCards()) {
            jsonGenerator.writeObject(card);
        }

        jsonGenerator.writeEndArray();
    }
}
