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
    Player[] players = new Player[Input.getMAX_PLAYERS()];
    private int roundNumber = 0;
    private List<List<BaseMinionCard>> board;
    private Boolean nextEndTurnWillEndRound = false;
    private int playerIDTurn;

    private GameConditions gameConditions = new GameConditions();
    private List<BaseAction> actions = new ArrayList<>();

    private void printBoard() {
        System.out.println("Player2: " + players[1].getMana());
        System.out.println("Player1: " + players[0].getMana());

        for (List<BaseMinionCard> row : board) {
            for (BaseMinionCard baseMinionCard : row) {
                System.out.print(baseMinionCard.getName() + ":" + baseMinionCard.getManaCost() +
                        " " + baseMinionCard.getIsFrozen() + "|||");
            }
            System.out.println();
        }

        System.out.println("*****************************************");
    }

    public List<BaseMinionCard> findAllFrozenCards() {
        List<BaseMinionCard> result = new ArrayList<>();

        for (List<BaseMinionCard> row : board) {
            for (BaseMinionCard minion : row) {
                if (minion.getIsFrozen()) {
                    result.add(minion);
                }
            }
        }

        return result;
    }

    public Boolean checkForTank(int playerID) {
        List<BaseMinionCard> row = board.get(2 - playerID);
        for (BaseMinionCard minion : row) {
            if (minion.getIsTank()) {
                return true;
            }
        }

        return false;
    }

    public void unfreezePlayerCards() {
        int startingRow;
        if (playerIDTurn == 0) {
            startingRow = 2;
        } else {
            startingRow = 0;
        }

        for (int i = startingRow; i <= startingRow + 1; i++) {
            for (BaseMinionCard minionCard : board.get(i)) {
                minionCard.setIsFrozen(false);
            }
        }
    }

    private void endOfRound() {
        for (List<BaseMinionCard> row : board) {
            for (BaseMinionCard minionCard : row) {
                minionCard.setAttackedThisRound(false);
            }
        }

        for (Player player : players) {
            player.getHero().setAttackedThisRound(false);
        }
    }

    private void playActions() {
        for (BaseAction action : actions) {
            System.out.println("*****************************************");
            System.out.println(action.getClass());
            action.run(this);
            printBoard();
        }
    }

    public void startOfRound() {
        endOfRound();
        for (int i = 0; i < Input.getMAX_PLAYERS(); i++) {
            players[i].addMana(min(roundNumber + 1, 10));

            if (players[i].getDeck().getCards().isEmpty()) {
                continue;
            }

            players[i].getHand().getCards().add(players[i].getDeck().getCards().get(0));
            players[i].getDeck().getCards().remove(0);
        }

        roundNumber += 1;
    }

    public void setBoard() {
        board = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            board.add(new ArrayList<>(5));
        }
    }

    public void runGame(int gameNumber) {
        setBoard();
        playerIDTurn = gameConditions.getStartingPlayer() - 1;
        Input inputInstance = Input.getINSTANCE();

        for (int i = 0; i < Input.getMAX_PLAYERS(); i++) {
            Deck playerDeck = inputInstance.getPlayersData()[i].getDecks().getDecks()
                    .get(inputInstance.getPlayersData()[i].getDeckIndexForGame().get(gameNumber)).clone();
            Collections.shuffle(playerDeck.getCards(), new Random(gameConditions.getShuffleSeed()));

            BaseHero playerHero = inputInstance.getPlayersData()[i].getHero().get(gameNumber);

            players[i] = new Player(playerDeck, playerHero);
        }

        startOfRound();
        playActions();
    }
}
