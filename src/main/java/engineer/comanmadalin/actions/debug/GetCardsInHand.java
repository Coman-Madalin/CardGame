package engineer.comanmadalin.actions.debug;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.player.Player;
import engineer.comanmadalin.utils.json.JsonUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Get cards in hand.
 */
@Getter
@Setter
public final class GetCardsInHand extends BaseAction {
    private int playerID;

    /**
     * Instantiates a new Get cards in hand.
     *
     * @param command  the command
     * @param playerID the player id
     */
    public GetCardsInHand(final String command, final JsonNode playerID) {
        super(command);
        this.playerID = playerID.asInt();
    }

    @Override
    public void run(final Game game) {
        final Player player = game.getPlayers()[playerID - 1];
        final ObjectMapper mapper = JsonUtils.getOBJECT_MAPPER();
        final String serializedDeck = String.valueOf(mapper.valueToTree(player.getHand()));

        this.setResult(serializedDeck);
    }
}
