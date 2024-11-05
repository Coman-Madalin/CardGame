package engineer.comanmadalin.actions.specific;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetPlayerTurn extends BaseAction {
    public GetPlayerTurn(String command) {
        super(command);
    }

    @Override
    public void run(Game game) {
        this.setResult(String.valueOf(game.getPlayerIDTurn() + 1));
    }
}
