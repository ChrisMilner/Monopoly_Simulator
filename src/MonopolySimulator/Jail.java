package MonopolySimulator;

import MonopolySimulator.Players.Player;

import java.util.HashMap;
import java.util.Random;

class Jail extends BoardPosition {

    private MonopolyBoard board;
    private Banker bank;

    private Random rand;
    HashMap<Integer, Integer> prisonerDurations;

    int chanceGOOJFOwner;
    int chestGOOJFOwner;

    Jail(UIHandler uih, MonopolyBoard board, Banker bank) {
        super(uih, "Jail");

        this.board = board;
        this.bank = bank;

        rand = new Random();
        prisonerDurations = new HashMap<>();

        chanceGOOJFOwner = -1;
        chestGOOJFOwner = -1;
    }

    void action(Player p, int roll) {
        if (prisonerDurations.containsKey(p.getID())) {
            uih.stillInJail();

            int roll1 = 0;
            int roll2 = 0;

            int choice = p.jailExitHandler();
            switch (choice) {
                case 1:
                    uih.choseRollForExit();

                    roll1 = rand.nextInt(6) + 1;
                    roll2 = rand.nextInt(6) + 1;

                    if (roll1 == roll2) {
                        uih.succeededRollForExit(roll1);

                        prisonerDurations.remove(p.getID());
                        board.movePlayerTo(p, 10, false);
                        board.movePlayer(p, roll1 + roll2);
                    } else {
                        uih.failedRollForExit(roll1, roll2);
                    }

                    break;
                case 2:
                    uih.chosePayForExit();

                    if (bank.getBalance(p.getID()) >= 50) {
                        payToExit(p);
                    } else {
                        uih.cantAffordExit();
                    }

                    break;
                case 3:
                    uih.choseCardForExit();

                    if (p.getID() == chanceGOOJFOwner || p.getID() == chestGOOJFOwner) {
                        if (p.getID() == chanceGOOJFOwner) {
                            chanceGOOJFOwner = -1;
                            ((Chance) board.getPlace(7)).reAddGOOJFCard();
                        } else if (p.getID() == chestGOOJFOwner) {
                            chestGOOJFOwner = -1;
                            ((CommunityChest) board.getPlace(2)).reAddGOOJFCard();
                        }

                        prisonerDurations.remove(p.getID());
                        board.movePlayerTo(p, 10, false);
                        board.movePlayer(p, roll1 + roll2);
                    } else {
                        uih.noCardAvailable();
                    }

                    break;
            }

            if (prisonerDurations.containsKey(p.getID())) {
                if (prisonerDurations.get(p.getID()) == 2) {
                    uih.finishedJailSentence();
                    payToExit(p);
                } else {
                    prisonerDurations.replace(p.getID(), prisonerDurations.get(p.getID()) + 1);
                }
            }
        } else {
            uih.enteredJail();

            prisonerDurations.put(p.getID(), 0);
        }
    }

    private void payToExit(Player p) {
        bank.alterBalance(p, -50);

        uih.paidToExit();

        prisonerDurations.remove(p.getID());
        board.movePlayerTo(p, 10, false);
    }

    void giveGOOJFCard(Player p, boolean isChance) {
        if (isChance) {
            chanceGOOJFOwner = p.getID();
        } else {
            chestGOOJFOwner = p.getID();
        }
    }
}
