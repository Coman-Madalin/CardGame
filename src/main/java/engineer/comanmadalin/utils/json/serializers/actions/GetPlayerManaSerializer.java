package engineer.comanmadalin.utils.json.serializers.actions;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.specific.GetPlayerMana;

import java.io.IOException;

public class GetPlayerManaSerializer extends StdSerializer<GetPlayerMana> {
    public GetPlayerManaSerializer(Class<GetPlayerMana> t) {
        super(t);
    }

    @Override
    public void serialize(GetPlayerMana value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeNumberField("playerIdx", value.getPlayerID());

        jsonGenerator.writeNumberField("output", Integer.parseInt(value.getResult()));

        jsonGenerator.writeEndObject();
    }
}
