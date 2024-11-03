package engineer.comanmadalin.utils.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;

import java.io.IOException;

public class GameSerializer extends StdSerializer<Game> {
    public GameSerializer(Class<Game> t) {
        super(t);
    }

    @Override
    public void serialize(Game value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartArray();
        for (BaseAction action : value.getActions()) {
            jsonGenerator.writeObject(action);
        }
        jsonGenerator.writeEndArray();
    }
}
