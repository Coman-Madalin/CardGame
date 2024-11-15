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
 * The type Get player hero.
 */
@Getter
@Setter
public final class GetPlayerHero extends BaseAction {
    private int playerID;

    /**
     * Instantiates a new Get player hero.
     *
     * @param command  the command
     * @param playerID the player id
     */
    public GetPlayerHero(final String command, final JsonNode playerID) {
        super(command);
        this.playerID = playerID.asInt();
    }

    @Override
    public void run(final Game game) {
        final Player player = game.getPlayers()[playerID - 1];
        final ObjectMapper mapper = JsonUtils.getOBJECT_MAPPER();
        final String serializedHero = String.valueOf(mapper.valueToTree(player.getHero()));

        this.setResult(serializedHero);
    }
}
