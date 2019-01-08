package MonopolySimulator.Players;

import MonopolySimulator.Property;

public class DefaultPlayer extends Player {

    public DefaultPlayer(int id, String name) {
        super(id, name);
    }

    public boolean propertyOfferHandler(Property p) {
        return (p.price < bank.getBalance(this.getID()));
    }

    public void cantPayHandler(int amount) {
        // TODO: Implement reasonable AI response

        return;
    }

    public int jailExitHandler() {
        // TODO: Implement reasonable AI response

        return 2;
    }

    public boolean fineOrChanceHandler() {
        return false;
    }

}
