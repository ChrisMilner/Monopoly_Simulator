package MonopolySimulator;

import MonopolySimulator.Players.Player;

abstract class BoardPosition {

    UIHandler uih;
    String name;

    BoardPosition(UIHandler uih, String name) {
        this.uih = uih;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract void action(Player p, int roll);
}
