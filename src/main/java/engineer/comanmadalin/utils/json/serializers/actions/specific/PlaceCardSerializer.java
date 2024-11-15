package engineer.comanmadalin.utils.json.serializers.actions.specific;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.specific.PlaceCard;

import java.io.IOException;

public class PlaceCardSerializer extends StdSerializer<PlaceCard> {
    public PlaceCardSerializer(Class<PlaceCard> t) {
        super(t);
    }

    @Override
    public void serialize(PlaceCard value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        if (value.getError() == null) {
            return;
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeNumberField("handIdx", value.getHandID());

        jsonGenerator.writeStringField("error", value.getError());

        jsonGenerator.writeEndObject();
    }
}
