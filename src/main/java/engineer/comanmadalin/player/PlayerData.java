package engineer.comanmadalin.player;

import engineer.comanmadalin.cards.Deck;
import engineer.comanmadalin.cards.hero.BaseHero;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The `PlayerData` class holds information about a player's decks,
 * the index of the deck used for each game, and the heroes associated with the player.
 * NOTE: This class only holds the data that it got from the input json and will suffer no
 * modification.
 */
@Getter
@Setter
public final class PlayerData {
    private List<Deck> decks = new ArrayList<>();
    private List<Integer> deckIndexForGame = new ArrayList<>();
    private List<BaseHero> hero = new ArrayList<>();
    private int wins = 0;

    /**
     * Increase wins.
     */
    public void increaseWins() {
        wins++;
    }
}
