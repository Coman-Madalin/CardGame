import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.comanmadalin.game.Input;
import engineer.comanmadalin.utils.json.JsonUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class DeserializerTest {

    @Test
    public void testDeserialize() throws IOException {
        ObjectMapper objectMapper = JsonUtils.getObjectMapper();
        Input inputData = objectMapper.readValue(new File("input/" + "test01_game_start.json"),
                Input.class);

        System.out.println(inputData);
    }
}
