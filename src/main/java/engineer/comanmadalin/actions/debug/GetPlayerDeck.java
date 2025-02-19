package engineer.comanmadalin.actions.debug;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.json.JsonUtils;
import engineer.comanmadalin.player.Player;
import lombok.Getter;

/**
 * The type Get player deck.
 */
@Getter
public final class GetPlayerDeck extends BaseAction {
    private final int playerID;

    /**
     * Instantiates a new Get player deck.
     *
     * @param command  the command
     * @param playerID the player id
     */
    public GetPlayerDeck(final String command, final JsonNode playerID) {
        super(command);
        this.playerID = playerID.asInt();
    }

    @Override
    public void run(final Game game) {
        final Player player = game.getPlayers()[playerID - 1];
        final ObjectMapper mapper = JsonUtils.getOBJECT_MAPPER();
        final String serializedDeck = String.valueOf(mapper.valueToTree(player.getDeck()));

        this.setResult(serializedDeck);
    }

}
