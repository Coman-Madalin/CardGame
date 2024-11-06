package engineer.comanmadalin.actions.specific;

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

    public PlaceCard(String command, String handID) {
        super(command);
        this.handID = Integer.parseInt(handID);
    }

    @Override
    public void run(Game game) {
        Player player = game.getPlayers()[game.getPlayerIDTurn()];
        BaseMinionCard card = (BaseMinionCard) player.getHand().getCards().get(handID);

        if (player.getMana() < card.getManaCost()) {
            setError("Not enough mana to place card on table.");
            return;
        }

        int cardPlacementRequirement = card.getMustBePlaceOnFrontRow() ? 2 : 3;
        List<BaseMinionCard> row;
        if (cardPlacementRequirement == 3) {
            row = game.getBoard().get(cardPlacementRequirement - 3 * game.getPlayerIDTurn());
        } else {
            row = game.getBoard().get(cardPlacementRequirement - game.getPlayerIDTurn());
        }

        if (row.size() >= 5) {
            setError("Cannot place card on board since row is full.");
            return;
        }

        row.add(card);
        player.addMana(-card.getManaCost());
        player.getHand().getCards().remove(handID);

    }


}
