package engineer.comanmadalin.game;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.player.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@Getter
@Setter
@ToString
public final class Game {
    private static int GAME_NR = 0;
    private GameConditions gameConditions;
    private ArrayList<BaseAction> actions;

    public Game() {
        gameConditions = new GameConditions();
        actions = new ArrayList<>();
    }

    private void playActions(Player[] players) {
        for (BaseAction action : actions) {
            System.out.println(action);
            action.run((Object) players);
        }
    }

    private void playRound(Player[] players, int roundNumber) {
        for (int i = 0; i < Input.getMAX_PLAYERS(); i++) {
            players[i].getHand().getCards()
                    .add(players[i].getDeck().getCards().get(roundNumber));

            players[i].addMana(roundNumber + 1);
        }
    }

    public void runGame() {
        Input inputInstance = Input.getINSTANCE();

        Player[] players = new Player[Input.getMAX_PLAYERS()];

        for (int i = 0; i < Input.getMAX_PLAYERS(); i++) {
            players[i] = new Player();

            players[i].setDeck(inputInstance.getPlayersData()[i].getDecks().getDecks()
                    .get(inputInstance.getPlayersData()[i].getDeckIndexForGame().get(GAME_NR)).clone());
            Collections.shuffle(players[i].getDeck().getCards(),
                    new Random(gameConditions.getShuffleSeed()));
        }


        playRound(players, 0);
        playActions(players);
    }
}
