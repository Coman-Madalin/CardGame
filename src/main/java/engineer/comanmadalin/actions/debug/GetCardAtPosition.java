package engineer.comanmadalin.actions.debug;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.actions.CardUtils;
import engineer.comanmadalin.cards.Coordinates;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.json.JsonUtils;
import lombok.Getter;

import java.util.List;

/**
 * The type Get card at position.
 */
@Getter
public final class GetCardAtPosition extends BaseAction {
    private final Coordinates cardCoordinates;

    /**
     * Instantiates a new Get card at position.
     *
     * @param command the command
     * @param x       the x
     * @param y       the y
     */
    public GetCardAtPosition(final String command, final JsonNode x, final JsonNode y) {
        super(command);
        cardCoordinates = new Coordinates(x.asInt(), y.asInt());
    }

    @Override
    public void run(final Game game) {
        final List<List<BaseMinionCard>> board = game.getBoard();
        final String result = CardUtils.isCardValid(board, cardCoordinates);
        if (result != null) {
            setError(result);
            return;
        }

        final BaseMinionCard card = game.getBoard().get(cardCoordinates.getX())
                .get(cardCoordinates.getY());

        final ObjectMapper mapper = JsonUtils.getOBJECT_MAPPER();
        final String serializedCard = String.valueOf(mapper.valueToTree(card));

        this.setResult(serializedCard);
    }
}
