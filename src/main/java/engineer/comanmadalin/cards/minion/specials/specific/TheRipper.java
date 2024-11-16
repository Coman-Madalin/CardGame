package engineer.comanmadalin.cards.minion.specials.specific;

import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.cards.minion.specials.BaseSpecialCard;

import java.util.List;


/**
 * The type The ripper.
 */
public final class TheRipper extends BaseSpecialCard {
    /**
     * Instantiates a new The ripper.
     *
     * @param mana        the mana
     * @param health      the health
     * @param description the description
     * @param colors      the colors
     * @param name        the name
     */
    public TheRipper(final int mana, final int health, final String description,
                     final List<String> colors, final String name) {
        super(mana, health, description, colors, name);
        setMustBePlaceOnFrontRow(true);
    }

    @Override
    public void ability(final BaseMinionCard enemy) {
        enemy.subtractAttackDamage(2);
    }
}
