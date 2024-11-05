package engineer.comanmadalin.cards.minion;

import engineer.comanmadalin.cards.BaseCard;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class BaseMinionCard extends BaseCard implements Cloneable {
    private int attackDamage;
    private Boolean mustBePlaceOnFrontRow;

    public BaseMinionCard(int mana, int health, String description,
                          ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
        this.attackDamage = 0;
        mustBePlaceOnFrontRow = null;
    }


    @Override
    public BaseMinionCard clone() {
        BaseMinionCard clone = (BaseMinionCard) super.clone();
        clone.mustBePlaceOnFrontRow = this.mustBePlaceOnFrontRow;
        return clone;
    }
}
