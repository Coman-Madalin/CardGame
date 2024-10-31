import com.fasterxml.jackson.databind.module.SimpleModule;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.deck.Deck;
import engineer.comanmadalin.deck.PlayerDecks;
import engineer.comanmadalin.deserializers.*;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.GameConditions;
import engineer.comanmadalin.game.Input;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.comanmadalin.utils.RegisterDeserializers;
import org.junit.jupiter.api.Test;

public class DeserializerTest {

    @Test
    public void testDeserialize() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(RegisterDeserializers.getCustomDeserializers());
        Input inputData = objectMapper.readValue(new File("input/" + "test01_game_start.json"),
                Input.class);

        System.out.println(inputData);
}
}
