package engineer.comanmadalin.utils.json.serializers.actions.specific;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.actions.BaseAction;

import java.io.IOException;

public class EmptySerializer<T extends BaseAction> extends StdSerializer<T> {
    public EmptySerializer(final Class<T> t) {
        super(t);
    }

    // This serializer is called for any action that don't need to be present in the output json
    @Override
    public void serialize(final T value, final JsonGenerator gen,
                          final SerializerProvider provider)  throws IOException {

    }
}
