package engineer.comanmadalin.player;

import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.deck.PlayerDecks;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

/**
 * The `PlayerData` class holds information about a player's decks, the index of the deck used for each game,
 * and the heroes associated with the player.
 * NOTE: This class only holds the data that it got from the input json and will suffer no
 * modification.
 */
@Getter
@Setter
@NoArgsConstructor
public class PlayerData {
    private PlayerDecks decks = new PlayerDecks();
    private ArrayList<Integer> deckIndexForGame = new ArrayList<>();
    private ArrayList<BaseHero> hero = new ArrayList<>();
    private int wins = 0;

    public void increaseWins(){
        wins++;
    }
}
