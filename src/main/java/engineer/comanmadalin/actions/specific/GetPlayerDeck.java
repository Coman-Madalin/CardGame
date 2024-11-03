package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.comanmadalin.actions.BaseAction;
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

    public GetPlayerDeck(String command, String playerID) {
        super(command);
        this.playerID = Integer.parseInt(playerID);
    }

    @Override
    public void run(Object... params) {
        Player[] players = (Player[]) params[0];
        Player player = players[playerID - 1];
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
