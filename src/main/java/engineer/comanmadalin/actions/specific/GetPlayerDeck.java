package engineer.comanmadalin.actions.specific;

import engineer.comanmadalin.actions.BaseAction;
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
}
