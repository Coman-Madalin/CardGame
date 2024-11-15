package engineer.comanmadalin.cards.minion.specials.specific;

import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.cards.minion.specials.BaseSpecialCard;

import java.util.ArrayList;

public final class TheCursedOne extends BaseSpecialCard {
    public TheCursedOne(final int mana, final int health, final String description,
                        final ArrayList<String> colors, final String name) {
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
