package engineer.comanmadalin.cards;

import engineer.comanmadalin.cards.minion.BaseMinionCard;

import java.util.List;

/**
 * The type Card utils.
 */
public final class CardUtils {
    private CardUtils() {
    }

    /**
     * Is card valid string.
     *
     * @param board       the board
     * @param coordinates the coordinates
     * @return the string
     */
    public static String isCardValid(final List<List<BaseMinionCard>> board,
                                     final Coordinates coordinates) {
        if (board.size() <= coordinates.getX()) {
            return "No card available at that position.";
        }
        if (board.get(coordinates.getX()).size() <= coordinates.getY()) {
            return "No card available at that position.";
        }
        return null;
    }
}
