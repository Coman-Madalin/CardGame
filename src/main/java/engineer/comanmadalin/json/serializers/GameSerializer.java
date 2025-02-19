package engineer.comanmadalin.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;

import java.io.IOException;

/**
 * The type Game serializer.
 */
public final class GameSerializer extends StdSerializer<Game> {
    /**
     * Instantiates a new Game serializer.
     *
     * @param t the t
     */
    public GameSerializer(final Class<Game> t) {
        super(t);
    }

    @Override
    public void serialize(final Game value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {
        for (final BaseAction action : value.getActions()) {
            jsonGenerator.writeObject(action);
        }
    }
}
