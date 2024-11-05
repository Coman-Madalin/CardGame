package engineer.comanmadalin.game;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.deck.Deck;
import engineer.comanmadalin.player.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Math.min;

@Getter
@Setter
@ToString
public final class Game {
    private static int GAME_NR = 0;
    Player[] players;
    private int roundNumber;
    private List<List<BaseMinionCard>> board;
    private Boolean nextEndTurnWillEndRound;
    private int playerIDTurn;

    private GameConditions gameConditions;
    private List<BaseAction> actions;

    public Game() {
        gameConditions = new GameConditions();
        actions = new ArrayList<>();
    }

    private void playActions() {
        for (BaseAction action : actions) {
            action.run(this);
        }
    }

    public void startOfRound() {
        for (int i = 0; i < Input.getMAX_PLAYERS(); i++) {
            players[i].getHand().getCards().add(players[i].getDeck().getCards().get(0));

            players[i].getDeck().getCards().remove(0);
            players[i].addMana(min(roundNumber + 1, 10));
        }

        roundNumber += 1;
    }

    public void setBoard() {
        board = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            board.add(new ArrayList<>(5));
        }
    }

    public void runGame() {
        setBoard();
        roundNumber = 0;
        playerIDTurn = gameConditions.getStartingPlayer() - 1;
        nextEndTurnWillEndRound = false;
        Input inputInstance = Input.getINSTANCE();
        players = new Player[Input.getMAX_PLAYERS()];

        for (int i = 0; i < Input.getMAX_PLAYERS(); i++) {
            Deck playerDeck = inputInstance.getPlayersData()[i].getDecks().getDecks().get(inputInstance.getPlayersData()[i].getDeckIndexForGame().get(GAME_NR)).clone();
            Collections.shuffle(playerDeck.getCards(), new Random(gameConditions.getShuffleSeed()));

            BaseHero playerHero = inputInstance.getPlayersData()[i].getHero().get(GAME_NR);

            players[i] = new Player(playerDeck, playerHero);
        }

        startOfRound();
        playActions();
    }
}
