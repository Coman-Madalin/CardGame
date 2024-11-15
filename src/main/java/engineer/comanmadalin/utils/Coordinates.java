package engineer.comanmadalin.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class Coordinates {
    private int x;
    private int y;

    public Coordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
}
