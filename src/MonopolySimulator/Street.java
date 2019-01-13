package MonopolySimulator;

class Street extends Property {

    private MonopolyBoard board;
    private boolean partOfFullGroup;
    private int[] group;

    // TODO: Add colour groups
    // TODO: Add House Cost

    Street(UIHandler uih, Banker bank, MonopolyBoard board, String name, int price, int[] rents, int[] group) {
        super(uih, bank, name, price, rents);

        this.board = board;
        partOfFullGroup = false;
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

    boolean isPartOfFullGroup() {
        return partOfFullGroup;
    }

    void setPartOfFullGroup(boolean val) {
        partOfFullGroup = val;
    }
}
