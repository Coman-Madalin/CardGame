package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.databind.JsonNode;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaceCard extends BaseAction {
    private int handID;

    public PlaceCard(final String command, final JsonNode handID) {
        super(command);
        this.handID = handID.asInt();
    }

    @Override
    public void run(final Game game) {
        final Player player = game.getPlayers()[game.getPlayerIDTurn()];
        final BaseMinionCard card = (BaseMinionCard) player.getHand().getCards().get(handID);

        if (player.getMana() < card.getManaCost()) {
            setError("Not enough mana to place card on table.");
            return;
        }

        final int cardPlacementRequirement = card.getMustBePlaceOnFrontRow() ? 2 : 3;
        final List<BaseMinionCard> row;
        if (cardPlacementRequirement == 3) {
            row = game.getBoard().get(cardPlacementRequirement - 3 * game.getPlayerIDTurn());
        } else {
            row = game.getBoard().get(cardPlacementRequirement - game.getPlayerIDTurn());
        }

        if (row.size() >= 5) {
            this.setError("Cannot place card on board since row is full.");
            return;
        }

        row.add(card);
        player.addMana(-card.getManaCost());
        player.getHand().getCards().remove(handID);
    }
}
