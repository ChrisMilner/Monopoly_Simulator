package MonopolySimulator;

import MonopolySimulator.Players.Player;

import java.util.ArrayList;
import java.util.Random;

class MonopolyGame {

    private UIHandler uih;
    private ArrayList<Player> activePlayers;
    private ArrayList<Player> bankruptPlayers;

    private MonopolyBoard board;
    private Banker banker;
    private Random rand;
    private int roundNum;


    MonopolyGame(UIHandler uih, ArrayList<Player> players) {
        this.uih = uih;
        this.activePlayers = new ArrayList<>(players);
        bankruptPlayers = new ArrayList<>();

        banker = new Banker(uih, this);
        board = new MonopolyBoard(uih, players, banker);

        for (Player p : players) {
            banker.registerPlayer(p.getID(), 1500);
            p.assignBanker(banker);
        }

        rand = new Random();
        roundNum = 1;
    }

    void play() {
        uih.newGame(activePlayers.size());

        while (!winCondition()) {
            uih.newRound(roundNum);
            playRound();
            roundNum++;
        }
    }

    private void playRound() {
        for (Player p : activePlayers) {
            uih.playerTurn(p.getName());

            int roll1, roll2;
            int doubleCount = 0;
            do {
                roll1 = 0;
                roll2 = 0;

                if (!isInJail(p.getID())) {
                    roll1 = rand.nextInt(6) + 1;
                    roll2 = rand.nextInt(6) + 1;
                    uih.rolled(roll1, roll2);

                    if (roll1 == roll2) {
                        doubleCount++;

                        uih.doubleRoll(doubleCount);

                        if (doubleCount >= 3) {
                            uih.thirdDoubleRoll();
                            board.movePlayerTo(p, 30, false);
                        }
                    }

                    board.movePlayer(p, roll1 + roll2);
                }

                board.executeActionOnPlayer(p, roll1 + roll2);
            } while (roll1 == roll2 && roll1 != 0);

            // TODO: Negotiation
        }
    }

    private boolean winCondition() {
        // TODO: Add Proper win condition
        return (roundNum > 20);
    }

    void bankruptPlayer(Player p) {
        bankruptPlayers.add(p);
        activePlayers.remove(p);

        // TODO: Clean up their property
    }

    private boolean isInJail(int id) {
        return board.getJail().prisonerDurations.containsKey(id);
    }

}
