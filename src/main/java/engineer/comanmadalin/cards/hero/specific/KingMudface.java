package engineer.comanmadalin.cards.hero.specific;

import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.game.Game;

import java.util.ArrayList;

/**
 * The type King mudface.
 */
public final class KingMudface extends BaseHero {
    /**
     * Instantiates a new King mudface.
     *
     * @param mana        the mana
     * @param health      the health
     * @param description the description
     * @param colors      the colors
     * @param name        the name
     */
    public KingMudface(final int mana, final int health, final String description,
                       final ArrayList<String> colors, final String name) {
        super(mana, health, description, colors, name);
    }

    @Override
    public void ability(final Game game, final int affectedRow) {
        game.getBoard().get(affectedRow).forEach((baseMinion) -> baseMinion.heal(1));
    }
}
