package engineer.comanmadalin.actions.debug;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.utils.json.JsonUtils;

import java.util.List;

public class GetFrozenCardsOnTable extends BaseAction {
    public GetFrozenCardsOnTable(String command) {
        super(command);
    }

    @Override
    public void run(Game game) {
        List<BaseMinionCard> frozenCards = game.findAllFrozenCards();
        String serializedCards = String.valueOf(JsonUtils.getObjectMapper().valueToTree(frozenCards));

        this.setResult(serializedCards);
    }
}
