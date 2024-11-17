package engineer.comanmadalin.json.serializers.actions.specific;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.specific.UseAttackHero;

import java.io.IOException;

/**
 * The type Game ended serializer.
 */
public final class GameEndedSerializer extends StdSerializer<UseAttackHero> {
    /**
     * Instantiates a new Game ended serializer.
     *
     * @param t the t
     */
    public GameEndedSerializer(final Class<UseAttackHero> t) {
        super(t);
    }

    @Override
    public void serialize(final UseAttackHero value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider)
            throws IOException {
        if (value.getResult() != null) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("gameEnded", value.getResult());
            jsonGenerator.writeEndObject();
            return;
        }

        if (value.getError() == null) {
            return;
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeObjectField("cardAttacker", value.getCoordinatesAttacker());
        jsonGenerator.writeStringField("error", value.getError());
        jsonGenerator.writeEndObject();
    }
}
