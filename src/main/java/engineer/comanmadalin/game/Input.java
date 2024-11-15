package engineer.comanmadalin.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import engineer.comanmadalin.player.PlayerData;
import engineer.comanmadalin.utils.json.JsonUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Getter
@Setter
public final class Input {
    @Getter
    private static final int MAX_PLAYERS = 2;
    @Getter
    private static Input INSTANCE;

    private int gamesPlayed = 0;

    private PlayerData[] playersData = new PlayerData[MAX_PLAYERS];
    private ArrayList<Game> games = new ArrayList<>();

    public Input() {
        Input.INSTANCE = this;

        for (int i = 0; i < MAX_PLAYERS; i++) {
            playersData[i] = new PlayerData();
        }
    }

    public void playAllGames() {
        for (int i = 0; i < games.size(); i++) {
            games.get(i).runGame(i);
        }
    }

    public void increaseGamesPlayed(){
        gamesPlayed++;
    }

    public void gamesToJson(final String filePath1) throws IOException {
        ObjectMapper objectMapper = JsonUtils.getObjectMapper();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath1), this.getGames());
    }

}
