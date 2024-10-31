package engineer.comanmadalin.game;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GameConditions {
    private int shuffleSeed;
    private int startingPlayer;

    public GameConditions() {
        shuffleSeed = 0;
        startingPlayer = 0;
    }
}
