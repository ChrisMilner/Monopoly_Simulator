package MonopolySimulator;

import MonopolySimulator.Players.Player;

public abstract class Property extends BoardPosition {

    public int price;
    public int[] rents;
    private Banker bank;

    private Player owner;
    public int mortgage;
    public int developmentLevel;

    Property(UIHandler uih, Banker bank, String name, int price, int[] rents) {
        super(uih, name);

        this.bank = bank;
        this.price = price;
        this.rents = rents;

        owner = null;
        mortgage = price / 2;
        developmentLevel = 0;
    }

    void action(Player p, int roll) {
        if (owner == null) {
            uih.propertyOfferMade(name);

            if (p.propertyOfferHandler(this)) {
                uih.propertyOfferAccepted();
                buy(p);
            } else {
                uih.propertyOfferDeclined();
            }
        } else if (owner.getID() == p.getID()) {
            uih.alreadyOwnProperty(name);
        } else {
            uih.propertyRented();

            int price = getPrice(roll);

            int newBalance = bank.transaction(p, owner, price);
            uih.paidRent(price, newBalance);
        }
    }

    private void buy(Player p) {
        int playerBalance = bank.getBalance(p.getID());

        if (price < playerBalance) {
            int newBalance = bank.alterBalance(p, -price);
            uih.propertySold(name, price, newBalance);

            owner = p;

            checkGroup();
        } else {
            uih.insufficientFunds(price, playerBalance);
        }
    }

    int getPrice(int roll) {
        return rents[developmentLevel];
    }

    abstract void checkGroup();
}
