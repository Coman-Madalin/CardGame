package engineer.comanmadalin.utils.json.serializers.actions.debug;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.debug.GetPlayerHero;

import java.io.IOException;

public class GetPlayerHeroSerializer extends StdSerializer<GetPlayerHero> {
    public GetPlayerHeroSerializer(Class<GetPlayerHero> t) {
        super(t);
    }

    @Override
    public void serialize(GetPlayerHero value, JsonGenerator jsonGenerator, SerializerProvider provider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeNumberField("playerIdx", value.getPlayerID());

        jsonGenerator.writeFieldName("output");
        jsonGenerator.writeRawValue(value.getResult());

        jsonGenerator.writeEndObject();
    }
}
