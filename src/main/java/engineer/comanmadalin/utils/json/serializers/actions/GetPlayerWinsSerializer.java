package engineer.comanmadalin.utils.json.serializers.actions;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.specific.GetPlayerWins;

import java.io.IOException;

public class GetPlayerWinsSerializer extends StdSerializer<GetPlayerWins> {
    public GetPlayerWinsSerializer(Class<GetPlayerWins> t) {
        super(t);
    }

    @Override
    public void serialize(GetPlayerWins value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeNumberField("output", Integer.parseInt(value.getResult()));
        jsonGenerator.writeEndObject();
    }
}
