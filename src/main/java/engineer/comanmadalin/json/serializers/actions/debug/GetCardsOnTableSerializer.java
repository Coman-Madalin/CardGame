package engineer.comanmadalin.json.serializers.actions.debug;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.debug.GetCardsOnTable;

import java.io.IOException;

/**
 * The type Get cards on table serializer.
 */
public final class GetCardsOnTableSerializer extends StdSerializer<GetCardsOnTable> {
    /**
     * Instantiates a new Get cards on table serializer.
     *
     * @param t the t
     */
    public GetCardsOnTableSerializer(final Class<GetCardsOnTable> t) {
        super(t);
    }

    @Override
    public void serialize(final GetCardsOnTable value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());

        jsonGenerator.writeFieldName("output");
        jsonGenerator.writeRawValue(value.getResult());

        jsonGenerator.writeEndObject();
    }
}
