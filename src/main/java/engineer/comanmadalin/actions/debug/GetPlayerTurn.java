package engineer.comanmadalin.actions.debug;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;

/**
 * The type Get player turn.
 */
public final class GetPlayerTurn extends BaseAction {
    /**
     * Instantiates a new Get player turn.
     *
     * @param command the command
     */
    public GetPlayerTurn(final String command) {
        super(command);
    }

    @Override
    public void run(final Game game) {
        this.setResult(String.valueOf(game.getPlayerIDTurn() + 1));
    }
}
