package engineer.comanmadalin.cards.minion.specials.specific;

import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.cards.minion.specials.BaseSpecialCard;

import java.util.ArrayList;

public final class TheRipper extends BaseSpecialCard {
    public TheRipper(final int mana, final int health, final String description,
                     final ArrayList<String> colors, final String name) {
        super(mana, health, description, colors, name);
        setMustBePlaceOnFrontRow(true);
    }

    @Override
    public void ability(final BaseMinionCard enemy) {
        enemy.subtractAttackDamage(2);
    }
}
