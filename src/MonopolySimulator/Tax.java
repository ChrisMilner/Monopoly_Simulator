package MonopolySimulator;

import MonopolySimulator.Players.Player;

class Tax extends BoardPosition {

    private Banker bank;
    private int amount;

    Tax(UIHandler uih, Banker bank, String name, int amount) {
        super(uih, name);

        this.bank = bank;
        this.amount = amount;
    }

    void action(Player p, int roll) {
        uih.taxed(amount);

        bank.alterBalance(p, -amount);
    }
}
