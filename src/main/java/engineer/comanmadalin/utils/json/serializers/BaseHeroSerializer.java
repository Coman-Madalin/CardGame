package engineer.comanmadalin.utils.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.cards.hero.BaseHero;

import java.io.IOException;

/**
 * The type Base hero serializer.
 */
public final class BaseHeroSerializer extends StdSerializer<BaseHero> {
    /**
     * Instantiates a new Base hero serializer.
     *
     * @param t the t
     */
    public BaseHeroSerializer(final Class<BaseHero> t) {
        super(t);
    }

    @Override
    public void serialize(final BaseHero value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("mana", value.getManaCost());
        jsonGenerator.writeStringField("description", value.getDescription());

        jsonGenerator.writeArrayFieldStart("colors");
        for (final String color : value.getColors()) {
            jsonGenerator.writeString(color);
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeStringField("name", value.getName());
        jsonGenerator.writeNumberField("health", value.getHealth());
        jsonGenerator.writeEndObject();
    }
}
