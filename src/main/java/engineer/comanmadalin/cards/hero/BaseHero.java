package engineer.comanmadalin.cards.hero;

import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.game.Game;

import java.util.ArrayList;

public abstract class BaseHero extends BaseCard {
    public BaseHero(final int mana, int health, final String description,
                    final ArrayList<String> colors, final String name) {
        super(mana, health, description, colors, name);
        health = 30;
    }

    public abstract void ability(Game game, int affectedRow);
}
