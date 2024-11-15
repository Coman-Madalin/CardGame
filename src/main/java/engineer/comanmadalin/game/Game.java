package engineer.comanmadalin.game;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.deck.Deck;
import engineer.comanmadalin.player.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Math.min;

@Getter
@Setter
@NoArgsConstructor
public final class Game {
    private static final int NUMBER_OF_ROWS = 4;
    @Getter
    private static final int NUMBER_OF_ELEMENTS_PER_ROW = 5;
    private static final int MAXIMUM_MANA_INCREASE_ROUND = 10;
    private Player[] players = new Player[Input.getMAX_PLAYERS()];
    private int roundNumber = 0;
    private List<List<BaseMinionCard>> board;
    private Boolean nextEndTurnWillEndRound = false;
    private int playerIDTurn;
    private GameConditions gameConditions = new GameConditions();
    private List<BaseAction> actions = new ArrayList<>();

    public List<BaseMinionCard> findAllFrozenCards() {
        final List<BaseMinionCard> result = new ArrayList<>();

        for (final List<BaseMinionCard> row : board) {
            for (final BaseMinionCard minion : row) {
                if (minion.getIsFrozen()) {
                    result.add(minion);
                }
            }
        }

        return result;
    }

    public Boolean checkForTank(final int playerID) {
        final List<BaseMinionCard> row = board.get(2 - playerID);
        for (final BaseMinionCard minion : row) {
            if (minion.getIsTank()) {
                return true;
            }
        }

        return false;
    }

    public void unfreezePlayerCards() {
        final int startingRow;
        if (playerIDTurn == 0) {
            startingRow = 2;
        } else {
            startingRow = 0;
        }

        for (int i = startingRow; i <= startingRow + 1; i++) {
            for (final BaseMinionCard minionCard : board.get(i)) {
                minionCard.setIsFrozen(false);
            }
        }
    }

    private void endOfRound() {
        for (final List<BaseMinionCard> row : board) {
            for (final BaseMinionCard minionCard : row) {
                minionCard.setAttackedThisRound(false);
            }
        }

        for (final Player player : players) {
            player.getHero().setAttackedThisRound(false);
        }
    }

    private void playActions() {
        for (final BaseAction action : actions) {
            action.run(this);
        }
    }

    public void startOfRound() {
        endOfRound();
        for (int i = 0; i < Input.getMAX_PLAYERS(); i++) {
            players[i].addMana(min(roundNumber + 1, MAXIMUM_MANA_INCREASE_ROUND));

            if (players[i].getDeck().getCards().isEmpty()) {
                continue;
            }

            players[i].getHand().getCards().add(players[i].getDeck().getCards().get(0));
            players[i].getDeck().getCards().remove(0);
        }

        roundNumber += 1;
    }

    public void setBoard() {
        board = new ArrayList<>(NUMBER_OF_ROWS);
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            board.add(new ArrayList<>(NUMBER_OF_ELEMENTS_PER_ROW));
        }
    }

    public void runGame(final int gameNumber) {
        setBoard();
        playerIDTurn = gameConditions.getStartingPlayer() - 1;
        final Input inputInstance = Input.getInstance();

        for (int i = 0; i < Input.getMAX_PLAYERS(); i++) {
            final Deck playerDeck = inputInstance.getPlayersData()[i].getDecks().getDecks()
                    .get(inputInstance.getPlayersData()[i].getDeckIndexForGame()
                            .get(gameNumber)).clone();
            Collections.shuffle(playerDeck.getCards(), new Random(gameConditions.getShuffleSeed()));

            final BaseHero playerHero = inputInstance.getPlayersData()[i].getHero().get(gameNumber);

            players[i] = new Player(playerDeck, playerHero);
        }

        startOfRound();
        playActions();
    }
}
