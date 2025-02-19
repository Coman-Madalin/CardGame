package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.databind.JsonNode;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.Coordinates;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.json.JsonUtils;
import lombok.Getter;

import java.util.List;

import static engineer.comanmadalin.cards.CardUtils.isCardValid;

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

    private boolean isValid(final Game game) {
        final List<List<BaseMinionCard>> board = game.getBoard();

        String result = isCardValid(board, getCoordinatesAttacker());
        if (result != null) {
            setError(result);
            return false;
        }
        result = isCardValid(board, getCoordinatesAttacked());
        if (result != null) {
            setError(result);
            return false;
        }

        final BaseMinionCard attacker = board
                .get(getCoordinatesAttacker().getX())
                .get(getCoordinatesAttacker().getY());
        final BaseMinionCard attacked = board
                .get(getCoordinatesAttacked().getX())
                .get(getCoordinatesAttacked().getY());

        final int attackerPlayerID;
        if (getCoordinatesAttacker().getX() < 2) {
            attackerPlayerID = 1;
        } else {
            attackerPlayerID = 0;
        }

        final int attackedPlayerID;
        if (getCoordinatesAttacked().getX() < 2) {
            attackedPlayerID = 1;
        } else {
            attackedPlayerID = 0;
        }

        if (attackedPlayerID == attackerPlayerID) {
            setError("Attacked card does not belong to the enemy.");
            return false;
        }

        if (attacker.getAttackedThisRound()) {
            setError("Attacker card has already attacked this turn.");
            return false;
        }

        if (attacker.getIsFrozen()) {
            setError("Attacker card is frozen.");
            return false;
        }

        if (!attacked.getIsTank() && game.checkForTank(attackedPlayerID)) {
            setError("Attacked card is not of type 'Tank'.");
            return false;
        }

        return true;
    }

    @Override
    public void run(final Game game) {
        if (!isValid(game)) {
            return;
        }

        final List<List<BaseMinionCard>> board = game.getBoard();
        final BaseMinionCard attacker = board.get(coordinatesAttacker.getX())
                .get(coordinatesAttacker.getY());
        final BaseMinionCard attacked = board.get(coordinatesAttacked.getX())
                .get(coordinatesAttacked.getY());

        attacked.takeDamage(attacker.getAttackDamage());
        attacker.setAttackedThisRound(true);

        if (attacked.getHealth() <= 0) {
            board.get(coordinatesAttacked.getX()).remove(coordinatesAttacked.getY());
        }
    }
}
