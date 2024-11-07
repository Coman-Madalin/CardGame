package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.databind.JsonNode;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.utils.Coordinates;
import engineer.comanmadalin.utils.json.JsonUtils;

import java.util.List;

public class UseAttackHero extends BaseAction {
    private final Coordinates coordinatesAttacker;

    public UseAttackHero(String command, JsonNode coordinatesAttacker) {
        super(command);
        this.coordinatesAttacker = JsonUtils.getObjectMapper().convertValue(coordinatesAttacker, Coordinates.class);
    }

    @Override
    public void run(Game game) {
        List<List<BaseMinionCard>> board = game.getBoard();
        BaseMinionCard attacker = board.get(coordinatesAttacker.getX()).get(coordinatesAttacker.getY());

        int heroOwnerID = (game.getPlayerIDTurn() + 1) / 2;
        BaseHero heroAttacked = game.getPlayers()[heroOwnerID].getHero();

        heroAttacked.takeDamage(attacker.getAttackDamage());

        if (heroAttacked.getHealth() <= 0) {
            // This will be changed in a hashmap if we might have more players than 2 in the future
            this.setResult("Player " + (game.getPlayerIDTurn() == 0 ? "one" : "two")
                    + " killed the enemy hero.");
        }
    }
}
