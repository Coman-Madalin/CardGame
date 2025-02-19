package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.databind.JsonNode;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.Coordinates;
import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.Input;
import engineer.comanmadalin.json.JsonUtils;
import lombok.Getter;

import java.util.List;

/**
 * The type Use attack hero.
 */
@Getter
public final class UseAttackHero extends BaseAction {
    private final Coordinates coordinatesAttacker;

    /**
     * Instantiates a new Use attack hero.
     *
     * @param command             the command
     * @param coordinatesAttacker the coordinates attacker
     */
    public UseAttackHero(final String command, final JsonNode coordinatesAttacker) {
        super(command);
        this.coordinatesAttacker = JsonUtils.getOBJECT_MAPPER()
                .convertValue(coordinatesAttacker, Coordinates.class);
    }

    private boolean isValid(final Game game) {
        final List<List<BaseMinionCard>> board = game.getBoard();

        final BaseMinionCard attacker = board
                .get(getCoordinatesAttacker().getX())
                .get(getCoordinatesAttacker().getY());

        final int heroOwnerID = (game.getPlayerIDTurn() + 1) % 2;


        if (attacker.getIsFrozen()) {
            setError("Attacker card is frozen.");
            return false;
        }

        if (attacker.getAttackedThisRound()) {
            setError("Attacker card has already attacked this turn.");
            return false;
        }

        if (game.checkForTank(heroOwnerID)) {
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

        final int heroOwnerID = (game.getPlayerIDTurn() + 1) % 2;

        final BaseHero heroAttacked = game.getPlayers()[heroOwnerID].getHero();
        heroAttacked.takeDamage(attacker.getAttackDamage());

        attacker.setAttackedThisRound(true);

        if (heroAttacked.getHealth() <= 0) {
            // This will be changed in a map if we have more players than 2 in the future
            this.setResult("Player " + (game.getPlayerIDTurn() == 0 ? "one" : "two")
                    + " killed the enemy hero.");

            Input.getInstance(false).getPlayersData()[game.getPlayerIDTurn()].increaseWins();
            Input.getInstance(false).increaseGamesPlayedCounter();
        }
    }
}
