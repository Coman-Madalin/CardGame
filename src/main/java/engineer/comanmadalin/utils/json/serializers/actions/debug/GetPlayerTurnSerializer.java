package engineer.comanmadalin.utils.json.serializers.actions.debug;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.debug.GetPlayerTurn;

import java.io.IOException;

/**
 * The type Get player turn serializer.
 */
public final class GetPlayerTurnSerializer extends StdSerializer<GetPlayerTurn> {
    /**
     * Instantiates a new Get player turn serializer.
     *
     * @param t the t
     */
    public GetPlayerTurnSerializer(final Class<GetPlayerTurn> t) {
        super(t);
    }

    @Override
    public void serialize(final GetPlayerTurn value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());

        jsonGenerator.writeNumberField("output", Short.parseShort(value.getResult()));

        jsonGenerator.writeEndObject();
    }
}
