package engineer.comanmadalin.cards.minion;

import engineer.comanmadalin.cards.BaseCard;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class BaseMinionCard extends BaseCard {
    private int attackDamage;
    private Boolean mustBePlaceOnFrontRow;

    public BaseMinionCard(int attackDamage) {
        super();
        this.attackDamage = attackDamage;
        mustBePlaceOnFrontRow = null;
    }

    public BaseMinionCard(int mana, int health, String description,
                          ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
        this.attackDamage = 0;
        mustBePlaceOnFrontRow = null;
    }

//    public BaseMinionCard(int attackDamage, int mana, int health, String
//            description, ArrayList<String> colors, String name) {
//        super(mana, health, description, colors, name);
//        this.attackDamage = attackDamage;
//        mustBePlaceOnFrontRow = null;
//    }

}
