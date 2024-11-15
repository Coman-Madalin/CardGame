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
    private Boolean isTank = false;
    private Boolean isFrozen = false;

    public BaseMinionCard(final int mana, final int health, final String description,
                          final ArrayList<String> colors, final String name) {
        super(mana, health, description, colors, name);
    }

    public void subtractAttackDamage(final int toSubtract) {
        attackDamage -= toSubtract;
        if (attackDamage < 0) {
            attackDamage = 0;
        }
    }

    public void heal(final int toHeal) {
        this.setHealth(this.getHealth() + toHeal);
    }


    @Override
    public BaseMinionCard clone() {
        final BaseMinionCard clone = (BaseMinionCard) super.clone();
        clone.mustBePlaceOnFrontRow = this.mustBePlaceOnFrontRow;
        return clone;
    }
}
