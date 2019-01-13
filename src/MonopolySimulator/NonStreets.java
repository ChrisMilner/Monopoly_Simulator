package MonopolySimulator;

import java.util.HashMap;

abstract class NonStreet extends Property {

    MonopolyBoard board;

    NonStreet(UIHandler uih, Banker bank, MonopolyBoard board, String name, int price, int[] rents) {
        super(uih, bank, name, price, rents);

        this.board = board;
    }

    void updateDevelopmentLevels(int[] otherProps) {
        HashMap<Integer, Integer> ownerCounts = new HashMap<>();

        for (int i : otherProps) {
            NonStreet otherProp = (NonStreet) board.getPlace(i);

            if (otherProp.getOwner() == null)
                continue;

            int ownerID = otherProp.getOwner().getID();

            if (ownerCounts.containsKey(ownerID)) {
                ownerCounts.replace(ownerID, ownerCounts.get(ownerID) + 1);
            } else {
                ownerCounts.put(ownerID, 1);
            }
        }

        for (int i : otherProps) {
            NonStreet otherProp = (NonStreet) board.getPlace(i);

            if (otherProp.getOwner() == null)
                continue;

            otherProp.setDevelopmentLevel(ownerCounts.get(otherProp.getOwner().getID()));
        }

    }
}
