package engineer.comanmadalin.actions.specific;

import engineer.comanmadalin.actions.BaseAction;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetPlayerHero extends BaseAction {
    private int playerID;

    public GetPlayerHero(String command, String playerID) {
        super(command);
        this.playerID = Integer.parseInt(playerID);
    }

    @Override
    public void run(Object... params) {

    }
}
