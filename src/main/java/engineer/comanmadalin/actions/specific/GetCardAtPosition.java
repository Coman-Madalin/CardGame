package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.core.JsonProcessingException;
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
public class GetCardAtPosition extends BaseAction {
    private Coordinates cardCoordinates;

    public GetCardAtPosition(String command, JsonNode x, JsonNode y) {
        super(command);
        cardCoordinates = new Coordinates(x.asInt(), y.asInt());
    }

    @Override
    public void run(Game game) {
        List<List<BaseMinionCard>> board = game.getBoard();
        if (board.size() <= cardCoordinates.getX()) {
            setError("No card available at that position.");
            return;
        }
        if (board.get(getCardCoordinates().getX()).size() <= cardCoordinates.getY()) {
            setError("No card available at that position.");
            return;
        }

        BaseMinionCard card = game.getBoard().get(cardCoordinates.getX()).get(cardCoordinates.getY());

        ObjectMapper mapper = JsonUtils.getObjectMapper();
        String serializedCard;

        try {
            serializedCard = mapper.writeValueAsString(card);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        this.setResult(serializedCard);
    }
}
