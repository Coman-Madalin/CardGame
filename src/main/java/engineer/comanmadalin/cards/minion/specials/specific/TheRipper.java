package engineer.comanmadalin.cards.minion.specials.specific;

import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.cards.minion.specials.BaseSpecialCard;

import java.util.ArrayList;

public class TheRipper extends BaseSpecialCard {
    public TheRipper(int mana, int health, String description, ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
        setMustBePlaceOnFrontRow(true);
    }

    @Override
    public void ability(BaseMinionCard enemy) {
        enemy.subtractAttackDamage(2);
    }
}
