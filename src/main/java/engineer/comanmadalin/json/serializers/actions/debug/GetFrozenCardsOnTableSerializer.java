package engineer.comanmadalin.json.serializers.actions.debug;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.debug.GetFrozenCardsOnTable;

import java.io.IOException;

/**
 * The type Get frozen cards on table serializer.
 */
public final class GetFrozenCardsOnTableSerializer extends StdSerializer<GetFrozenCardsOnTable> {
    /**
     * Instantiates a new Get frozen cards on table serializer.
     *
     * @param t the t
     */
    public GetFrozenCardsOnTableSerializer(final Class<GetFrozenCardsOnTable> t) {
        super(t);
    }

    @Override
    public void serialize(final GetFrozenCardsOnTable value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField("command", value.getCommand());

        jsonGenerator.writeFieldName("output");
        jsonGenerator.writeRawValue(value.getResult());

        jsonGenerator.writeEndObject();
    }
}
