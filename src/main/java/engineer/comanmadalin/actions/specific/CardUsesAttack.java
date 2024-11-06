package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.databind.JsonNode;
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
public class CardUsesAttack extends BaseAction {
    private Coordinates coordinatesAttacker;
    private Coordinates coordinatesAttacked;

    public CardUsesAttack(String command, JsonNode coordinatesAttacker, JsonNode coordinatesAttacked) {
        super(command);
        this.coordinatesAttacker = JsonUtils.getObjectMapper().convertValue(coordinatesAttacker, Coordinates.class);
        this.coordinatesAttacked = JsonUtils.getObjectMapper().convertValue(coordinatesAttacked, Coordinates.class);
    }

    @Override
    public void run(Game game) {
        List<List<BaseMinionCard>> board = game.getBoard();
        BaseMinionCard attacker = board.get(coordinatesAttacker.getX()).get(coordinatesAttacker.getY());
        BaseMinionCard attacked = board.get(coordinatesAttacked.getX()).get(coordinatesAttacked.getY());

        attacked.takeDamage(attacker.getAttackDamage());

        if (attacked.getHealth() <= 0) {
            board.get(coordinatesAttacked.getX()).remove(coordinatesAttacked.getY());
        }
    }
}
