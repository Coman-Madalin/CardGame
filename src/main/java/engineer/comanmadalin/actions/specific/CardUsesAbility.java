package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.databind.JsonNode;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.cards.minion.specials.BaseSpecialCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.utils.Coordinates;
import engineer.comanmadalin.utils.json.JsonUtils;

import java.util.List;

public class CardUsesAbility extends BaseAction {
    private final Coordinates coordinatesAttacker;
    private final Coordinates coordinatesAttacked;

    public CardUsesAbility(String command, JsonNode coordinatesAttacker, JsonNode coordinatesAttacked) {
        super(command);
        this.coordinatesAttacker = JsonUtils.getObjectMapper().convertValue(coordinatesAttacker, Coordinates.class);
        this.coordinatesAttacked = JsonUtils.getObjectMapper().convertValue(coordinatesAttacked, Coordinates.class);
    }

    @Override
    public void run(Game game) {
        List<List<BaseMinionCard>> board = game.getBoard();
        BaseSpecialCard attacker = (BaseSpecialCard) board.get(coordinatesAttacker.getX())
                .get(coordinatesAttacker.getY());
        BaseMinionCard attacked = board.get(coordinatesAttacked.getX()).get(coordinatesAttacked.getY());

        attacker.ability(attacked);

        if (attacked.getHealth() <= 0) {
            board.get(coordinatesAttacked.getX()).remove(coordinatesAttacked.getY());
        }
    }
}
