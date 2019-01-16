package MonopolySimulator;

import MonopolySimulator.Players.DefaultPlayer;
import MonopolySimulator.Players.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StreetTest {

    private MonopolyBoard mb;
    private Player p1;

    @Before
    public void setUp() {
        UIHandler uih = new CLIHandler();
        p1 = new DefaultPlayer(1, "Player 1");
        ArrayList<Player> ps = new ArrayList<>();
        ps.add(p1);

        MonopolyGame game = new MonopolyGame(uih, ps);
        Banker b = new Banker(uih, game);
        b.registerPlayer(p1.getID(), 1000);
        p1.assignBanker(b);

        mb  = new MonopolyBoard(uih, game, b, ps);
    }

    @Test
    public void checkGroup() {
        assertFalse(((Street) mb.getPlace(MonopolyBoard.PALL_MALL)).isPartOfFullGroup());
        assertFalse(((Street) mb.getPlace(MonopolyBoard.WHITEHALL)).isPartOfFullGroup());
        assertFalse(((Street) mb.getPlace(MonopolyBoard.NORTHUMBERLAND_AVENUE)).isPartOfFullGroup());

        mb.movePlayerTo(p1, MonopolyBoard.PALL_MALL, false);
        mb.executeActionOnPlayer(p1, 0);
        assertEquals(p1.getID(), ((Street) mb.getPlace(MonopolyBoard.PALL_MALL)).getOwner().getID());

        assertFalse(((Street) mb.getPlace(MonopolyBoard.PALL_MALL)).isPartOfFullGroup());
        assertFalse(((Street) mb.getPlace(MonopolyBoard.WHITEHALL)).isPartOfFullGroup());
        assertFalse(((Street) mb.getPlace(MonopolyBoard.NORTHUMBERLAND_AVENUE)).isPartOfFullGroup());

        mb.movePlayerTo(p1, MonopolyBoard.WHITEHALL, false);
        mb.executeActionOnPlayer(p1, 0);
        assertEquals(p1.getID(), ((Street) mb.getPlace(MonopolyBoard.WHITEHALL)).getOwner().getID());

        assertFalse(((Street) mb.getPlace(MonopolyBoard.PALL_MALL)).isPartOfFullGroup());
        assertFalse(((Street) mb.getPlace(MonopolyBoard.WHITEHALL)).isPartOfFullGroup());
        assertFalse(((Street) mb.getPlace(MonopolyBoard.NORTHUMBERLAND_AVENUE)).isPartOfFullGroup());

        mb.movePlayerTo(p1, MonopolyBoard.NORTHUMBERLAND_AVENUE, false);
        mb.executeActionOnPlayer(p1, 0);
        assertEquals(p1.getID(), ((Street) mb.getPlace(MonopolyBoard.NORTHUMBERLAND_AVENUE)).getOwner().getID());

        assertTrue(((Street) mb.getPlace(MonopolyBoard.PALL_MALL)).isPartOfFullGroup());
        assertTrue(((Street) mb.getPlace(MonopolyBoard.WHITEHALL)).isPartOfFullGroup());
        assertTrue(((Street) mb.getPlace(MonopolyBoard.NORTHUMBERLAND_AVENUE)).isPartOfFullGroup());
    }

    @Test
    public void addHouse() {
        Street na = (Street) mb.getPlace(MonopolyBoard.NORTHUMBERLAND_AVENUE);

        assertEquals(0, na.getDevelopmentLevel());
        assertEquals(12, na.getRent(0));

        na.addHouse();
        assertEquals(1, na.getDevelopmentLevel());
        assertEquals(60, na.getRent(0));

        na.addHouse();
        assertEquals(2, na.getDevelopmentLevel());
        assertEquals(180, na.getRent(0));

        na.addHouse();
        assertEquals(3, na.getDevelopmentLevel());
        assertEquals(500, na.getRent(0));

        na.addHouse();
        assertEquals(4, na.getDevelopmentLevel());
        assertEquals(700, na.getRent(0));

        na.addHouse();
        assertEquals(5, na.getDevelopmentLevel());
        assertEquals(900, na.getRent(0));
    }

}