package MonopolySimulator;

class Station extends Property {

    private MonopolyBoard board;

    Station(UIHandler uih, Banker bank, MonopolyBoard board, String name) {
        super(uih, bank, name, 200, new int[] {25, 50, 100, 200});

        this.board = board;
    }

    void checkGroup() {
        int count = 0;
        for (int i = 5; i < 36; i += 10) {
            Station otherStation = (Station) board.getPlace(i);

            if (otherStation.getOwner() == getOwner())
                count++;
        }

        for (int i = 5; i < 36; i += 10) {
            Station otherStation = (Station) board.getPlace(i);

            if (otherStation.getOwner() == getOwner())
                otherStation.setDevelopmentLevel(count);
        }
    }
}
