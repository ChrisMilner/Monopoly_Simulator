package MonopolySimulator;

class Street extends Property {

    // TODO: Add colour groups
    // TODO: Add House Cost

    Street(UIHandler uih, Banker bank, String name, int price, int[] rents) {
        super(uih, bank, name, price, rents);
    }

    void checkGroup() {
        // TODO: Handle Colour Groups
    }
}
