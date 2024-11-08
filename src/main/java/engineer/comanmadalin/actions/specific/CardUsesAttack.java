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

        if (coordinatesAttacked.getX() == 1 && coordinatesAttacked.getY() == 0) {
            System.out.println();
        }

        if (board.size() <= coordinatesAttacked.getX()) {
            setError("No card available at that position.");
            return;
        }
        if (board.get(coordinatesAttacked.getX()).size() <= coordinatesAttacked.getY()) {
            setError("No card available at that position.");
            return;
        }
        BaseMinionCard attacker = board.get(coordinatesAttacker.getX()).get(coordinatesAttacker.getY());
        BaseMinionCard attacked = board.get(coordinatesAttacked.getX()).get(coordinatesAttacked.getY());

        if (attacker.getAttackedThisRound()) {
            this.setError("Attacker card has already attacked this turn.");
            return;
        }

        if (attacker.getIsFrozen()) {
            this.setError("Attacker card is frozen.");
            return;
        }

        int attackedPlayerID;
        if (coordinatesAttacked.getX() < 2) {
            attackedPlayerID = 1;
        } else {
            attackedPlayerID = 0;
        }

        if (!attacked.getIsTank() && game.checkForTank(attackedPlayerID)) {
            this.setError("Attacked card is not of type 'Tank'.");
            return;
        }


        attacked.takeDamage(attacker.getAttackDamage());
        attacker.setAttackedThisRound(true);

        if (attacked.getHealth() <= 0) {
            board.get(coordinatesAttacked.getX()).remove(coordinatesAttacked.getY());
        }
    }
}
