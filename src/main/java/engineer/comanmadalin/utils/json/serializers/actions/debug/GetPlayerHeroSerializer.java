package engineer.comanmadalin.utils.json.serializers.actions.debug;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.debug.GetPlayerHero;

import java.io.IOException;

/**
 * The type Get player hero serializer.
 */
public final class GetPlayerHeroSerializer extends StdSerializer<GetPlayerHero> {
    /**
     * Instantiates a new Get player hero serializer.
     *
     * @param t the t
     */
    public GetPlayerHeroSerializer(final Class<GetPlayerHero> t) {
        super(t);
    }

    @Override
    public void serialize(final GetPlayerHero value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeNumberField("playerIdx", value.getPlayerID());

        jsonGenerator.writeFieldName("output");
        jsonGenerator.writeRawValue(value.getResult());

        jsonGenerator.writeEndObject();
    }
}
