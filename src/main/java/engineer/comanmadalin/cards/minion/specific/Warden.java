package engineer.comanmadalin.cards.minion.specific;

import engineer.comanmadalin.cards.minion.BaseMinionCard;

import java.util.ArrayList;

/**
 * The type Warden.
 */
public final class Warden extends BaseMinionCard {

    /**
     * Instantiates a new Warden.
     *
     * @param mana        the mana
     * @param health      the health
     * @param description the description
     * @param colors      the colors
     * @param name        the name
     */
    public Warden(final int mana, final int health, final String description,
                  final ArrayList<String> colors, final String name) {
        super(mana, health, description, colors, name);
        setMustBePlaceOnFrontRow(true);
        setIsTank(true);
    }
}
