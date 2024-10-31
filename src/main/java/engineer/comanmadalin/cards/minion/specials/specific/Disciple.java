package engineer.comanmadalin.cards.minion.specials.specific;

import engineer.comanmadalin.cards.minion.specials.BaseSpecialCard;

import java.util.ArrayList;

public class Disciple extends BaseSpecialCard {
    public Disciple(int mana, int health, String description, ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
        setMustBePlaceOnFrontRow(false);
    }

    @Override
    public void ability() {
        // TODO: Implement ability
    }
}
