package engineer.comanmadalin.cards.minion.specific;

import engineer.comanmadalin.cards.minion.BaseMinionCard;

import java.util.ArrayList;

public final class Berserker extends BaseMinionCard {
    public Berserker(final int mana, final int health, final String description,
                     final ArrayList<String> colors, final String name) {
        super(mana, health, description, colors, name);
        setMustBePlaceOnFrontRow(false);
    }
}
