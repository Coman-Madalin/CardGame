package engineer.comanmadalin.actions.debug;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.json.JsonUtils;

import java.util.List;

/**
 * The type Get frozen cards on table.
 */
public final class GetFrozenCardsOnTable extends BaseAction {
    /**
     * Instantiates a new Get frozen cards on table.
     *
     * @param command the command
     */
    public GetFrozenCardsOnTable(final String command) {
        super(command);
    }

    @Override
    public void run(final Game game) {
        final List<BaseMinionCard> frozenCards = game.findAllFrozenCards();
        final String serializedCards = String
                .valueOf(JsonUtils.getOBJECT_MAPPER().valueToTree(frozenCards));

        this.setResult(serializedCards);
    }
}
