package engineer.comanmadalin.cards.minion.specials;

import engineer.comanmadalin.cards.minion.BaseMinionCard;

import java.util.ArrayList;

public abstract class BaseSpecialCard extends BaseMinionCard {
    public BaseSpecialCard(int mana, int health, String description,
                           ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
    }

    public abstract void ability();
}
