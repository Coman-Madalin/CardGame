package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.databind.JsonNode;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.utils.Coordinates;
import engineer.comanmadalin.utils.json.JsonUtils;
import lombok.Getter;

import java.util.List;

/**
 * The type Card uses attack.
 */
@Getter
public final class CardUsesAttack extends BaseAction {
    private final Coordinates coordinatesAttacker;
    private final Coordinates coordinatesAttacked;

    /**
     * Instantiates a new Card uses attack.
     *
     * @param command             the command
     * @param coordinatesAttacker the coordinates attacker
     * @param coordinatesAttacked the coordinates attacked
     */
    public CardUsesAttack(final String command, final JsonNode coordinatesAttacker,
                          final JsonNode coordinatesAttacked) {
        super(command);
        this.coordinatesAttacker = JsonUtils.getOBJECT_MAPPER()
                .convertValue(coordinatesAttacker, Coordinates.class);
        this.coordinatesAttacked = JsonUtils.getOBJECT_MAPPER()
                .convertValue(coordinatesAttacked, Coordinates.class);
    }

    @Override
    public void run(final Game game) {
        final List<List<BaseMinionCard>> board = game.getBoard();

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
        final BaseMinionCard attacker = board.get(coordinatesAttacker.getX())
                .get(coordinatesAttacker.getY());
        final BaseMinionCard attacked = board.get(coordinatesAttacked.getX())
                .get(coordinatesAttacked.getY());

        final int attackerPlayerID;
        if (coordinatesAttacker.getX() < 2) {
            attackerPlayerID = 1;
        } else {
            attackerPlayerID = 0;
        }

        final int attackedPlayerID;
        if (coordinatesAttacked.getX() < 2) {
            attackedPlayerID = 1;
        } else {
            attackedPlayerID = 0;
        }

        if (attackedPlayerID == attackerPlayerID) {
            this.setError("Attacked card does not belong to the enemy.");
            return;
        }

        if (attacker.getAttackedThisRound()) {
            this.setError("Attacker card has already attacked this turn.");
            return;
        }

        if (attacker.getIsFrozen()) {
            this.setError("Attacker card is frozen.");
            return;
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
