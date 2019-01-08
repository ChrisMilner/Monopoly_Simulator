package MonopolySimulator;

import MonopolySimulator.Players.DefaultPlayer;
import MonopolySimulator.Players.Player;

import java.util.ArrayList;

public class MonopolySimulator {

    public static void main(String[] args) {
        CLIHandler uih = new CLIHandler();
        uih.startSimulator();

        int playerNum = uih.getPlayerNum();
        ArrayList<Player> players = new ArrayList<>();

        for (int i = 0; i < playerNum; i++) {
            players.add(new DefaultPlayer(i, "Player " + (i + 1)));
        }

        MonopolyGame game = new MonopolyGame(uih, players);

        game.play();
    }

}
