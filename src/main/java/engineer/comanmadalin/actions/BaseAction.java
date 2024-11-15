package engineer.comanmadalin.actions;

import engineer.comanmadalin.game.Game;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Base action.
 */
@Getter
@Setter
public abstract class BaseAction {
    private String command;
    private String error = null;
    private String result = null;

    /**
     * Instantiates a new Base action.
     *
     * @param command the command
     */
    public BaseAction(final String command) {
        this.command = command;
    }

    /**
     * Run.
     *
     * @param game the game
     */
    public abstract void run(Game game);
}
