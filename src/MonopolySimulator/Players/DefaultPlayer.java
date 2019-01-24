package MonopolySimulator.Players;

import MonopolySimulator.Deal;
import MonopolySimulator.Property;
import MonopolySimulator.Street;

public class DefaultPlayer extends Player {

    public DefaultPlayer(int id, String name) {
        super(id, name);
    }

    public boolean propertyOfferHandler(Property p) {
        return (p.getPrice() < bank.getBalance(this.getID()));
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

    public int houseBuyingHandler(Street s) {
        // TODO: Implement reasonable AI response

        return 0;
    }

    public Deal[] negotiate() {
        // TODO: Implement reasonable AI response

        return new Deal[0];
    }

    public Deal handleDeal(Deal deal) {
        return null;
    }

}
