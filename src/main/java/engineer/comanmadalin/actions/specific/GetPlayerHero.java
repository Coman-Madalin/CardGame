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
public class GetPlayerHero extends BaseAction {
    private int playerID;

    public GetPlayerHero(String command, String playerID) {
        super(command);
        this.playerID = Integer.parseInt(playerID);
    }

    @Override
    public void run(Object... params) {
        Player[] players = (Player[]) params[0];
        Player player = players[playerID - 1];
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        String serializedHero;

        try {
            serializedHero = mapper.writeValueAsString(player.getHero());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        this.setResult(serializedHero);
    }
}
