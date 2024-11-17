package engineer.comanmadalin.json.serializers.actions.debug;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.debug.GetCardAtPosition;

import java.io.IOException;

/**
 * The type Get card at position serializer.
 */
public final class GetCardAtPositionSerializer extends StdSerializer<GetCardAtPosition> {
    /**
     * Instantiates a new Get card at position serializer.
     *
     * @param t the t
     */
    public GetCardAtPositionSerializer(final Class<GetCardAtPosition> t) {
        super(t);
    }

    @Override
    public void serialize(final GetCardAtPosition value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeNumberField("x", value.getCardCoordinates().getX());
        jsonGenerator.writeNumberField("y", value.getCardCoordinates().getY());

        if (value.getError() != null) {
            jsonGenerator.writeStringField("output", value.getError());
            jsonGenerator.writeEndObject();
            return;
        }

        jsonGenerator.writeFieldName("output");
        jsonGenerator.writeRawValue(value.getResult());

        jsonGenerator.writeEndObject();
    }
}
