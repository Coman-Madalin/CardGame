package engineer.comanmadalin.json.serializers.actions.specific;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.specific.UseHeroAbility;

import java.io.IOException;

/**
 * The type Use hero ability serializer.
 */
public final class UseHeroAbilitySerializer extends StdSerializer<UseHeroAbility> {
    /**
     * Instantiates a new Use hero ability serializer.
     *
     * @param t the t
     */
    public UseHeroAbilitySerializer(final Class<UseHeroAbility> t) {
        super(t);
    }

    @Override
    public void serialize(final UseHeroAbility value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {
        if (value.getError() == null) {
            return;
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeNumberField("affectedRow", value.getAffectedRow());
        jsonGenerator.writeStringField("error", value.getError());

        jsonGenerator.writeEndObject();
    }
}
