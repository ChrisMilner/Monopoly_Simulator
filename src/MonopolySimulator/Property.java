package MonopolySimulator;

import MonopolySimulator.Players.Player;

public abstract class Property extends BoardPosition {

    private int price;
    private int[] rents;
    private Banker bank;

    private Player owner;
    private int mortgage;
    private int developmentLevel;

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

            int rent = getRent(roll);

            int newBalance = bank.transaction(p, owner, rent);
            uih.paidRent(rent, newBalance);
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

    public int getRent(int roll) {
        return rents[developmentLevel];
    }

    public int getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

    void removeOwner() {
        owner = null;
    }

    public int getMortgage() {
        return mortgage;
    }

    int getDevelopmentLevel() {
        return developmentLevel;
    }

    void setDevelopmentLevel(int level) {
        developmentLevel = level;
    }

    int[] getRents() {
        return rents;
    }

    abstract void checkGroup();

}
