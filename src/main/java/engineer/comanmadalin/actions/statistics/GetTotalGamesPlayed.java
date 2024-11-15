package engineer.comanmadalin.actions.statistics;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.Input;

public class GetTotalGamesPlayed extends BaseAction {
    public GetTotalGamesPlayed(String command) {
        super(command);
    }

    @Override
    public void run(Game game) {
        this.setResult(String.valueOf(Input.getINSTANCE().getGamesPlayed()));
    }
}
