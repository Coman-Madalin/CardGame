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

@Getter
@Setter
public class GetCardsInHand extends BaseAction {
    private int playerID;

    public GetCardsInHand(String command, JsonNode playerID) {
        super(command);
        this.playerID = playerID.asInt();
    }

    @Override
    public void run(Game game) {
        Player player = game.getPlayers()[playerID - 1];
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        String serializedDeck;

        try {
            serializedDeck = mapper.writeValueAsString(player.getHand());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        this.setResult(serializedDeck);
    }
}
