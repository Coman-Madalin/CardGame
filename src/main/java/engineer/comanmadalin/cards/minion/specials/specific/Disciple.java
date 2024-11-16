package engineer.comanmadalin.cards.minion.specials.specific;

import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.cards.minion.specials.BaseSpecialCard;

import java.util.List;


/**
 * The type Disciple.
 */
public final class Disciple extends BaseSpecialCard {
    /**
     * Instantiates a new Disciple.
     *
     * @param mana        the mana
     * @param health      the health
     * @param description the description
     * @param colors      the colors
     * @param name        the name
     */
    public Disciple(final int mana, final int health, final String description,
                    final List<String> colors, final String name) {
        super(mana, health, description, colors, name);
        setMustBePlaceOnFrontRow(false);
    }

    @Override
    public void ability(final BaseMinionCard ally) {
        ally.heal(2);
    }
}
