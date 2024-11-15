package engineer.comanmadalin.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Game conditions.
 */
@Getter
@Setter
@NoArgsConstructor
public final class GameConditions {
    private int shuffleSeed = 0;
    private int startingPlayer = 0;
}
