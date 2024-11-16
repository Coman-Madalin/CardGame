package engineer.comanmadalin.cards.hero.specific;

import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;

import java.util.List;

/**
 * The type Empress thorina.
 */
public final class EmpressThorina extends BaseHero {
    /**
     * Instantiates a new Empress thorina.
     *
     * @param mana        the mana
     * @param health      the health
     * @param description the description
     * @param colors      the colors
     * @param name        the name
     */
    public EmpressThorina(final int mana, final int health, final String description,
                          final List<String> colors, final String name) {
        super(mana, health, description, colors, name);
    }

    @Override
    public void ability(final Game game, final int affectedRow) {
        int maxHealth = 0;
        int maxHealthCardIndex = -1;

        final List<BaseMinionCard> row = game.getBoard().get(affectedRow);
        for (int i = 0; i < row.size(); i++) {
            if (maxHealth < row.get(i).getHealth()) {
                maxHealth = row.get(i).getHealth();
                maxHealthCardIndex = i;
            }
        }

        row.remove(maxHealthCardIndex);
    }
}
