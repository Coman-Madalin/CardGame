package engineer.comanmadalin.cards.minion;

import engineer.comanmadalin.cards.BaseCard;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class BaseMinionCard extends BaseCard implements Cloneable {
    private int attackDamage = 0;
    private Boolean mustBePlaceOnFrontRow = null;

    public BaseMinionCard(int mana, int health, String description,
                          ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
    }

    @Override
    public BaseMinionCard clone() {
        BaseMinionCard clone = (BaseMinionCard) super.clone();
        clone.mustBePlaceOnFrontRow = this.mustBePlaceOnFrontRow;
        return clone;
    }
}
