package engineer.comanmadalin.deck;

import engineer.comanmadalin.cards.BaseCard;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class Deck implements Cloneable {
    private ArrayList<BaseCard> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    public Deck(int numberOfCards) {
        cards = new ArrayList<>(numberOfCards);
    }

    @Override
    public Deck clone() {
        try {
            Deck clone = (Deck) super.clone();
            clone.cards = new ArrayList<>(cards.size());
            for (BaseCard card : cards) {
                clone.cards.add(card.clone());
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
