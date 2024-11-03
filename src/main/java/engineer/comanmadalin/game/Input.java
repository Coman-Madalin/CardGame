package engineer.comanmadalin.game;

import engineer.comanmadalin.player.PlayerData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public final class Input {
    @Getter
    private static final int MAX_PLAYERS = 2;
    @Getter
    private static Input INSTANCE;

    private PlayerData[] playersData;
    private ArrayList<Game> games;

    public Input() {
        Input.INSTANCE = this;

        playersData = new PlayerData[MAX_PLAYERS];
        for (int i = 0; i < MAX_PLAYERS; i++) {
            playersData[i] = new PlayerData();
        }
        games = new ArrayList<>();
    }
}
