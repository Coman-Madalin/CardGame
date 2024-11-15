package engineer.comanmadalin.utils.json.serializers.actions.debug;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.debug.GetPlayerTurn;

import java.io.IOException;

public class GetPlayerTurnSerializer extends StdSerializer<GetPlayerTurn> {
    public GetPlayerTurnSerializer(Class<GetPlayerTurn> t) {
        super(t);
    }

    @Override
    public void serialize(GetPlayerTurn value, JsonGenerator jsonGenerator, SerializerProvider provider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());

        jsonGenerator.writeNumberField("output", Short.parseShort(value.getResult()));

        jsonGenerator.writeEndObject();
    }
}
