package engineer.comanmadalin.cards.minion.specials.specific;

import engineer.comanmadalin.cards.minion.specials.BaseSpecialCard;

import java.util.ArrayList;

public class TheCursedOne extends BaseSpecialCard {
    public TheCursedOne(int mana, int health, String description, ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
        setMustBePlaceOnFrontRow(false);
    }

    @Override
    public void ability() {
        // TODO: Implement ability

    }
}
