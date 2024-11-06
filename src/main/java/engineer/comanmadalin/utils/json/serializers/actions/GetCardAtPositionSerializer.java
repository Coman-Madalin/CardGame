package engineer.comanmadalin.utils.json.serializers.actions;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.specific.GetCardAtPosition;

import java.io.IOException;

public class GetCardAtPositionSerializer extends StdSerializer<GetCardAtPosition> {
    public GetCardAtPositionSerializer(Class<GetCardAtPosition> t) {
        super(t);
    }

    @Override
    public void serialize(GetCardAtPosition value, JsonGenerator jsonGenerator, SerializerProvider provider)
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
