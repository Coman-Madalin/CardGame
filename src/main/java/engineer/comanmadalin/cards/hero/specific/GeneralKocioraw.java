package engineer.comanmadalin.cards.hero.specific;

import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.game.Game;

import java.util.List;


/**
 * The type General kocioraw.
 */
public final class GeneralKocioraw extends BaseHero {
    /**
     * Instantiates a new General kocioraw.
     *
     * @param mana        the mana
     * @param health      the health
     * @param description the description
     * @param colors      the colors
     * @param name        the name
     */
    public GeneralKocioraw(final int mana, final int health, final String description,
                           final List<String> colors, final String name) {
        super(mana, health, description, colors, name);
    }

    // We subtract -1 because we want to add 1 attack damage to each unit on that row
    @Override
    public void ability(final Game game, final int affectedRow) {
        game.getBoard().get(affectedRow)
                .forEach((baseMinion) -> baseMinion.subtractAttackDamage(-1));
    }

    @Override
    public String canUseAbility(final int targetedPlayerID, final int currentPlayerID) {
        if (targetedPlayerID != currentPlayerID) {
            return "Selected row does not belong to the current player.";
        }
        return null;
    }
}
