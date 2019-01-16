package MonopolySimulator.Players;

import MonopolySimulator.Banker;
import MonopolySimulator.Property;
import MonopolySimulator.Street;

public abstract class Player {

    private int id;
    private String name;
    Banker bank;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void assignBanker(Banker bank) {
        this.bank = bank;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract boolean propertyOfferHandler(Property p);

    public abstract void cantPayHandler(int amount);

    public abstract int jailExitHandler();

    public abstract boolean fineOrChanceHandler();

    public abstract int houseBuyingHandler(Street s);
}
