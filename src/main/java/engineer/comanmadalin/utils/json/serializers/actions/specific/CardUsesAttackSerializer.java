package engineer.comanmadalin.utils.json.serializers.actions.specific;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.specific.CardUsesAttack;

import java.io.IOException;

/**
 * The type Card uses attack serializer.
 */
public final class CardUsesAttackSerializer extends StdSerializer<CardUsesAttack> {
    /**
     * Instantiates a new Card uses attack serializer.
     *
     * @param t the t
     */
    public CardUsesAttackSerializer(final Class<CardUsesAttack> t) {
        super(t);
    }

    @Override
    public void serialize(final CardUsesAttack value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {
        if (value.getError() == null) {
            return;
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeObjectField("cardAttacker", value.getCoordinatesAttacker());
        jsonGenerator.writeObjectField("cardAttacked", value.getCoordinatesAttacked());
        jsonGenerator.writeStringField("error", value.getError());
        jsonGenerator.writeEndObject();
    }
}
