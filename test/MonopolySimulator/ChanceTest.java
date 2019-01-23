package MonopolySimulator;

import MonopolySimulator.Players.DefaultPlayer;
import MonopolySimulator.Players.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChanceTest {

    private Chance c;
    private Player p;
    private MonopolyBoard mb;
    private Banker b;

    @Before
    public void setUp() throws Exception {
        UIHandler uih = new CLIHandler();
        p = new DefaultPlayer(0, "Player 0");

        MonopolyGame game = new MonopolyGame(uih, new ArrayList<>());

        b = new Banker(uih, game);
        b.registerPlayer(p.getID(), 1500);
        p.assignBanker(b);

        ArrayList<Player> ps = new ArrayList<>();
        ps.add(p);
        mb = new MonopolyBoard(uih, game, b, ps);

        c = new Chance(uih, mb, b);
    }

    @Test
    public void cardAction() {
        c.cardAction(p, 0);
        assertEquals(39, mb.getPlayerPos(0));

        c.cardAction(p, 1);
        assertEquals(0, mb.getPlayerPos(0));

        int prevBalance = b.getBalance(0);
        c.cardAction(p, 2);
        assertEquals(prevBalance, b.getBalance(0));

        mb.getPlace(MonopolyBoard.NORTHUMBERLAND_AVENUE).action(p, 0);
        ((Street) mb.getPlace(MonopolyBoard.NORTHUMBERLAND_AVENUE)).setDevelopmentLevel(2);

        prevBalance = b.getBalance(0);
        c.cardAction(p, 2);
        assertEquals(prevBalance - 80, b.getBalance(0));

        ((Street) mb.getPlace(MonopolyBoard.NORTHUMBERLAND_AVENUE)).setDevelopmentLevel(5);

        prevBalance = b.getBalance(0);
        c.cardAction(p, 2);
        assertEquals(prevBalance - 115, b.getBalance(0));

        c.cardAction(p, 3);
        assertEquals(30, mb.getPlayerPos(0));
        assertTrue(((Jail) mb.getPlace(MonopolyBoard.JAIL)).prisonerDurations.containsKey(0));

        c.cardAction(p, 4);
        assertEquals(0, ((Jail) mb.getPlace(MonopolyBoard.JAIL)).chanceGOOJFOwner);

        c.cardAction(p, 5);
        assertEquals(27, mb.getPlayerPos(0));
        mb.movePlayerTo(p, 1, false);
        c.cardAction(p, 5);
        assertEquals(38, mb.getPlayerPos(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 6);
        assertEquals(prevBalance - 150, b.getBalance(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 7);
        assertEquals(prevBalance - 100, b.getBalance(0));

        ((Street) mb.getPlace(MonopolyBoard.NORTHUMBERLAND_AVENUE)).setDevelopmentLevel(2);

        prevBalance = b.getBalance(0);
        c.cardAction(p, 7);
        assertEquals(prevBalance - 50, b.getBalance(0));

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