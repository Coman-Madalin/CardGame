package engineer.comanmadalin.actions.specific;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.player.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetPlayerDeck extends BaseAction {
    private int playerID;

    public GetPlayerDeck(String playerID) {
        this.playerID = Integer.parseInt(playerID);
    }

    @Override
    public void run(Object... args) {
        Player[] players = (Player[]) args[0];
        Player player = players[playerID - 1];
        System.out.println(player.getDeck());
    }

}
