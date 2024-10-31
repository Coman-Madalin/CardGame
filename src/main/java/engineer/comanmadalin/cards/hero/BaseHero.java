package engineer.comanmadalin.cards.hero;

import engineer.comanmadalin.cards.BaseCard;

import java.util.ArrayList;

public abstract class BaseHero extends BaseCard {
    public BaseHero(int mana, int health, String description, ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
        health = 30;
    }

    public abstract void ability();
}
