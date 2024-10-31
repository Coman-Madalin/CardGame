package engineer.comanmadalin;

import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.deck.PlayerDecks;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class Player {
    private PlayerDecks decks;
    private ArrayList<Integer> deckIndexForGame;
    private ArrayList<BaseHero> hero;

    public Player() {
        decks = new PlayerDecks();
        deckIndexForGame = new ArrayList<>();
        hero = new ArrayList<>();
    }
}
