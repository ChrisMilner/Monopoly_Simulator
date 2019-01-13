package MonopolySimulator;

import MonopolySimulator.Players.Player;

class Chance extends Cards {

    Chance(UIHandler uih, MonopolyBoard board, Banker bank) {
        super(uih, board, bank, "Chance");
    }

    void cardAction(Player p, int cardNum) {
        switch (cardNum) {
            case 0:
                uih.cardDrawn("Advance to Mayfair");
                board.movePlayerTo(p, 39, true);
                board.executeActionOnPlayer(p, 0);
                break;
            case 1:
                uih.cardDrawn("Advance to Go");
                board.movePlayerTo(p, 0, true);
                board.executeActionOnPlayer(p, 0);
                break;
            case 2:
                uih.cardDrawn("Street Repairs (-40 per House) (-115 per Hotel)");
                // TODO: Implement
                break;
            case 3:
                uih.cardDrawn("Go to Jail");
                board.movePlayerTo(p, 30, false);
                board.executeActionOnPlayer(p, 0);
                break;
            case 4:
                uih.cardDrawn("Get out of Jail Free");
                ((Jail) board.getPlace(MonopolyBoard.JAIL)).giveGOOJFCard(p, true);
                containsGOOJFCard = false;
                break;
            case 5:
                uih.cardDrawn("Go back 3 spaces");
                board.movePlayer(p, -3);
                break;
            case 6:
                uih.cardDrawn("Pay School Fees (-150)");
                bank.alterBalance(p, -150);
                break;
            case 7:
                uih.cardDrawn("General Repairs (-25 per House) (-100 per hotel)");
                // TODO: Implement
                break;
            case 8:
                uih.cardDrawn("Speeding Fine (-15)");
                bank.alterBalance(p, -15);
                break;
            case 9:
                uih.cardDrawn("Won a Crossword Competition (+100)");
                bank.alterBalance(p, 100);
                break;
            case 10:
                uih.cardDrawn("Building and Loan Matures (+150)");
                bank.alterBalance(p, 150);
                break;
            case 11:
                uih.cardDrawn("Bank pays you Dividend (+50)");
                bank.alterBalance(p, 50);
                break;
            case 12:
                uih.cardDrawn("Advance to Trafalgar Square");
                board.movePlayerTo(p, 24, true);
                board.executeActionOnPlayer(p, 0);
                break;
            case 13:
                uih.cardDrawn("Advance to Marylebone Station");
                board.movePlayerTo(p, 15, true);
                board.executeActionOnPlayer(p, 0);
                break;
            case 14:
                uih.cardDrawn("Advance to Pall Mall");
                board.movePlayerTo(p, 11, true);
                board.executeActionOnPlayer(p, 0);
                break;
            case 15:
                uih.cardDrawn("\"Drunk in Charge\" (-20)");
                bank.alterBalance(p, -20);
                break;
        }

    }
}
