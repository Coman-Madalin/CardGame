package engineer.comanmadalin.cards.minion.specific;

import engineer.comanmadalin.cards.minion.BaseMinionCard;

import java.util.ArrayList;

public class Warden extends BaseMinionCard {

    public Warden(int mana, int health, String description, ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
        setMustBePlaceOnFrontRow(true);
        setIsTank(true);
    }
}
