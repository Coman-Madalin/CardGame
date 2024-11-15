package engineer.comanmadalin.actions.statistics;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.Input;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public final class GetPlayerWins extends BaseAction {
    private int playerID;

    public GetPlayerWins(final String command) {
        super(command);
        if (Objects.equals(command, "getPlayerOneWins")) {
            playerID = 0;
        } else {
            playerID = 1;
        }
    }

    @Override
    public void run(final Game game) {
        this.setResult(String.valueOf(Input.getInstance().getPlayersData()[playerID].getWins()));
    }
}
