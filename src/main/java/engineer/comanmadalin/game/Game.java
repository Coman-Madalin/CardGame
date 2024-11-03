package engineer.comanmadalin.game;

import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.actions.specific.GetPlayerDeck;
import engineer.comanmadalin.actions.specific.GetPlayerHero;
import engineer.comanmadalin.actions.specific.GetPlayerTurn;
import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.deck.Deck;
import engineer.comanmadalin.player.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

@Getter
@Setter
@ToString
public final class Game {
    private static final HashMap<Class<?>, String> actionToParameters = new HashMap<>() {{
        put(GetPlayerDeck.class, "players");
        put(GetPlayerHero.class, "players");
        put(GetPlayerTurn.class, "round_nr");
    }};
    private static int GAME_NR = 0;
    private int roundNumber = 0;
    private GameConditions gameConditions;
    private ArrayList<BaseAction> actions;

    public Game() {
        gameConditions = new GameConditions();
        actions = new ArrayList<>();
    }

    private void playersAction(BaseAction action, Player[] players) {
        action.run((Object) players);
    }

    private void roundNrAction(BaseAction action) {
        int currentRound = gameConditions.getStartingPlayer() - 1 + roundNumber;
        currentRound %= 2;
        currentRound += 1;
        action.run(currentRound);
    }

    private void playActions(Player[] players) {
        for (BaseAction action : actions) {
            System.out.println(action.getClass());
            String specificActionParameters = actionToParameters.get(action.getClass());
            switch (specificActionParameters) {
                case "players":
                    playersAction(action, players);
                    break;
                case "round_nr":
                    roundNrAction(action);
                    break;
            }
        }
    }

    private void playRound(Player[] players) {
        for (int i = 0; i < Input.getMAX_PLAYERS(); i++) {
            players[i].getHand().getCards().add(players[i].getDeck().getCards().get(roundNumber));

            players[i].getDeck().getCards().remove(0);
            players[i].addMana(roundNumber + 1);
        }
    }

    public void runGame() {
        Input inputInstance = Input.getINSTANCE();

        Player[] players = new Player[Input.getMAX_PLAYERS()];

        for (int i = 0; i < Input.getMAX_PLAYERS(); i++) {
            Deck playerDeck = inputInstance.getPlayersData()[i].getDecks().getDecks().get(inputInstance.getPlayersData()[i].getDeckIndexForGame().get(GAME_NR)).clone();
            Collections.shuffle(playerDeck.getCards(), new Random(gameConditions.getShuffleSeed()));

            BaseHero playerHero = inputInstance.getPlayersData()[i].getHero().get(GAME_NR);

            players[i] = new Player(playerDeck, playerHero);
        }


        playRound(players);
        playActions(players);
    }
}
