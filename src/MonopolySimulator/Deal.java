package MonopolySimulator;

import MonopolySimulator.Players.Player;

public class Deal {

    private Player to;
    private Player from;
    private DealType type;

    private int money;      // Amount that the receiver will receive
    private int GOOJFCards;
    private Property[] givingProperties;        // The properties that the deal receiver will give
    private Property[] receivingProperties;     // The properties that the deal receiver will receive

    public Deal(Player to, Player from, DealType type, int money, int GOOJFCards,
                Property[] givingProperties, Property[] receivingProperties) {

        this.to = to;
        this.from = from;
        this.type = type;
        this.money = money;
        this.GOOJFCards = GOOJFCards;
        this.givingProperties = givingProperties;
        this.receivingProperties = receivingProperties;
    }

    public Deal(Player to, Player from, DealType type) {
        this(to, from, type, 0, 0, new Property[0], new Property[0]);
    }

    public Deal() {
        this(null, null, DealType.REJECT);
    }

    public void setTo(Player to) {
        this.to = to;
    }

    public Player getTo() {
        return to;
    }

    public Player getFrom() {
        return from;
    }

    public void setFrom(Player from) {
        this.from = from;
    }

    public DealType getType() {
        return type;
    }

    public void setType(DealType type) {
        this.type = type;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getGOOJFCards() {
        return GOOJFCards;
    }

    public void setGOOJFCards(int GOOJFCards) {
        this.GOOJFCards = GOOJFCards;
    }

    public Property[] getGivingProperties() {
        return givingProperties;
    }

    public void setGivingProperties(Property[] givingProperties) {
        this.givingProperties = givingProperties;
    }

    public Property[] getReceivingProperties() {
        return receivingProperties;
    }

    public void setReceivingProperties(Property[] receivingProperties) {
        this.receivingProperties = receivingProperties;
    }

    public boolean equals(Deal other) {
        return  this.to.getID() == other.getTo().getID() &&
                this.from.getID() == other.getFrom().getID() &&
                this.type == other.getType() &&
                this.money == other.getMoney() &&
                this.GOOJFCards == other.getGOOJFCards() &&
                this.givingProperties == other.getGivingProperties() &&
                this.receivingProperties == other.getReceivingProperties();
    }
}
