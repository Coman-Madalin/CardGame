package engineer.comanmadalin.actions.statistics;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.Input;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class GetPlayerWins extends BaseAction {
    private int playerID;

    public GetPlayerWins(String command) {
        super(command);
        if (Objects.equals(command, "getPlayerOneWins")) {
            playerID = 0;
        } else {
            playerID = 1;
        }
    }

    @Override
    public void run(Game game) {
        this.setResult(String.valueOf(Input.getINSTANCE().getPlayersData()[playerID].getWins()));
    }
}
