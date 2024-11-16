package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.databind.JsonNode;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.player.Player;
import lombok.Getter;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

/**
 * The type Place card.
 */
@Getter
public final class PlaceCard extends BaseAction {

    // Because we don't have tuples/pairs, we will use a concatenated string
    private static final Map<String, Integer> DATA_TO_ROW = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("1false", 0),
            new AbstractMap.SimpleEntry<>("1true", 1),
            new AbstractMap.SimpleEntry<>("0true", 2),
            new AbstractMap.SimpleEntry<>("0false", 3)
    );

    private final int handID;

    /**
     * Instantiates a new Place card.
     *
     * @param command the command
     * @param handID  the hand id
     */
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

        final List<BaseMinionCard> row;

        final String playerIDAndOnFrontRow =
                String.valueOf(game.getPlayerIDTurn()) + card.getMustBePlaceOnFrontRow();

        row = game.getBoard().get(DATA_TO_ROW.get(playerIDAndOnFrontRow));

        if (row.size() >= Game.getNUMBER_OF_ELEMENTS_PER_ROW()) {
            this.setError("Cannot place card on board since row is full.");
            return;
        }

        row.add(card);
        player.addMana(-card.getManaCost());
        player.getHand().getCards().remove(handID);
    }
}
