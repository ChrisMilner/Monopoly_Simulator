package MonopolySimulator;

import MonopolySimulator.Players.DefaultPlayer;
import MonopolySimulator.Players.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChanceTest {

    private Chance c;
    private Player p;
    private MonopolyBoard mb;
    private Banker b;

    @Before
    public void setUp() throws Exception {
        UIHandler uih = new CLIHandler();
        b = new Banker(uih);
        p = new DefaultPlayer(0, "Player 0");
        b.registerPlayer(p.getID(), 1500);
        mb = new MonopolyBoard(uih, new Player[] {p}, b);

        c = new Chance(uih, mb, b);
    }

    @Test
    public void constructor() {
        boolean[] checks = new boolean[16];

        for (int i : c.order) {
            assertTrue(i >= 0);
            assertTrue(i < 16);

            assertFalse(checks[i]);
            checks[i] = true;
        }
    }

    @Test
    public void action() {
        for (int i = 0; i < 16; i++) {
            c.action(p, 0);
            assertEquals(15 - i, c.order.size());
        }

        c.action(p, 0);
        assertEquals(14, c.order.size());
    }

    @Test
    public void cardAction() {
        c.cardAction(p, 0);
        assertEquals(39, mb.getPlayerPos(0));

        c.cardAction(p, 1);
        assertEquals(0, mb.getPlayerPos(0));

//        c.cardAction(p, 2);
//        assertEquals(--, b.getBalance(0));

        c.cardAction(p, 3);
        assertEquals(30, mb.getPlayerPos(0));
        assertTrue(mb.getJail().prisonerDurations.containsKey(0));

        c.cardAction(p, 4);
        assertTrue(mb.getJail().GOOJFCards.containsKey(0));
        assertEquals(1, (int) mb.getJail().GOOJFCards.get(0));

        c.cardAction(p, 5);
        assertEquals(27, mb.getPlayerPos(0));
        mb.movePlayerTo(p, 1, false);
        c.cardAction(p, 5);
        assertEquals(38, mb.getPlayerPos(0));

        int prevBalance = b.getBalance(0);
        c.cardAction(p, 6);
        assertEquals(prevBalance - 150, b.getBalance(0));

//        c.cardAction(p, 7);
//        assertEquals(--, b.getBalance(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 8);
        assertEquals(prevBalance - 15, b.getBalance(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 9);
        assertEquals(prevBalance + 100, b.getBalance(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 10);
        assertEquals(prevBalance + 150, b.getBalance(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 11);
        assertEquals(prevBalance + 50, b.getBalance(0));

        c.cardAction(p, 12);
        assertEquals(24, mb.getPlayerPos(0));

        c.cardAction(p, 13);
        assertEquals(15, mb.getPlayerPos(0));

        c.cardAction(p, 14);
        assertEquals(11, mb.getPlayerPos(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 15);
        assertEquals(prevBalance - 20, b.getBalance(0));
    }
}