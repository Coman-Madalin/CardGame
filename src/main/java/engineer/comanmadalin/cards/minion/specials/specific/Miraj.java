package engineer.comanmadalin.cards.minion.specials.specific;

import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.cards.minion.specials.BaseSpecialCard;

import java.util.ArrayList;

/**
 * The type Miraj.
 */
public final class Miraj extends BaseSpecialCard {
    /**
     * Instantiates a new Miraj.
     *
     * @param mana        the mana
     * @param health      the health
     * @param description the description
     * @param colors      the colors
     * @param name        the name
     */
    public Miraj(final int mana, final int health, final String description,
                 final ArrayList<String> colors, final String name) {
        super(mana, health, description, colors, name);
        setMustBePlaceOnFrontRow(true);
    }

    @Override
    public void ability(final BaseMinionCard enemy) {
        final int enemyHealth = enemy.getHealth();
        enemy.setHealth(this.getHealth());
        this.setHealth(enemyHealth);
    }
}
