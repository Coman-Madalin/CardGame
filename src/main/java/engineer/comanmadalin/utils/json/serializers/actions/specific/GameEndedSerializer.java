package engineer.comanmadalin.utils.json.serializers.actions.specific;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.specific.UseAttackHero;

import java.io.IOException;

public class GameEndedSerializer extends StdSerializer<UseAttackHero> {
    public GameEndedSerializer(Class<UseAttackHero> t) {
        super(t);
    }

    @Override
    public void serialize(UseAttackHero value, JsonGenerator jsonGenerator, SerializerProvider provider)
            throws IOException {
        if (value.getResult() != null) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("gameEnded", value.getResult());
            jsonGenerator.writeEndObject();
            return;
        }

        if(value.getError() == null)
            return;

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeObjectField("cardAttacker", value.getCoordinatesAttacker());
        jsonGenerator.writeStringField("error", value.getError());
        jsonGenerator.writeEndObject();
    }
}
