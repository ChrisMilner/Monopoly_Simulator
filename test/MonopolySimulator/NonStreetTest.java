package MonopolySimulator;

import MonopolySimulator.Players.DefaultPlayer;
import MonopolySimulator.Players.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class NonStreetTest {

    MonopolyBoard mb;
    Banker b;
    Player p1;
    Player p2;

    @Before
    public void setUp() {
        UIHandler uih = new CLIHandler();
        p1 = new DefaultPlayer(1, "Player 1");
        p2 = new DefaultPlayer(2, "Player 2");
        ArrayList<Player> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p2);

        MonopolyGame game = new MonopolyGame(uih, ps);
        b = new Banker(uih, game);
        b.registerPlayer(p1.getID(), 1000);
        b.registerPlayer(p2.getID(), 1000);
        p1.assignBanker(b);
        p2.assignBanker(b);

        mb = new MonopolyBoard(uih, game, b, ps);
    }

    @Test
    public void testStationDevelopmentLevels() {
        mb.getPlace(MonopolyBoard.KINGS_CROSS_STATION).action(p1, 0);
        assertEquals(p1.getID(), ((Station) mb.getPlace(MonopolyBoard.KINGS_CROSS_STATION)).getOwner().getID());
        assertEquals(0, ((Station) mb.getPlace(MonopolyBoard.KINGS_CROSS_STATION)).getDevelopmentLevel());

        int prevBalance = b.getBalance(p2.getID());
        mb.movePlayerTo(p2, MonopolyBoard.KINGS_CROSS_STATION, false);
        mb.executeActionOnPlayer(p2, 0);
        assertEquals(prevBalance - 25, b.getBalance(p2.getID()));

        mb.getPlace(MonopolyBoard.FENCHURCH_STATION).action(p1, 0);
        assertEquals(p1.getID(), ((Station) mb.getPlace(MonopolyBoard.FENCHURCH_STATION)).getOwner().getID());
        assertEquals(1, ((Station) mb.getPlace(MonopolyBoard.KINGS_CROSS_STATION)).getDevelopmentLevel());
        assertEquals(1, ((Station) mb.getPlace(MonopolyBoard.FENCHURCH_STATION)).getDevelopmentLevel());

        prevBalance = b.getBalance(p2.getID());
        mb.movePlayerTo(p2, MonopolyBoard.FENCHURCH_STATION, false);
        mb.executeActionOnPlayer(p2, 0);
        assertEquals(prevBalance - 50, b.getBalance(p2.getID()));

        prevBalance = b.getBalance(p2.getID());
        mb.movePlayerTo(p2, MonopolyBoard.KINGS_CROSS_STATION, false);
        mb.executeActionOnPlayer(p2, 0);
        assertEquals(prevBalance - 50, b.getBalance(p2.getID()));

        // TODO: Add test for selling NonStreets once selling is implemented
    }

    @Test
    public void testServiceDevelopmentLevels() {
        mb.getPlace(MonopolyBoard.ELECTRIC_COMPANY).action(p1, 0);
        assertEquals(p1.getID(), ((Service) mb.getPlace(MonopolyBoard.ELECTRIC_COMPANY)).getOwner().getID());
        assertEquals(0, ((Service) mb.getPlace(MonopolyBoard.ELECTRIC_COMPANY)).getDevelopmentLevel());

        int prevBalance = b.getBalance(p2.getID());
        mb.movePlayerTo(p2, MonopolyBoard.ELECTRIC_COMPANY, false);
        mb.executeActionOnPlayer(p2, 6);
        assertEquals(prevBalance - (4 * 6), b.getBalance(p2.getID()));

        mb.getPlace(MonopolyBoard.WATER_WORKS).action(p1, 0);
        assertEquals(p1.getID(), ((Service) mb.getPlace(MonopolyBoard.WATER_WORKS)).getOwner().getID());
        assertEquals(1, ((Service) mb.getPlace(MonopolyBoard.WATER_WORKS)).getDevelopmentLevel());
        assertEquals(1, ((Service) mb.getPlace(MonopolyBoard.ELECTRIC_COMPANY)).getDevelopmentLevel());

        prevBalance = b.getBalance(p2.getID());
        mb.movePlayerTo(p2, MonopolyBoard.ELECTRIC_COMPANY, false);
        mb.executeActionOnPlayer(p2, 6);
        assertEquals(prevBalance - (6 * 10), b.getBalance(p2.getID()));

        prevBalance = b.getBalance(p2.getID());
        mb.movePlayerTo(p2, MonopolyBoard.WATER_WORKS, false);
        mb.executeActionOnPlayer(p2, 10);
        assertEquals(prevBalance - (10 * 10), b.getBalance(p2.getID()));

        // TODO: Add test for selling NonStreets once selling is implemented
    }
}