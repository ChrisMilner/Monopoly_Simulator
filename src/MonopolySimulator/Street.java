package MonopolySimulator;

public class Street extends Property {

    private MonopolyBoard board;
    private boolean partOfFullGroup;
    private int houseCost;
    private int[] group;

    Street(UIHandler uih, Banker bank, MonopolyBoard board, String name, int price, int[] rents, int houseCost, int[] group) {
        super(uih, bank, name, price, rents);

        this.board = board;
        partOfFullGroup = false;
        this.houseCost = houseCost;
        this.group = group;
    }

    void checkGroup() {
        for (int i : group) {
            if (getOwner() == null || ((Street) board.getPlace(i)).getOwner() == null||
                    getOwner().getID() != ((Street) board.getPlace(i)).getOwner().getID()) {

                setGroupFullTo(false);
                return;
            }
        }

        setGroupFullTo(true);
    }

    private void setGroupFullTo(boolean value) {
        for (int j : group) {
            ((Street) board.getPlace(j)).setPartOfFullGroup(value);
        }
    }

    void addHouse() {
        setDevelopmentLevel(getDevelopmentLevel() + 1);

        if (getDevelopmentLevel() < 5)
            uih.boughtHouse(name);
        else
            uih.boughtHotel(name);
    }

    public boolean isPartOfFullGroup() {
        return partOfFullGroup;
    }

    void setPartOfFullGroup(boolean val) {
        partOfFullGroup = val;
    }

    public int getHousePrice() {
        return houseCost;
    }
}
