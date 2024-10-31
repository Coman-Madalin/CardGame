package engineer.comanmadalin.deck;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public final class PlayerDecks {
    private ArrayList<Deck> decks;

    public PlayerDecks() {
        decks = new ArrayList<>();
    }

    public void setDecks(int numberOfDecks, int numberOfCardsPerDeck) {
        decks = new ArrayList<>(numberOfDecks);
        for (int i = 0; i < numberOfDecks; i++) {
            decks.add(new Deck(numberOfCardsPerDeck));
        }
    }
}
