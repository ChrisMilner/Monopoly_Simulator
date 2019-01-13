package MonopolySimulator;

class Station extends NonStreet {

    Station(UIHandler uih, Banker bank, MonopolyBoard board, String name) {
        super(uih, bank, board, name, 200, new int[] {25, 50, 100, 200});
    }

    void checkGroup() {
        int[] stations = {MonopolyBoard.KINGS_CROSS_STATION, MonopolyBoard.MARYLEBONE_STATION,
                          MonopolyBoard.FENCHURCH_STATION, MonopolyBoard.LIVERPOOL_STATION};

        updateDevelopmentLevels(stations);
    }
}
