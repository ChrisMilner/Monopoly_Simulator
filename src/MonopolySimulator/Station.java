package MonopolySimulator;

class Station extends Property {

    Station(UIHandler uih, Banker bank, String name) {
        super(uih, bank, name, 200, new int[] {25, 50, 100, 200});
    }

    void checkGroup() {
        // TODO: Handle Station Development Level
    }
}
