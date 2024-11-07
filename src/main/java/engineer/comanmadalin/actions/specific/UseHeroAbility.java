package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.databind.JsonNode;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.player.Player;

import java.util.Objects;

public class UseHeroAbility extends BaseAction {
    int affectedRow;

    public UseHeroAbility(String command, JsonNode affectedRow) {
        super(command);
        this.affectedRow = affectedRow.asInt();
    }

    @Override
    public void run(Game game) {
        int currentPlayerID = game.getPlayerIDTurn();
        Player currentPlayer = game.getPlayers()[currentPlayerID];
        BaseHero currentPlayerHero = currentPlayer.getHero();

        if (Objects.equals(currentPlayerHero.getName(), "General Kocioraw")) {
            System.out.println("DADAD");
        }
        if (currentPlayerHero.getManaCost() > currentPlayer.getMana()) {
            this.setError("Not enough mana to use hero's ability.");
            return;
        }

        if (currentPlayerHero.getAttackedThisRound()) {
            this.setError("Hero has already attacked this turn.");
            return;
        }

        currentPlayer.addMana(-currentPlayerHero.getManaCost());

        currentPlayerHero.ability(game, affectedRow);
        currentPlayerHero.setAttackedThisRound(true);
    }
}
