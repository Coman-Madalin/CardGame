package engineer.comanmadalin.actions.specific;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;

/**
 * The type End player turn.
 */
public final class EndPlayerTurn extends BaseAction {
    /**
     * Instantiates a new End player turn.
     *
     * @param command the command
     */
    public EndPlayerTurn(final String command) {
        super(command);
    }

    @Override
    public void run(final Game game) {
        if (game.getNextEndTurnWillEndRound()) {
            game.startOfRound();
        }

        game.unfreezePlayerCards();
        game.setPlayerIDTurn((game.getPlayerIDTurn() + 1) % 2);
        game.setNextEndTurnWillEndRound(!game.getNextEndTurnWillEndRound());
    }
}
