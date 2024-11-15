package engineer.comanmadalin.actions.statistics;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.Input;

/**
 * The type Get total games played.
 */
public final class GetTotalGamesPlayed extends BaseAction {
    /**
     * Instantiates a new Get total games played.
     *
     * @param command the command
     */
    public GetTotalGamesPlayed(final String command) {
        super(command);
    }

    @Override
    public void run(final Game game) {
        this.setResult(String.valueOf(Input.getInstance().getGamesPlayed()));
    }
}
