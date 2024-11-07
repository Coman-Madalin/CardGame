package engineer.comanmadalin.utils.json.serializers.actions;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.specific.GetFrozenCardsOnTable;

import java.io.IOException;

public class GetFrozenCardsOnTableSerializer extends StdSerializer<GetFrozenCardsOnTable> {
    public GetFrozenCardsOnTableSerializer(Class<GetFrozenCardsOnTable> t) {
        super(t);
    }

    @Override
    public void serialize(GetFrozenCardsOnTable value, JsonGenerator jsonGenerator,
                          SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField("command", value.getCommand());

        jsonGenerator.writeFieldName("output");
        jsonGenerator.writeRawValue(value.getResult());

        jsonGenerator.writeEndObject();
    }
}