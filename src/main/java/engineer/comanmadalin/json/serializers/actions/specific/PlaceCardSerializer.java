package engineer.comanmadalin.json.serializers.actions.specific;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.specific.PlaceCard;

import java.io.IOException;

/**
 * The type Place card serializer.
 */
public final class PlaceCardSerializer extends StdSerializer<PlaceCard> {
    /**
     * Instantiates a new Place card serializer.
     *
     * @param t the t
     */
    public PlaceCardSerializer(final Class<PlaceCard> t) {
        super(t);
    }

    @Override
    public void serialize(final PlaceCard value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {
        if (value.getError() == null) {
            return;
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeNumberField("handIdx", value.getHandID());

        jsonGenerator.writeStringField("error", value.getError());

        jsonGenerator.writeEndObject();
    }
}
