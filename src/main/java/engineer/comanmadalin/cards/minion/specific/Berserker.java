package engineer.comanmadalin.cards.minion.specific;

import engineer.comanmadalin.cards.minion.BaseMinionCard;

import java.util.ArrayList;

public class Berserker extends BaseMinionCard {
    public Berserker( int mana, int health, String description, ArrayList<String> colors, String name) {
        super( mana, health, description, colors, name);
        setMustBePlaceOnFrontRow(false);
    }
}
