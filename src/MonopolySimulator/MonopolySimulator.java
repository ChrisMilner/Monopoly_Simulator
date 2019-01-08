package MonopolySimulator;

import MonopolySimulator.Players.*;

public class MonopolySimulator {

    public static void main(String[] args) {
        CLIHandler uih = new CLIHandler();
        uih.startSimulator();

        int playerNum = uih.getPlayerNum();
        Player[] players = new Player[playerNum];

        for (int i = 0; i < players.length; i++) {
            players[i] = new DefaultPlayer(i, "Player " + (i + 1));
        }

        MonopolyGame game = new MonopolyGame(uih, players);

        game.play();
    }

}
