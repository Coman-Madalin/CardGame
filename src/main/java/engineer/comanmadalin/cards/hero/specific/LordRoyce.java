package engineer.comanmadalin.cards.hero.specific;

import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.game.Game;

import java.util.List;


/**
 * The type Lord royce.
 */
public final class LordRoyce extends BaseHero {
    /**
     * Instantiates a new Lord royce.
     *
     * @param mana        the mana
     * @param health      the health
     * @param description the description
     * @param colors      the colors
     * @param name        the name
     */
    public LordRoyce(final int mana, final int health, final String description,
                     final List<String> colors, final String name) {
        super(mana, health, description, colors, name);
    }

    @Override
    public void ability(final Game game, final int affectedRow) {
        game.getBoard().get(affectedRow).forEach((minionCard -> minionCard.setIsFrozen(true)));
    }
}
