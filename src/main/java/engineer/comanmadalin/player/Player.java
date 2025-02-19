package engineer.comanmadalin.player;

import engineer.comanmadalin.cards.Deck;
import engineer.comanmadalin.cards.hero.BaseHero;
import lombok.Getter;

/**
 * The `Player` class holds information about a player's deck, hand and manaCost associated with
 * the player.
 * NOTE: This class is the one that will be changed as particular games are played.
 */
@Getter
public final class Player {
    private final Deck hand = new Deck();
    private final Deck deck;
    private final BaseHero hero;
    private int mana = 0;

    /**
     * Instantiates a new Player.
     *
     * @param deck the deck
     * @param hero the hero
     */
    public Player(final Deck deck, final BaseHero hero) {
        this.deck = deck;
        this.hero = hero;
    }

    /**
     * Add mana.
     *
     * @param manaToAdd the mana to add
     */
    public void addMana(final int manaToAdd) {
        this.mana += manaToAdd;
    }
}
