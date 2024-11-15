package engineer.comanmadalin.utils.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.cards.minion.BaseMinionCard;

import java.io.IOException;

public final class BaseCardSerializer extends StdSerializer<BaseCard> {
    public BaseCardSerializer(final Class<BaseCard> t) {
        super(t);
    }

    @Override
    public void serialize(final BaseCard value, final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("mana", value.getManaCost());
        jsonGenerator.writeNumberField("attackDamage",
                ((BaseMinionCard) value).getAttackDamage());
        jsonGenerator.writeNumberField("health", value.getHealth());
        jsonGenerator.writeStringField("description", value.getDescription());

        jsonGenerator.writeArrayFieldStart("colors");
        for (final String color : value.getColors()) {
            jsonGenerator.writeString(color);
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeStringField("name", value.getName());
        jsonGenerator.writeEndObject();
    }
}
