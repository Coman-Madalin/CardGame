package engineer.comanmadalin.player;

import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.deck.PlayerDecks;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * The `PlayerData` class holds information about a player's decks, the index of the deck used for each game,
 * and the heroes associated with the player.
 * NOTE: This class only holds the data that it got from the input json and will suffer no
 * modification.
 */
@Getter
@Setter
@ToString
public class PlayerData {
    private PlayerDecks decks;
    private ArrayList<Integer> deckIndexForGame;
    private ArrayList<BaseHero> hero;

    public PlayerData() {
        decks = new PlayerDecks();
        deckIndexForGame = new ArrayList<>();
        hero = new ArrayList<>();
    }
}
