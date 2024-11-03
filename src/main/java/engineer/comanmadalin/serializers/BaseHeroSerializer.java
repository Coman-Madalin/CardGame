package engineer.comanmadalin.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.cards.hero.BaseHero;

import java.io.IOException;

public class BaseHeroSerializer extends StdSerializer<BaseHero> {
    public BaseHeroSerializer(Class<BaseHero> t) {
        super(t);
    }

    @Override
    public void serialize(BaseHero value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("mana", value.getMana());
        jsonGenerator.writeStringField("description", value.getDescription());

        jsonGenerator.writeArrayFieldStart("colors");
        for (String color : value.getColors()) {
            jsonGenerator.writeString(color);
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeStringField("name", value.getName());
        jsonGenerator.writeNumberField("health", value.getHealth());
        jsonGenerator.writeEndObject();
    }
}
