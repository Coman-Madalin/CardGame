package engineer.comanmadalin.player;

import engineer.comanmadalin.deck.Deck;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The `Player` class holds information about a player's deck, hand and mana associated with
 * the player.
 * NOTE: This class is the one that will be changed as particular games are played.
 */
@Getter
@Setter
@ToString
public class Player {
    Deck hand;
    Deck deck;
    int mana;

    public Player() {
        hand = new Deck();
        deck = new Deck();
        mana = 0;
    }

    public void addMana(int manaToAdd) {
        this.mana += manaToAdd;
    }
}
