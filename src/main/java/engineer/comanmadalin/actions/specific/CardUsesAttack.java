package engineer.comanmadalin.actions.specific;

import com.fasterxml.jackson.databind.JsonNode;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.utils.Coordinates;
import engineer.comanmadalin.utils.json.JsonUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardUsesAttack extends BaseAction {
    private Coordinates cardAttacker, cardAttacked;

    public CardUsesAttack(String command, JsonNode cardAttacker, JsonNode cardAttacked) {
        super(command);
        this.cardAttacker = JsonUtils.getObjectMapper().convertValue(cardAttacker, Coordinates.class);
        this.cardAttacked = JsonUtils.getObjectMapper().convertValue(cardAttacked, Coordinates.class);
    }

    @Override
    public void run(Game game) {
        BaseMinionCard attacker = game.getBoard().get(cardAttacker.getX()).get(cardAttacker.getY());
        BaseMinionCard attacked = game.getBoard().get(cardAttacked.getX()).get(cardAttacked.getY());

        attacked.takeDamage(attacker.getAttackDamage());
    }
}
