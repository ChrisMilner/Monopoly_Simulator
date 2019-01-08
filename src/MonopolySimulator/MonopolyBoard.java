package MonopolySimulator;

import MonopolySimulator.Players.Player;

import java.util.ArrayList;
import java.util.HashMap;

class MonopolyBoard {

    private final static int NO_OF_BOARD_POSITIONS = 40;

    private UIHandler uih;
    private MonopolyGame game;
    private Banker bank;
    private BoardPosition[] places = new BoardPosition[NO_OF_BOARD_POSITIONS];
    private HashMap<Integer,Integer> playerPositions = new HashMap<>();

    MonopolyBoard(UIHandler uih, MonopolyGame game, Banker bank, ArrayList<Player> players) {
        this.uih = uih;
        this.game = game;
        this.bank = bank;

        for (Player p : players) {
            playerPositions.put(p.getID(), 0);
        }

        initialiseBoard();
    }

    int movePlayer(Player p, int move) {
        int newPos = (playerPositions.get(p.getID()) + move) % 40;

        if (move > 0) {
            if (getPlayerPos(p.getID()) - newPos > 0) {
                bank.alterBalance(p, 200);
                uih.passedGo(bank.getBalance(p.getID()));
            }
        } else if (newPos < 0) {
            newPos += 40;
        }

        playerPositions.replace(p.getID(), newPos);

        uih.playerMoved(places[newPos].getName());

        return newPos;
    }

    void movePlayerTo(Player p, int pos, boolean collect) {
        if (collect) {
            if (getPlayerPos(p.getID()) - pos > 0) {
                bank.alterBalance(p, 200);
                uih.passedGo(bank.getBalance(p.getID()));
            }
        }

        playerPositions.replace(p.getID(), pos);

        uih.playerMoved(places[pos].getName());
    }

    int getPlayerPos(int id) {
        return playerPositions.get(id);
    }

    void executeActionOnPlayer(Player p, int roll) {
        int pos = playerPositions.get(p.getID());
        places[pos].action(p, roll);
    }

    Jail getJail() {
        return (Jail) places[30];
    }

    // TODO: Free Parking Money??

    BoardPosition getPlace(int pos) {
        return places[pos];
    }

    private void initialiseBoard() {
        Chance chance = new Chance(uih, this, bank);
        CommunityChest cc = new CommunityChest(uih, game, this, bank, chance);

        places[0] = new Empty(uih, "Go");
        places[1] = new Street(uih, bank, "Old Kent Road", 60, new int[] {2,10,30,90,160,250});
        places[2] = cc;
        places[3] = new Street(uih, bank, "Whitechapel Road", 60, new int[] {4,20,60,180,320,450});
        places[4] = new Tax(uih, bank, "Income Tax", 200);
        places[5] = new Station(uih, bank, this, "Kings Cross Station");
        places[6] = new Street(uih, bank, "The Angel Islington", 100, new int[] {6,30,90,270,400,550});
        places[7] = chance;
        places[8] = new Street(uih, bank, "Euston Road", 100, new int[] {6,30,90,270,400,550});
        places[9] = new Street(uih, bank, "Pentonville Road", 120, new int[] {8,40,100,300,450,600});

        places[10] = new Empty(uih, "Visiting Jail");
        places[11] = new Street(uih, bank, "Pall Mall", 140, new int[] {10,50,150,450,625,750});
        places[12] = new Service(uih, bank, "Electric Company");
        places[13] = new Street(uih, bank, "Whitehall", 140, new int[] {10,50,150,450,625,750});
        places[14] = new Street(uih, bank, "Northumberland Avenue", 160, new int[] {12,60,180,500,700,900});
        places[15] = new Station(uih, bank, this, "Marylebone Station");
        places[16] = new Street(uih, bank, "Bow Street", 180, new int[] {14,70,200,550,750,950});
        places[17] = cc;
        places[18] = new Street(uih, bank, "Marlborough Street", 180, new int[] {14,70,200,550,750,950});
        places[19] = new Street(uih, bank, "Vine Street", 200, new int[] {16,80,220,600,800,1000});

        places[20] = new Empty(uih, "Free Parking");
        places[21] = new Street(uih, bank, "Strand", 220, new int[] {18,90,250,700,875,1050});
        places[22] = chance;
        places[23] = new Street(uih, bank, "Fleet Street", 220, new int[] {18,90,250,700,875,1050});
        places[24] = new Street(uih, bank, "Trafalgar Square", 240, new int[] {20,100,300,750,925,1100});
        places[25] = new Station(uih, bank, this, "Fenchurch Street Station");
        places[26] = new Street(uih, bank, "Leicester Square", 260, new int[] {22,110,330,800,975,1150});
        places[27] = new Street(uih, bank, "Coventry Street", 260, new int[] {22,110,330,800,975,1150});
        places[28] = new Service(uih, bank, "Water Works");
        places[29] = new Street(uih, bank, "Piccadilly", 280, new int[] {22,120,360,850,1025,1200});

        places[30] = new Jail(uih, this, bank);
        places[31] = new Street(uih, bank, "Regent Street", 300, new int[] {26,130,390,900,1100,1275});
        places[32] = new Street(uih, bank, "Oxford Street", 300, new int[] {26,130,390,900,1100,1275});
        places[33] = cc;
        places[34] = new Street(uih, bank, "Bond Street", 320, new int[] {28,150,450,1000,1200,1400});
        places[35] = new Station(uih, bank, this, "Liverpool Street Station");
        places[36] = chance;
        places[37] = new Street(uih, bank, "Park Lane", 350, new int[] {35,175,500,1100,1300,1500});
        places[38] = new Tax(uih, bank, "Super Tax", 100);
        places[39] = new Street(uih, bank, "Mayfair", 400, new int[] {50,200,600,1400,1700,2000});
    }
}
