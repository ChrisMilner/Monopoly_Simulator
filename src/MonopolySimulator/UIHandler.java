package MonopolySimulator;

import MonopolySimulator.Players.Player;

abstract class UIHandler {

    final static int MIN_PLAYERS = 2;
    final static int MAX_PLAYERS = 8;

    abstract void startSimulator();

    int getPlayerNum() {
        int input = -1;
        boolean valid = false;

        while (!valid) {
            input = getPlayerNumInput();

            if (input < MIN_PLAYERS || input > MAX_PLAYERS)
                repromptPlayerNumInput();
            else
                valid = true;
        }
        return input;
    }

    abstract int getPlayerNumInput();

    abstract void repromptPlayerNumInput();

    abstract void newGame(int playerNum);

    abstract void newRound(int roundNum);

    abstract void playerTurn(String name);

    abstract void rolled(int roll1, int roll2);

    abstract void playerMoved(String posName);

    abstract void passedGo(int newBalance);

    abstract void noAction(String name);

    abstract void propertyOfferMade(String propName);

    abstract void propertyOfferAccepted();

    abstract void propertyOfferDeclined();

    abstract void propertySold(String propName, int cost, int newBalance);

    abstract void insufficientFunds(int propCost, int playerBalance);

    abstract void propertyRented();

    abstract void paidRent(int rent, int newBalance);

    abstract void alreadyOwnProperty(String propName);

    abstract void taxed(int amount);

    abstract void cardDrawn(String effect);

    abstract void stillInJail();

    abstract void enteredJail();

    abstract void choseRollForExit();

    abstract void succeededRollForExit(int roll);

    abstract void failedRollForExit(int roll1, int roll2);

    abstract void chosePayForExit();

    abstract void paidToExit();

    abstract void cantAffordExit();

    abstract void choseCardForExit();

    abstract void finishedJailSentence();

    abstract void doubleRoll(int doubleCount);

    abstract void thirdDoubleRoll();

    abstract void noCardAvailable();

    abstract void bankrupt(Player p);

    void error(String msg) {
        System.err.println(msg);
    }

    void exception(Throwable e) {
        informUserOfException(e);

        System.err.println("FATAL ERROR: ");
        e.printStackTrace();
        System.exit(1);
    }

    abstract void informUserOfException(Throwable e);

}
