package engineer.comanmadalin.cards.minion.specials;

import engineer.comanmadalin.cards.minion.BaseMinionCard;

import java.util.List;


/**
 * The type Base special card.
 */
public abstract class BaseSpecialCard extends BaseMinionCard {
    /**
     * Instantiates a new Base special card.
     *
     * @param mana        the mana
     * @param health      the health
     * @param description the description
     * @param colors      the colors
     * @param name        the name
     */
    public BaseSpecialCard(final int mana, final int health, final String description,
                           final List<String> colors, final String name) {
        super(mana, health, description, colors, name);
    }

    /**
     * Ability.
     *
     * @param target the target
     */
    public abstract void ability(BaseMinionCard target);

    public String canUseAbility(final int targetedPlayerID, final int currentPlayerID,
                                final boolean isTargetedCardTank, final boolean areThereTanks) {
        if (targetedPlayerID == currentPlayerID) {
            return "Attacked card does not belong to the enemy.";
        }

        if (!isTargetedCardTank && areThereTanks) {
            return "Attacked card is not of type 'Tank'.";
        }

        return null;
    }
}
