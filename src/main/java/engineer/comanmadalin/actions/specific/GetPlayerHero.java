package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.player.Player;
import engineer.comanmadalin.utils.json.JsonUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPlayerHero extends BaseAction {
    private int playerID;

    public GetPlayerHero(String command, JsonNode playerID) {
        super(command);
        this.playerID = playerID.asInt();
    }

    @Override
    public void run(Game game) {
        Player player = game.getPlayers()[playerID - 1];
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        String serializedHero = String.valueOf(mapper.valueToTree(player.getHero()));

        this.setResult(serializedHero);
    }
}
