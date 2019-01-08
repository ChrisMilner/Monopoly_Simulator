package MonopolySimulator;

import MonopolySimulator.Players.DefaultPlayer;
import MonopolySimulator.Players.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JailTest {

    private Player p;
    private MonopolyBoard mb;
    private Banker b;

    @Before
    public void setUp() throws Exception {
        UIHandler uih = new CLIHandler();
        p = new DefaultPlayer(0, "Player 0");
        b = new Banker(uih);

        b.registerPlayer(p.getID(), 1500);
        p.assignBanker(b);

        mb = new MonopolyBoard(uih, new Player[] {p}, b);
    }

    // TODO: Expand on tests for different exit options

    @Test
    public void action() {
        mb.movePlayerTo(p, 30, false);
        mb.executeActionOnPlayer(p, 0);
        assertEquals(30, mb.getPlayerPos(p.getID()));
        assertTrue(mb.getJail().prisonerDurations.containsKey(p.getID()));
        assertEquals(0, (int) mb.getJail().prisonerDurations.get(p.getID()));

        int prevBalance = b.getBalance(p.getID());
        mb.executeActionOnPlayer(p, 0);
        assertEquals(10, mb.getPlayerPos(p.getID()));
        assertFalse(mb.getJail().prisonerDurations.containsKey(p.getID()));
        assertEquals(prevBalance - 50, b.getBalance(p.getID()));
    }

    @Test
    public void giveGOOJFCard() {
        mb.getJail().giveGOOJFCard(p);
        assertTrue(mb.getJail().GOOJFCards.containsKey(p.getID()));
        assertEquals(1, (int) mb.getJail().GOOJFCards.get(p.getID()));
    }

}