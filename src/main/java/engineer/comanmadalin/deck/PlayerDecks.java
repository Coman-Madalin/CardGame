package engineer.comanmadalin.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

/**
 * The type Player decks.
 */
@Getter
@Setter
@NoArgsConstructor
public final class PlayerDecks {
    private ArrayList<Deck> decks = new ArrayList<>();
}
