package engineer.comanmadalin.utils.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.deck.Deck;

import java.io.IOException;

public final class DeckSerializer extends StdSerializer<Deck> {
    public DeckSerializer(final Class<Deck> t) {
        super(t);
    }

    @Override
    public void serialize(final Deck value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartArray();
        for (final BaseCard card : value.getCards()) {
            jsonGenerator.writeObject(card);
        }

        jsonGenerator.writeEndArray();
    }
}
