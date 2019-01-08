package MonopolySimulator;

import MonopolySimulator.Players.Player;

class Empty extends BoardPosition {

    Empty(UIHandler uih, String name) {
        super(uih, name);
    }

    void action(Player p, int roll) {
        uih.noAction(name);
    }
}
