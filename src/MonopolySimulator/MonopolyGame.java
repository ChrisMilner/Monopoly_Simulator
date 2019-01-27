package MonopolySimulator;

import MonopolySimulator.Players.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

class MonopolyGame {

    private UIHandler uih;
    private ArrayList<Player> activePlayers;
    private ArrayList<Player> bankruptPlayers;
    private int maxDeals;

    private MonopolyBoard board;
    private Banker banker;
    private Random rand;
    private int roundNum;


    MonopolyGame(UIHandler uih, ArrayList<Player> players, int maxDeals) {
        this.uih = uih;
        this.activePlayers = new ArrayList<>(players);
        this.maxDeals = maxDeals;

        bankruptPlayers = new ArrayList<>();
        banker = new Banker(uih, this);
        board = new MonopolyBoard(uih, this, banker, players);

        for (Player p : players) {
            banker.registerPlayer(p.getID(), 1500);
            p.assignBanker(banker);
        }

        rand = new Random();
        roundNum = 1;
    }

    MonopolyGame(UIHandler uih, ArrayList<Player> players) {
        this(uih, players, players.size() - 1);
    }

    void play() {
        uih.newGame(activePlayers.size());

        while (!winCondition()) {
            uih.newRound(roundNum);
            playRound();
            roundNum++;
        }
    }

    private void playRound() {
        Iterator<Player> it = activePlayers.iterator();
        while (it.hasNext()) {
            Player p = it.next();
            uih.playerTurn(p.getName());

            organisePlayerRolls(p);
            organiseHouseOffers(p);
            organiseNegotiation(p);
        }
    }

    private void organisePlayerRolls(Player p) {
        int roll1, roll2;
        int doubleCount = 0;
        do {
            roll1 = 0;
            roll2 = 0;

            if (!isInJail(p.getID())) {
                roll1 = rand.nextInt(6) + 1;
                roll2 = rand.nextInt(6) + 1;
                uih.rolled(roll1, roll2);

                if (roll1 == roll2) {
                    doubleCount++;

                    if (doubleCount >= 3) {
                        uih.thirdDoubleRoll();
                        board.movePlayerTo(p, 30, false);
                        board.executeActionOnPlayer(p, roll1 + roll2);
                        break;
                    } else {
                        uih.doubleRoll(doubleCount);
                    }
                }

                board.movePlayer(p, roll1 + roll2);
            }

            board.executeActionOnPlayer(p, roll1 + roll2);
        } while (roll1 == roll2 && roll1 != 0);
    }

    private void organiseHouseOffers(Player p) {
        for (int i = MonopolyBoard.OLD_KENT_ROAD; i <= MonopolyBoard.MAYFAIR; i++) {
            if (board.getPlace(i) instanceof Street) {
                Street street = (Street) board.getPlace(i);
                if (street.isPartOfFullGroup()
                        && street.getOwner().getID() == p.getID()
                        && street.getDevelopmentLevel() < 5) {

                    // TODO: Enforce even development across colour groups???

                    int amount = p.houseBuyingHandler(street);
                    for (int j = 0; j < Math.min(amount, 5 - street.getDevelopmentLevel()); j++) {
                        banker.alterBalance(p, -street.getHousePrice());
                        street.addHouse();
                    }
                }
            }
        }
    }

    private void organiseNegotiation(Player p) {
        Deal[] deals = p.negotiate();
        for (int i = 0; i < Math.min(deals.length, maxDeals); i++) {
            Deal previous = null;
            Deal response = deals[i];

            boolean invalid = false;
            int count = 0;
            Player[] participants = new Player[] {p, response.getTo()};
            do {
                if (isValidDeal(participants[count % 2], response)) {
                    previous = response;
                    response = response.getTo().handleDeal(deals[i]);
                } else {
                    invalid = true;
                    break;
                }
                count++;
            } while (response.getType() == DealType.COUNTER_OFFER);

            if (!invalid && response.getType() == DealType.ACCEPT) {
                // TODO: Add output CLI

                if (response.equals(previous))
                    executeDeal(deals[i]);
            }
        }
    }

    private boolean isValidDeal(Player p, Deal deal) {
        // Check that all necessary values are set
        if (deal.getTo() == null || deal.getFrom() == null || deal.getType() == null ||
                deal.getGivingProperties() == null || deal.getReceivingProperties() == null)
            return false;

        // Check that the From value is set to them and the To is not set to them
        if (deal.getFrom().getID() != p.getID() || deal.getTo().getID() == p.getID())
            return false;

        // Check that the To field is set to a valid and non-bankrupt player
        if (!activePlayers.contains(deal.getTo()))
            return false;

        // Check that all the receiving properties are owned by the sender
        for (Property prop : deal.getReceivingProperties()) {
            if (prop.getOwner().getID() != p.getID())
                return false;
        }

        // Check that all the giving properties are owned by the receiver
        for (Property prop : deal.getGivingProperties()) {
            if (prop.getOwner().getID() != deal.getTo().getID())
                return false;
        }

        // Check that players have a valid amount of money
        if (deal.getMoney() > 0) {
            if (banker.getBalance(p.getID()) < deal.getMoney())
                return false;
        } else {
            if (banker.getBalance(deal.getTo().getID()) < -deal.getMoney())
                return false;
        }

        // Check that players have a valid amount of GOOJF cards
        Jail jail = (Jail) board.getPlace(MonopolyBoard.JAIL);
        if (deal.getGOOJFCards() > 0) {
            if (jail.getNumOfGOOJFCards(p.getID()) < deal.getGOOJFCards())
                return false;
        } else {
            if (jail.getNumOfGOOJFCards(deal.getTo().getID()) < -deal.getGOOJFCards())
                return false;
        }

        return true;
    }

    private void executeDeal(Deal deal) {
        // TODO: Implement
    }

    private boolean winCondition() {
        // TODO: Add Proper win condition
        return (activePlayers.size() <= 1 || roundNum > 20);
    }

    void bankruptPlayer(Player p) {
        uih.bankrupt(p);

        bankruptPlayers.add(p);
        activePlayers.remove(p);

        for (int i = 0; i < 40; i++) {
            BoardPosition bp = board.getPlace(i);

            if (!(bp instanceof Property))
                continue;

            Property prop = (Property) bp;

            if (prop.getOwner() != null && prop.getOwner().getID() == p.getID()) {
                prop.removeOwner();
            }
        }
    }

    private boolean isInJail(int id) {
        return ((Jail) board.getPlace(MonopolyBoard.JAIL)).prisonerDurations.containsKey(id);
    }

    ArrayList<Player> getActivePlayers() {
        return activePlayers;
    }

}
