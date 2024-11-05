package engineer.comanmadalin.utils.json.serializers.actions;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.specific.GetCardsInHand;

import java.io.IOException;

public class GetCardsInHandSerializer extends StdSerializer<GetCardsInHand> {
    public GetCardsInHandSerializer(Class<GetCardsInHand> t) {
        super(t);
    }

    @Override
    public void serialize(GetCardsInHand value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeNumberField("playerIdx", value.getPlayerID());

        jsonGenerator.writeFieldName("output");
        jsonGenerator.writeRawValue(value.getResult());

        jsonGenerator.writeEndObject();
    }
}
