package engineer.comanmadalin.actions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class BaseAction {
    private String command;

    public BaseAction() {
        command = "";
    }

}
