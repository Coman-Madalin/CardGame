package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.utils.json.JsonUtils;

import java.util.List;

public class GetCardsOnTable extends BaseAction {
    public GetCardsOnTable(String command) {
        super(command);
    }

    @Override
    public void run(Game game) {
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        List<List<BaseMinionCard>> table = game.getBoard();
        String serializedDeck = String.valueOf(mapper.valueToTree(table));

        this.setResult(serializedDeck);
    }

}
