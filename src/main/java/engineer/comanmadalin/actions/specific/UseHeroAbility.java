package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.databind.JsonNode;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.cards.hero.specific.EmpressThorina;
import engineer.comanmadalin.cards.hero.specific.LordRoyce;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.player.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class UseHeroAbility extends BaseAction {
    private int affectedRow;

    public UseHeroAbility(final String command, final JsonNode affectedRow) {
        super(command);
        this.affectedRow = affectedRow.asInt();
    }

    @Override
    public void run(final Game game) {
        final int currentPlayerID = game.getPlayerIDTurn();
        final Player currentPlayer = game.getPlayers()[currentPlayerID];
        final BaseHero currentPlayerHero = currentPlayer.getHero();

        if (currentPlayerHero.getManaCost() > currentPlayer.getMana()) {
            this.setError("Not enough mana to use hero's ability.");
            return;
        }

        if (currentPlayerHero.getAttackedThisRound()) {
            this.setError("Hero has already attacked this turn.");
            return;
        }

        int playerIDOfTargetedRow = 0;
        if (affectedRow < 2) {
            playerIDOfTargetedRow = 1;
        }
        if (currentPlayerHero instanceof LordRoyce || currentPlayerHero instanceof EmpressThorina) {
            if (playerIDOfTargetedRow == currentPlayerID) {
                this.setError("Selected row does not belong to the enemy.");
                return;
            }
        } else {
            if (playerIDOfTargetedRow != currentPlayerID) {
                this.setError("Selected row does not belong to the current player.");
                return;
            }
        }

        currentPlayer.addMana(-currentPlayerHero.getManaCost());
        currentPlayerHero.ability(game, affectedRow);
        currentPlayerHero.setAttackedThisRound(true);
    }
}
