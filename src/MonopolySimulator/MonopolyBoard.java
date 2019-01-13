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

    BoardPosition getPlace(int pos) {
        return places[pos];
    }

    public static final int GO = 0;
    public static final int OLD_KENT_ROAD = 1;
    public static final int COMMUNITY_CHEST_1 = 2;
    public static final int WHITECHAPEL_ROAD = 3;
    public static final int INCOME_TAX = 4;
    public static final int KINGS_CROSS_STATION = 5;
    public static final int ANGEL_ISLINGTON = 6;
    public static final int CHANCE_1 = 7;
    public static final int EUSTON_ROAD = 8;
    public static final int PENTONVILLE_ROAD = 9;

    public static final int VISITING_JAIL = 10;
    public static final int PALL_MALL = 11;
    public static final int ELECTRIC_COMPANY= 12;
    public static final int WHITEHALL = 13;
    public static final int NORTHUMBERLAND_AVENUE = 14;
    public static final int MARYLEBONE_STATION = 15;
    public static final int BOW_STREET = 16;
    public static final int COMMUNITY_CHEST_2 = 17;
    public static final int MARLBOROUGH_STREET = 18;
    public static final int VINE_STREET = 19;

    public static final int FREE_PARKING = 20;
    public static final int STRAND = 21;
    public static final int CHANCE_2= 22;
    public static final int FLEET_STREET = 23;
    public static final int TRAFALGAR_SQUARE = 24;
    public static final int FENCHURCH_STATION = 25;
    public static final int LEICESTER_SQUARE = 26;
    public static final int COVENTRY_STREET = 27;
    public static final int WATER_WORKS = 28;
    public static final int PICCADILLY = 29;

    public static final int JAIL =30;
    public static final int REGENT_STREET = 31;
    public static final int OXFORD_STREET= 32;
    public static final int COMMUNITY_CHEST_3 = 33;
    public static final int BOND_STREET = 34;
    public static final int LIVERPOOL_STATION = 35;
    public static final int CHANCE_3 = 36;
    public static final int PARK_LANE = 37;
    public static final int SUPER_TAX = 38;
    public static final int MAYFAIR = 39;

    // TODO: Free Parking Money??
    private void initialiseBoard() {
        Chance chance = new Chance(uih, this, bank);
        CommunityChest cc = new CommunityChest(uih, game, this, bank, chance);

        places[GO] = new Empty(uih, "Go");
        places[OLD_KENT_ROAD] = new Street(uih, bank, "Old Kent Road", 60, new int[] {2,10,30,90,160,250});
        places[COMMUNITY_CHEST_1] = cc;
        places[WHITECHAPEL_ROAD] = new Street(uih, bank, "Whitechapel Road", 60, new int[] {4,20,60,180,320,450});
        places[INCOME_TAX] = new Tax(uih, bank, "Income Tax", 200);
        places[KINGS_CROSS_STATION] = new Station(uih, bank, this, "Kings Cross Station");
        places[ANGEL_ISLINGTON] = new Street(uih, bank, "The Angel Islington", 100, new int[] {6,30,90,270,400,550});
        places[CHANCE_1] = chance;
        places[EUSTON_ROAD] = new Street(uih, bank, "Euston Road", 100, new int[] {6,30,90,270,400,550});
        places[PENTONVILLE_ROAD] = new Street(uih, bank, "Pentonville Road", 120, new int[] {8,40,100,300,450,600});

        places[VISITING_JAIL] = new Empty(uih, "Visiting Jail");
        places[PALL_MALL] = new Street(uih, bank, "Pall Mall", 140, new int[] {10,50,150,450,625,750});
        places[ELECTRIC_COMPANY] = new Service(uih, bank, this, "Electric Company");
        places[WHITEHALL] = new Street(uih, bank, "Whitehall", 140, new int[] {10,50,150,450,625,750});
        places[NORTHUMBERLAND_AVENUE] = new Street(uih, bank, "Northumberland Avenue", 160, new int[] {12,60,180,500,700,900});
        places[MARYLEBONE_STATION] = new Station(uih, bank, this, "Marylebone Station");
        places[BOW_STREET] = new Street(uih, bank, "Bow Street", 180, new int[] {14,70,200,550,750,950});
        places[COMMUNITY_CHEST_2] = cc;
        places[MARLBOROUGH_STREET] = new Street(uih, bank, "Marlborough Street", 180, new int[] {14,70,200,550,750,950});
        places[VINE_STREET] = new Street(uih, bank, "Vine Street", 200, new int[] {16,80,220,600,800,1000});

        places[FREE_PARKING] = new Empty(uih, "Free Parking");
        places[STRAND] = new Street(uih, bank, "Strand", 220, new int[] {18,90,250,700,875,1050});
        places[CHANCE_2] = chance;
        places[FLEET_STREET] = new Street(uih, bank, "Fleet Street", 220, new int[] {18,90,250,700,875,1050});
        places[TRAFALGAR_SQUARE] = new Street(uih, bank, "Trafalgar Square", 240, new int[] {20,100,300,750,925,1100});
        places[FENCHURCH_STATION] = new Station(uih, bank, this, "Fenchurch Street Station");
        places[LEICESTER_SQUARE] = new Street(uih, bank, "Leicester Square", 260, new int[] {22,110,330,800,975,1150});
        places[COVENTRY_STREET] = new Street(uih, bank, "Coventry Street", 260, new int[] {22,110,330,800,975,1150});
        places[WATER_WORKS] = new Service(uih, bank, this, "Water Works");
        places[PICCADILLY] = new Street(uih, bank, "Piccadilly", 280, new int[] {22,120,360,850,1025,1200});

        places[JAIL] = new Jail(uih, this, bank);
        places[REGENT_STREET] = new Street(uih, bank, "Regent Street", 300, new int[] {26,130,390,900,1100,1275});
        places[OXFORD_STREET] = new Street(uih, bank, "Oxford Street", 300, new int[] {26,130,390,900,1100,1275});
        places[COMMUNITY_CHEST_3] = cc;
        places[BOND_STREET] = new Street(uih, bank, "Bond Street", 320, new int[] {28,150,450,1000,1200,1400});
        places[LIVERPOOL_STATION] = new Station(uih, bank, this, "Liverpool Street Station");
        places[CHANCE_3] = chance;
        places[PARK_LANE] = new Street(uih, bank, "Park Lane", 350, new int[] {35,175,500,1100,1300,1500});
        places[SUPER_TAX] = new Tax(uih, bank, "Super Tax", 100);
        places[MAYFAIR] = new Street(uih, bank, "Mayfair", 400, new int[] {50,200,600,1400,1700,2000});
    }
}
