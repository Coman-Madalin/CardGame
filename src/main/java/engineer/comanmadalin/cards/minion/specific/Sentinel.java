package engineer.comanmadalin.cards.minion.specific;

import engineer.comanmadalin.cards.minion.BaseMinionCard;

import java.util.ArrayList;

/**
 * The type Sentinel.
 */
public final class Sentinel extends BaseMinionCard {

    /**
     * Instantiates a new Sentinel.
     *
     * @param mana        the mana
     * @param health      the health
     * @param description the description
     * @param colors      the colors
     * @param name        the name
     */
    public Sentinel(final int mana, final int health, final String description,
                    final ArrayList<String> colors, final String name) {
        super(mana, health, description, colors, name);
        setMustBePlaceOnFrontRow(false);
    }
}
