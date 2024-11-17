package engineer.comanmadalin.json.serializers.actions.debug;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.debug.GetPlayerDeck;

import java.io.IOException;

/**
 * The type Get player deck serializer.
 */
public final class GetPlayerDeckSerializer extends StdSerializer<GetPlayerDeck> {
    /**
     * Instantiates a new Get player deck serializer.
     *
     * @param t the t
     */
    public GetPlayerDeckSerializer(final Class<GetPlayerDeck> t) {
        super(t);
    }

    @Override
    public void serialize(final GetPlayerDeck value, final JsonGenerator jsonGenerator,
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
