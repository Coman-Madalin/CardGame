package engineer.comanmadalin.cards.hero.specific;

import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.game.Game;

import java.util.ArrayList;

public class LordRoyce extends BaseHero {
    public LordRoyce(int mana, int health, String description, ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
    }

    @Override
    public void ability(Game game, int affectedRow) {
        game.getBoard().get(affectedRow).forEach((minionCard -> minionCard.setIsFrozen(true)));
    }
}
