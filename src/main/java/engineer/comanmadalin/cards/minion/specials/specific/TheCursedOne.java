package engineer.comanmadalin.cards.minion.specials.specific;

import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.cards.minion.specials.BaseSpecialCard;

import java.util.List;


/**
 * The type The cursed one.
 */
public final class TheCursedOne extends BaseSpecialCard {
    /**
     * Instantiates a new The cursed one.
     *
     * @param mana        the mana
     * @param health      the health
     * @param description the description
     * @param colors      the colors
     * @param name        the name
     */
    public TheCursedOne(final int mana, final int health, final String description,
                        final List<String> colors, final String name) {
        super(mana, health, description, colors, name);
        setMustBePlaceOnFrontRow(false);
    }

    @Override
    public void ability(final BaseMinionCard enemy) {
        final int enemyHealth = enemy.getHealth();
        enemy.setHealth(enemy.getAttackDamage());
        enemy.setAttackDamage(enemyHealth);
    }
}
