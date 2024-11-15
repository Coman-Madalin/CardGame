package engineer.comanmadalin.actions.debug;

import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.utils.json.JsonUtils;

import java.util.List;

public final class GetCardsOnTable extends BaseAction {
    public GetCardsOnTable(final String command) {
        super(command);
    }

    @Override
    public void run(final Game game) {
        final ObjectMapper mapper = JsonUtils.getOBJECT_MAPPER();
        final List<List<BaseMinionCard>> table = game.getBoard();
        final String serializedDeck = String.valueOf(mapper.valueToTree(table));

        this.setResult(serializedDeck);
    }
}
