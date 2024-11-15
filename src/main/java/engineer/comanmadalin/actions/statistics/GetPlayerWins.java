package engineer.comanmadalin.actions.statistics;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.Input;
import lombok.Getter;

import java.util.Objects;

/**
 * The type Get player wins.
 */
@Getter
public final class GetPlayerWins extends BaseAction {
    private final int playerID;

    /**
     * Instantiates a new Get player wins.
     *
     * @param command the command
     */
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
