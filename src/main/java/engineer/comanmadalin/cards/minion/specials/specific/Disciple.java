package engineer.comanmadalin.cards.minion.specials.specific;

import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.cards.minion.specials.BaseSpecialCard;

import java.util.ArrayList;

public final class Disciple extends BaseSpecialCard {
    public Disciple(final int mana, final int health, final String description,
                    final ArrayList<String> colors, final String name) {
        super(mana, health, description, colors, name);
        setMustBePlaceOnFrontRow(false);
    }

    @Override
    public void ability(final BaseMinionCard ally) {
        ally.heal(2);
    }
}
