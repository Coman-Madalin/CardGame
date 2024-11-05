package engineer.comanmadalin.player;

import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.deck.Deck;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The `Player` class holds information about a player's deck, hand and manaCost associated with
 * the player.
 * NOTE: This class is the one that will be changed as particular games are played.
 */
@Getter
@Setter
@ToString
public class Player {
    Deck hand;
    Deck deck;
    BaseHero hero;
    int mana;

    public Player(Deck deck, BaseHero hero) {
        hand = new Deck();
        this.deck = deck;
        this.hero = hero;
        mana = 0;
    }

    public void addMana(int manaToAdd) {
        this.mana += manaToAdd;
    }
}
