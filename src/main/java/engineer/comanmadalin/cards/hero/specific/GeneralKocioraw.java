package engineer.comanmadalin.cards.hero.specific;

import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.game.Game;

import java.util.ArrayList;

public class GeneralKocioraw extends BaseHero {
    public GeneralKocioraw(final int mana, final int health, final String description,
                           final ArrayList<String> colors, final String name) {
        super(mana, health, description, colors, name);
    }

    // We subtract -1 because we want to add 1 attack damage to each unit on that row
    @Override
    public void ability(final Game game, final int affectedRow) {
        game.getBoard().get(affectedRow).forEach((baseMinion) -> baseMinion.subtractAttackDamage(-1));
    }
}
