package engineer.comanmadalin.cards.hero.specific;

import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.game.Game;

import java.util.ArrayList;

public class LordRoyce extends BaseHero {
    public LordRoyce(final int mana, final int health, final String description,
                     final ArrayList<String> colors, final String name) {
        super(mana, health, description, colors, name);
    }

    @Override
    public void ability(final Game game, final int affectedRow) {
        game.getBoard().get(affectedRow).forEach((minionCard -> minionCard.setIsFrozen(true)));
    }
}
