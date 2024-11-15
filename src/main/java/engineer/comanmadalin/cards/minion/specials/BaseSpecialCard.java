package engineer.comanmadalin.cards.minion.specials;

import engineer.comanmadalin.cards.minion.BaseMinionCard;

import java.util.ArrayList;

public abstract class BaseSpecialCard extends BaseMinionCard {
    public BaseSpecialCard(final int mana, final int health, final String description,
                           final ArrayList<String> colors, final String name) {
        super(mana, health, description, colors, name);
    }

    public abstract void ability(BaseMinionCard target);
}
