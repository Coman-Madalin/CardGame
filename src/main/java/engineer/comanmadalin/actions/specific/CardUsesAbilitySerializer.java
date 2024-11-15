package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CardUsesAbilitySerializer extends StdSerializer<CardUsesAbility> {
    public CardUsesAbilitySerializer(final Class<CardUsesAbility> t) {
        super(t);
    }

    @Override
    public void serialize(final CardUsesAbility value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {
        if (value.getError() == null)
            return;

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeObjectField("cardAttacker", value.getCoordinatesAttacker());
        jsonGenerator.writeObjectField("cardAttacked", value.getCoordinatesAttacked());
        jsonGenerator.writeStringField("error", value.getError());
        jsonGenerator.writeEndObject();
    }
}
