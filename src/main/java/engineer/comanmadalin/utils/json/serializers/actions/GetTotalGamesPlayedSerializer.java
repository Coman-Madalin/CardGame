package engineer.comanmadalin.utils.json.serializers.actions;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.specific.GetTotalGamesPlayed;

import java.io.IOException;

public class GetTotalGamesPlayedSerializer extends StdSerializer<GetTotalGamesPlayed> {
    public GetTotalGamesPlayedSerializer(Class<GetTotalGamesPlayed> t) {
        super(t);
    }

    @Override
    public void serialize(GetTotalGamesPlayed value, JsonGenerator jsonGenerator, SerializerProvider provider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeNumberField("output", Integer.parseInt(value.getResult()));
        jsonGenerator.writeEndObject();
    }
}
