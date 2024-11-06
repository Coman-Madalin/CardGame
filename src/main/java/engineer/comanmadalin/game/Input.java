package engineer.comanmadalin.game;

import engineer.comanmadalin.player.PlayerData;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public final class Input {
    @Getter
    private static final int MAX_PLAYERS = 2;
    @Getter
    private static Input INSTANCE;

    private PlayerData[] playersData = new PlayerData[MAX_PLAYERS];
    private ArrayList<Game> games = new ArrayList<>();

    public Input() {
        Input.INSTANCE = this;

        for (int i = 0; i < MAX_PLAYERS; i++) {
            playersData[i] = new PlayerData();
        }
    }
}
