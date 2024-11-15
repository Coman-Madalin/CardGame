package engineer.comanmadalin.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The type Coordinates.
 */
@Getter
@NoArgsConstructor
public final class Coordinates {
    private int x;
    private int y;

    /**
     * Instantiates a new Coordinates.
     *
     * @param x the x
     * @param y the y
     */
    public Coordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
}
