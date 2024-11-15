package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.databind.JsonNode;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.Input;
import engineer.comanmadalin.utils.Coordinates;
import engineer.comanmadalin.utils.json.JsonUtils;
import lombok.Getter;

import java.util.List;

@Getter
public class UseAttackHero extends BaseAction {
    private final Coordinates coordinatesAttacker;

    public UseAttackHero(final String command, final JsonNode coordinatesAttacker) {
        super(command);
        this.coordinatesAttacker = JsonUtils.getObjectMapper()
                .convertValue(coordinatesAttacker, Coordinates.class);
    }

    @Override
    public void run(final Game game) {
        final List<List<BaseMinionCard>> board = game.getBoard();
        final BaseMinionCard attacker = board.get(coordinatesAttacker.getX())
                .get(coordinatesAttacker.getY());

        final int heroOwnerID = (game.getPlayerIDTurn() + 1) % 2;

        if (attacker.getIsFrozen()) {
            this.setError("Attacker card is frozen.");
            return;
        }

        if (attacker.getAttackedThisRound()) {
            this.setError("Attacker card has already attacked this turn.");
            return;
        }

        if (game.checkForTank(heroOwnerID)) {
            this.setError("Attacked card is not of type 'Tank'.");
            return;
        }

        final BaseHero heroAttacked = game.getPlayers()[heroOwnerID].getHero();
        heroAttacked.takeDamage(attacker.getAttackDamage());

        attacker.setAttackedThisRound(true);

        if (heroAttacked.getHealth() <= 0) {
            // This will be changed in a hashmap if we might have more players than 2 in the future
            this.setResult("Player " + (game.getPlayerIDTurn() == 0 ? "one" : "two")
                    + " killed the enemy hero.");

            Input.getINSTANCE().getPlayersData()[game.getPlayerIDTurn()].increaseWins();
            Input.getINSTANCE().increaseGamesPlayed();
        }
    }
}
