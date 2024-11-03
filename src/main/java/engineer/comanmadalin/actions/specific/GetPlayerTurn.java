package engineer.comanmadalin.actions.specific;

import engineer.comanmadalin.actions.BaseAction;
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
    public void run(Object... params) {
        Integer turn = (Integer) params[0];
        this.setResult(String.valueOf(turn));
    }
}
