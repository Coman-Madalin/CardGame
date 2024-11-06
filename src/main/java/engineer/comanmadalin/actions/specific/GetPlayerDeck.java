package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.player.Player;
import engineer.comanmadalin.utils.json.JsonUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetPlayerDeck extends BaseAction {
    private int playerID;

    public GetPlayerDeck(String command, JsonNode playerID) {
        super(command);
        this.playerID = playerID.asInt();
    }

    @Override
    public void run(Game game) {
        Player player = game.getPlayers()[playerID - 1];
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        String serializedDeck;

        try {
            serializedDeck = mapper.writeValueAsString(player.getDeck());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        this.setResult(serializedDeck);
    }

}
