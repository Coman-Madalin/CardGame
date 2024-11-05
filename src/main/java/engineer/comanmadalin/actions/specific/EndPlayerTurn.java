package engineer.comanmadalin.actions.specific;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;

public class EndPlayerTurn extends BaseAction {
    public EndPlayerTurn(String command) {
        super(command);
    }

    @Override
    public void run(Game game) {
        if (game.getNextEndTurnWillEndRound()) {
            game.startOfRound();
        }

        game.setPlayerIDTurn((game.getPlayerIDTurn() + 1) % 2);
        game.setNextEndTurnWillEndRound(!game.getNextEndTurnWillEndRound());
    }
}
