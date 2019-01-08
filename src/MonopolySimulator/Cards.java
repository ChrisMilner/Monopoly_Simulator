package MonopolySimulator;

import MonopolySimulator.Players.Player;

import java.util.LinkedList;

import static java.util.Collections.shuffle;

abstract class Cards extends BoardPosition {

    MonopolyBoard board;
    Banker bank;

    boolean containsGOOJFCard;
    LinkedList<Integer> order = new LinkedList<>();

    Cards(UIHandler uih, MonopolyBoard board, Banker bank, String name) {
        super(uih, name);

        this.board = board;
        this.bank = bank;

        containsGOOJFCard = true;
        initialiseOrder();
    }

    void action(Player p, int roll) {
        if (order.isEmpty())
            initialiseOrder();

        try {
            int cardNum = order.poll();
            cardAction(p, cardNum);
        } catch (NullPointerException e) {
            // This should be unreachable
            uih.exception(e);
        }
    }

    abstract void cardAction(Player p, int cardNum);

    private void initialiseOrder() {
        for (int i = 0; i < 16; i++) {
            if (i == 4 && !containsGOOJFCard)
                continue;

            order.add(i);
        }

        shuffle(order);
    }

    void reAddGOOJFCard() {
        containsGOOJFCard = true;
    }
}
