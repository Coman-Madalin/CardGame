package engineer.comanmadalin.actions;

import engineer.comanmadalin.game.Game;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseAction {
    private String command;
    private String error = null;
    private String result = null;

    public BaseAction(String command) {
        this.command = command;
    }

    public abstract void run(Game game);
}
