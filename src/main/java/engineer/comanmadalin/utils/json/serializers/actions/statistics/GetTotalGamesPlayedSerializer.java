package engineer.comanmadalin.utils.json.serializers.actions.statistics;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.statistics.GetTotalGamesPlayed;

import java.io.IOException;

public class GetTotalGamesPlayedSerializer extends StdSerializer<GetTotalGamesPlayed> {
    public GetTotalGamesPlayedSerializer(final Class<GetTotalGamesPlayed> t) {
        super(t);
    }

    @Override
    public void serialize(final GetTotalGamesPlayed value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("command", value.getCommand());
        jsonGenerator.writeNumberField("output", Integer.parseInt(value.getResult()));
        jsonGenerator.writeEndObject();
    }
}
