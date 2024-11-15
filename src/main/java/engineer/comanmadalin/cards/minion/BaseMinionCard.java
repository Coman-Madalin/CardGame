package engineer.comanmadalin.cards.minion;

import engineer.comanmadalin.cards.BaseCard;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * The type Base minion card.
 */
@Getter
@Setter
public class BaseMinionCard extends BaseCard implements Cloneable {
    private int attackDamage = 0;
    private Boolean mustBePlaceOnFrontRow = null;
    private Boolean isTank = false;
    private Boolean isFrozen = false;

    /**
     * Instantiates a new Base minion card.
     *
     * @param mana        the mana
     * @param health      the health
     * @param description the description
     * @param colors      the colors
     * @param name        the name
     */
    public BaseMinionCard(final int mana, final int health, final String description,
                          final ArrayList<String> colors, final String name) {
        super(mana, health, description, colors, name);
    }

    /**
     * Subtract attack damage.
     *
     * @param toSubtract the to subtract
     */
    public void subtractAttackDamage(final int toSubtract) {
        attackDamage -= toSubtract;
        if (attackDamage < 0) {
            attackDamage = 0;
        }
    }

    /**
     * Heal.
     *
     * @param toHeal the to heal
     */
    public void heal(final int toHeal) {
        this.setHealth(this.getHealth() + toHeal);
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return copy of this object
     */
    @Override
    public BaseMinionCard clone() {
        final BaseMinionCard clone = (BaseMinionCard) super.clone();
        clone.mustBePlaceOnFrontRow = this.mustBePlaceOnFrontRow;
        return clone;
    }
}
