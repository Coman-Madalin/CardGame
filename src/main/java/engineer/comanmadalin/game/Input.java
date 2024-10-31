package engineer.comanmadalin.game;

import engineer.comanmadalin.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public final class Input {
    private Player[] players;
    private ArrayList<Game> games;

    public Input() {
        players = new Player[2];
        // Overengineered for only 2 players, but might be useful if we add more players
        for (int i = 0; i < 2; i++) {
            players[i] = new Player();
        }
        games = new ArrayList<>();
    }
}
