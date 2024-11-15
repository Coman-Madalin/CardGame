package engineer.comanmadalin.deck;

import engineer.comanmadalin.cards.BaseCard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Deck implements Cloneable {
    private ArrayList<BaseCard> cards = new ArrayList<>();

    @Override
    public Deck clone() {
        try {
            final Deck clone = (Deck) super.clone();
            clone.cards = new ArrayList<>(cards.size());
            for (final BaseCard card : cards) {
                clone.cards.add(card.clone());
            }
            return clone;
        } catch (final CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
