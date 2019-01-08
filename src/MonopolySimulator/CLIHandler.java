package MonopolySimulator;

import java.util.Scanner;

class CLIHandler extends UIHandler {

    CLIHandler() {
        super();
    }

    void startSimulator() {
        System.out.println();
        System.out.println("=================================================================");
        System.out.println();
        System.out.println("                       Monopoly Simulator");
        System.out.println();
        System.out.println("=================================================================");
        System.out.println();
    }

    int getPlayerNumInput() {
        String msg = "Number of Simulated MonopolySimulator.Players (" + MIN_PLAYERS + "-" + MAX_PLAYERS + "): ";
        System.out.print(msg);

        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    void repromptPlayerNumInput() {
        System.out.println("Number of MonopolySimulator.Players must be in the range " + MIN_PLAYERS + "-" + MAX_PLAYERS);
        System.out.println("Please try again");
        System.out.println();
    }

    void newGame(int playerNum) {
        System.out.println();
        System.out.println("  New Game (" + playerNum + " players)");
        System.out.println("=================================================================");
    }

    void newRound(int roundNum) {
        System.out.println();
        System.out.println("Round " + roundNum);
        System.out.println("=========");
    }

    void playerTurn(String name) {
        System.out.println(name + "'s Turn:");
    }

    void rolled(int roll1, int roll2) {
        System.out.println("  Rolled " + (roll1 + roll2) + " (" + roll1 + " + " + roll2 + ")");
    }

    void playerMoved(String posName) {
        System.out.println("  Moved to " + posName);
    }

    void passedGo(int newBalance) {
        System.out.println("  Passed Go (+200)");
        System.out.println("  New Balance: " + newBalance);
    }

    void noAction(String name) {
        System.out.println("  No action at " + name);
    }

    void propertyOfferMade(String propName) {
        System.out.println("  " + propName + " offered");
    }

    void propertyOfferAccepted() {
        System.out.println("  ACCEPTED offer");
    }

    void propertyOfferDeclined() {
        System.out.println("  DECLINED offer");
    }

    void propertySold(String propName, int cost, int newBalance) {
        System.out.println("  " + propName + " SOLD (-" + cost + ") (Balance: " + newBalance + ")");
    }

    void insufficientFunds(int propCost, int playerBalance) {
        System.out.println("  Player's funds are not sufficient to purchase property (" + propCost + " > " + playerBalance + ")");
    }

    void propertyRented() {
        System.out.println("  Property already owned, so rent must be paid");
    }

    void paidRent(int rent, int newBalance) {
        System.out.println("  Paid Rent (-" + rent + ") (Balance: " + newBalance + ")");
    }

    void alreadyOwnProperty(String propName) {
        System.out.println("  Already own " + propName);
    }

    void taxed(int amount) {
        System.out.println("  Taxed (-" + amount + ")");
    }

    void cardDrawn(String effect) {
        System.out.println("  " + effect);
    }

    void stillInJail() {
        System.out.println("  Still in Jail");
    }

    void enteredJail() {
        System.out.println("  In Jail");
    }

    void choseRollForExit() {
        System.out.println("  Chose to Roll to exit Jail");
    }

    void succeededRollForExit(int roll) {
        System.out.println("  Successfully rolled a double " + roll);
    }

    void failedRollForExit(int roll1, int roll2) {
        System.out.println("  Roll was not a pair (" + roll1 + " and " + roll2 + ")");
    }

    void chosePayForExit() {
        System.out.println("  Chose to Pay 50 to exit Jail");
    }

    void paidToExit() {
        System.out.println("  Paid 50");
    }

    void cantAffordExit() {
        System.out.println("  Cannot afford to pay the fine");
    }

    void choseCardForExit() {
        System.out.println("  Chose to use a Get Out of Jail Free Card to exit Jail");
    }

    void finishedJailSentence() {
        System.out.println("  Jail Sentence is finished, the fine must be paid");
    }

    void doubleRoll(int doubleCount) {
        System.out.println("  Rolled a double (number " + doubleCount + "), roll again");
    }

    void thirdDoubleRoll() {
        System.out.println("  Third double in a row, sent to jail");
    }

    void noCardAvailable() {
        System.out.println("  No Get Out of Jail Free Card is owned");
    }

    void informUserOfException(Throwable e) {
        System.out.println("AN ERROR HAS OCCURRED");
        System.out.println("Trace: ");
        e.printStackTrace();
        System.out.println();
    }

}
