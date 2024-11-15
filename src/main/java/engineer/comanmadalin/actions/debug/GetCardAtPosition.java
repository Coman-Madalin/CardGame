package engineer.comanmadalin.actions.debug;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.utils.Coordinates;
import engineer.comanmadalin.utils.json.JsonUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class GetCardAtPosition extends BaseAction {
    private Coordinates cardCoordinates;

    public GetCardAtPosition(final String command, final JsonNode x, final JsonNode y) {
        super(command);
        cardCoordinates = new Coordinates(x.asInt(), y.asInt());
    }

    @Override
    public void run(final Game game) {
        final List<List<BaseMinionCard>> board = game.getBoard();

        if (board.size() <= cardCoordinates.getX()) {
            setError("No card available at that position.");
            return;
        }
        if (board.get(getCardCoordinates().getX()).size() <= cardCoordinates.getY()) {
            setError("No card available at that position.");
            return;
        }

        final BaseMinionCard card = game.getBoard().get(cardCoordinates.getX())
                .get(cardCoordinates.getY());

        final ObjectMapper mapper = JsonUtils.getOBJECT_MAPPER();
        final String serializedCard = String.valueOf(mapper.valueToTree(card));

        this.setResult(serializedCard);
    }
}
