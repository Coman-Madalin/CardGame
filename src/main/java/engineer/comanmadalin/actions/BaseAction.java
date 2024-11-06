package engineer.comanmadalin.actions;

import engineer.comanmadalin.game.Game;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class BaseAction {
    private String command;
    private String error;
    private String result;

    public BaseAction(String command) {
        this.command = command;
        error = "";
        result = "";
    }

    public abstract void run(Game game);
}
