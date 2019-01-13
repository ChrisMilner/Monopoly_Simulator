package MonopolySimulator;

class Service extends NonStreet {

    Service(UIHandler uih, Banker bank, MonopolyBoard board, String name) {
        super(uih, bank, board, name, 150, new int[] {4, 10});
    }

    public int getRent(int roll) {
        return getRents()[getDevelopmentLevel()] * roll;
    }

    void checkGroup() {
        int[] services = {MonopolyBoard.ELECTRIC_COMPANY, MonopolyBoard.WATER_WORKS};

        updateDevelopmentLevels(services);
    }
}
