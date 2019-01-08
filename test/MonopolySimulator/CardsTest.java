package MonopolySimulator;

import MonopolySimulator.Players.DefaultPlayer;
import MonopolySimulator.Players.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardsTest {

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
}