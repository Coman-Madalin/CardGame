package engineer.comanmadalin.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import engineer.comanmadalin.player.PlayerData;
import engineer.comanmadalin.utils.json.JsonUtils;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Input.
 */
@Getter
public final class Input {
    @Getter
    private static final int MAX_PLAYERS = 2;
    @Getter
    private static Input instance;
    private final PlayerData[] playersData = new PlayerData[MAX_PLAYERS];
    private final List<Game> games = new ArrayList<>();
    private int gamesPlayed = 0;

    /**
     * Instantiates a new Input.
     */
    public Input() {
        Input.instance = this;

        for (int i = 0; i < MAX_PLAYERS; i++) {
            playersData[i] = new PlayerData();
        }
    }

    /**
     * Play all games.
     */
    public void playAllGames() {
        for (int i = 0; i < games.size(); i++) {
            games.get(i).runGame(i);
        }
    }

    /**
     * Increase games played.
     */
    public void increaseGamesPlayed() {
        gamesPlayed++;
    }

    /**
     * Games to json.
     *
     * @param filePath1 the file path 1
     * @throws IOException the io exception
     */
    public void gamesToJson(final String filePath1) throws IOException {
        final ObjectMapper objectMapper = JsonUtils.getOBJECT_MAPPER();
        final ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath1), this.getGames());
    }

}
