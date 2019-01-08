package MonopolySimulator;

class Service extends Property {

    Service(UIHandler uih, Banker bank, String name) {
        super(uih, bank, name, 150, new int[] {4, 10});
    }

    int getPrice(int roll) {
        return rents[developmentLevel] * roll;
    }

    void checkGroup() {

    }
}
