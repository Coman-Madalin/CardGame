package engineer.comanmadalin.player;

import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.deck.Deck;
import lombok.Getter;
import lombok.Setter;

/**
 * The `Player` class holds information about a player's deck, hand and manaCost associated with
 * the player.
 * NOTE: This class is the one that will be changed as particular games are played.
 */
@Getter
@Setter
public class Player {
    private Deck hand = new Deck();
    private Deck deck;
    private BaseHero hero;
    private int mana = 0;

    public Player(Deck deck, BaseHero hero) {
        this.deck = deck;
        this.hero = hero;
    }

    public void addMana(int manaToAdd) {
        this.mana += manaToAdd;
    }
}
