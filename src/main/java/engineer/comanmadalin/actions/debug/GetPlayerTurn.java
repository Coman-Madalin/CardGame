package engineer.comanmadalin.actions.debug;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPlayerTurn extends BaseAction {
    public GetPlayerTurn(final String command) {
        super(command);
    }

    @Override
    public void run(final Game game) {
        this.setResult(String.valueOf(game.getPlayerIDTurn() + 1));
    }
}
