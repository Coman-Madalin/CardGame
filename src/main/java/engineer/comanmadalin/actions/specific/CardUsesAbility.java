package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.databind.JsonNode;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.cards.minion.specials.BaseSpecialCard;
import engineer.comanmadalin.cards.minion.specials.specific.Disciple;
import engineer.comanmadalin.cards.minion.specials.specific.Miraj;
import engineer.comanmadalin.cards.minion.specials.specific.TheCursedOne;
import engineer.comanmadalin.cards.minion.specials.specific.TheRipper;
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

        if (attacker.getIsFrozen()) {
            this.setError("Attacker card is frozen.");
            return;
        }

        if (attacker.getAttackedThisRound()) {
            this.setError("Attacker card has already attacked this turn.");
            return;
        }

        int attackedPlayerID;
        if (coordinatesAttacked.getX() < 2) {
            attackedPlayerID = 1;
        } else {
            attackedPlayerID = 0;
        }

        int attackerPlayerID;
        if (coordinatesAttacker.getX() < 2) {
            attackerPlayerID = 1;
        } else {
            attackerPlayerID = 0;
        }

        if (attacker instanceof Disciple) {
            if (attackedPlayerID != attackerPlayerID) {
                this.setError("Attacked card does not belong to the current player.");
                return;
            }
        } else if (attacker instanceof TheRipper || attacker instanceof Miraj ||
                attacker instanceof TheCursedOne) {
            if (attackedPlayerID == attackerPlayerID) {
                this.setError("Attacked card does not belong to the enemy.");
                return;
            }

            if (!attacked.getIsTank() && game.checkForTank(attackedPlayerID)) {
                this.setError("Attacked card is not of type 'Tank'.");
                return;
            }
        }

        attacker.ability(attacked);
        attacker.setAttackedThisRound(true);

        if (attacked.getHealth() <= 0) {
            board.get(coordinatesAttacked.getX()).remove(coordinatesAttacked.getY());
        }
    }
}
