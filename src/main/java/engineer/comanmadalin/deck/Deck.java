package engineer.comanmadalin.deck;

import engineer.comanmadalin.cards.BaseCard;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class Deck {
    private ArrayList<BaseCard> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    public Deck(int numberOfCards) {
        cards = new ArrayList<>(numberOfCards);
    }

}
