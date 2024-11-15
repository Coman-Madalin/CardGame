package engineer.comanmadalin.cards.minion.specific;

import engineer.comanmadalin.cards.minion.BaseMinionCard;

import java.util.ArrayList;

public class Warden extends BaseMinionCard {

    public Warden(final int mana, final int health, final String description,
                  final ArrayList<String> colors, final String name) {
        super(mana, health, description, colors, name);
        setMustBePlaceOnFrontRow(true);
        setIsTank(true);
    }
}
