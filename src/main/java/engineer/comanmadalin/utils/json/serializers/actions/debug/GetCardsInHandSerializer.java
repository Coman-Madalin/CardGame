package engineer.comanmadalin.utils.json.serializers.actions.debug;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.debug.GetCardsInHand;

import java.io.IOException;

/**
 * The type Get cards in hand serializer.
 */
public final class GetCardsInHandSerializer extends StdSerializer<GetCardsInHand> {
    /**
     * Instantiates a new Get cards in hand serializer.
     *
     * @param t the t
     */
    public GetCardsInHandSerializer(final Class<GetCardsInHand> t) {
        super(t);
    }

    @Override
    public void serialize(final GetCardsInHand value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeNumberField("playerIdx", value.getPlayerID());

        jsonGenerator.writeFieldName("output");
        jsonGenerator.writeRawValue(value.getResult());

        jsonGenerator.writeEndObject();
    }
}
