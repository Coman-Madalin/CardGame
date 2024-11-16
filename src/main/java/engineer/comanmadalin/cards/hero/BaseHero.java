package engineer.comanmadalin.cards.hero;

import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.game.Game;

import java.util.List;


/**
 * The type Base hero.
 */
public abstract class BaseHero extends BaseCard {
    /**
     * Instantiates a new Base hero.
     *
     * @param mana        the mana
     * @param health      the health
     * @param description the description
     * @param colors      the colors
     * @param name        the name
     */
    public BaseHero(final int mana, final int health, final String description,
                    final List<String> colors, final String name) {
        super(mana, health, description, colors, name);
    }

    /**
     * Ability.
     *
     * @param game        the game
     * @param affectedRow the affected row
     */
    public abstract void ability(Game game, int affectedRow);
}
