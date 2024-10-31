package engineer.comanmadalin.game;

import engineer.comanmadalin.actions.BaseAction;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public final class Game {
    private GameConditions gameConditions;
    private ArrayList<BaseAction> actions;

    public Game() {
        gameConditions = new GameConditions();
        actions = new ArrayList<>();
    }
}
