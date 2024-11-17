package engineer.comanmadalin.json.serializers.actions.debug;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.debug.GetPlayerMana;

import java.io.IOException;

/**
 * The type Get player mana serializer.
 */
public final class GetPlayerManaSerializer extends StdSerializer<GetPlayerMana> {
    /**
     * Instantiates a new Get player mana serializer.
     *
     * @param t the t
     */
    public GetPlayerManaSerializer(final Class<GetPlayerMana> t) {
        super(t);
    }

    @Override
    public void serialize(final GetPlayerMana value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeNumberField("playerIdx", value.getPlayerID());

        jsonGenerator.writeNumberField("output", Integer.parseInt(value.getResult()));

        jsonGenerator.writeEndObject();
    }
}
