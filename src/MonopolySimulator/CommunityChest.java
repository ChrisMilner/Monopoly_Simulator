package MonopolySimulator;

import MonopolySimulator.Players.Player;

class CommunityChest extends Cards {

    private MonopolyGame game;
    private Chance chance;

    CommunityChest(UIHandler uih, MonopolyGame game, MonopolyBoard board, Banker bank, Chance chance) {
        super(uih, board, bank, "Community Chest");

        this.game = game;
        this.chance = chance;
    }

    void cardAction(Player p, int cardNum) {
        switch (cardNum) {
            case 0:
                uih.cardDrawn("Income Tax Refund (+20)");
                bank.alterBalance(p, 20);
                break;
            case 1:
                uih.cardDrawn("Sale of Stock (+50)");
                bank.alterBalance(p, 50);
                break;
            case 2:
                uih.cardDrawn("It's your Birthday (+10 from every player)");
                for (Player p2 : game.getActivePlayers()) {
                    if (p2 == p)
                        continue;

                    bank.transaction(p2, p, 10);
                }
                break;
            case 3:
                uih.cardDrawn("Receive Interest on 7% Preference Shares (+25)");
                bank.alterBalance(p, 25);
                break;
            case 4:
                uih.cardDrawn("Get out of Jail Free");
                ((Jail) board.getPlace(MonopolyBoard.JAIL)).giveGOOJFCard(p, false);
                containsGOOJFCard = false;
                break;
            case 5:
                uih.cardDrawn("Advance to Go");
                board.movePlayerTo(p, 0, true);
                board.executeActionOnPlayer(p, 0);
                break;
            case 6:
                uih.cardDrawn("Pay Hospital (-100)");
                bank.alterBalance(p, -100);
                break;
            case 7:
                uih.cardDrawn("Won Second Prize in a Beauty Contest (+10)");
                bank.alterBalance(p, 10);
                break;
            case 8:
                uih.cardDrawn("Bank Error in your Favour (+200)");
                bank.alterBalance(p, 200);
                break;
            case 9:
                uih.cardDrawn("Inheritance (+100)");
                bank.alterBalance(p, 100);
                break;
            case 10:
                uih.cardDrawn("Go to Jail");
                board.movePlayerTo(p, 30, false);
                board.executeActionOnPlayer(p, 0);
                break;
            case 11:
                uih.cardDrawn("Pay your Insurance Premium (-50)");
                bank.alterBalance(p, -50);
                break;
            case 12:
                uih.cardDrawn("Pay a $10 Fine or Take a \"Chance\"");
                boolean decision = p.fineOrChanceHandler();
                if (decision) {
                    bank.alterBalance(p, -10);
                } else {
                    chance.action(p, 0);
                }
                break;
            case 13:
                uih.cardDrawn("Doctor's Fee (-50)");
                bank.alterBalance(p, -50);
                break;
            case 14:
                uih.cardDrawn("Go Back to Old Kent Road");
                board.movePlayerTo(p, 1, false);
                board.executeActionOnPlayer(p, 0);
                break;
            case 15:
                uih.cardDrawn("Annuity Matures Collect (+100)");
                bank.alterBalance(p, 100);
                break;
        }

    }
}
