package MonopolySimulator;

import MonopolySimulator.Players.DefaultPlayer;
import MonopolySimulator.Players.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommunityChestTest {

    private CommunityChest c;
    private Player p;
    private MonopolyGame game;
    private MonopolyBoard mb;
    private Banker b;

    @Before
    public void setUp() throws Exception {
        UIHandler uih = new CLIHandler();

        p = new DefaultPlayer(0, "Player 0");
        Player p1 = new DefaultPlayer(1, "Player 1");
        ArrayList<Player> ps = new ArrayList<>();
        ps.add(p);
        ps.add(p1);
        game = new MonopolyGame(uih, ps);

        b = new Banker(uih, game);
        b.registerPlayer(p.getID(), 1500);
        p.assignBanker(b);
        b.registerPlayer(p1.getID(), 1500);
        p1.assignBanker(b);

        mb = new MonopolyBoard(uih, game, b, ps);

        c = new CommunityChest(uih, game, mb, b, new Chance(uih, mb, b));
    }

    @Test
    public void cardAction() {
        int prevBalance = b.getBalance(0);
        c.cardAction(p, 0);
        assertEquals(prevBalance + 20, b.getBalance(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 1);
        assertEquals(prevBalance + 50, b.getBalance(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 2);
        assertEquals(prevBalance + (10 * (game.getActivePlayers().size() - 1)), b.getBalance(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 3);
        assertEquals(prevBalance + 25, b.getBalance(0));

        c.cardAction(p, 4);
        assertEquals(0, ((Jail) mb.getPlace(MonopolyBoard.JAIL)).chestGOOJFOwner);

        c.cardAction(p, 5);
        assertEquals(0, mb.getPlayerPos(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 6);
        assertEquals(prevBalance - 100, b.getBalance(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 7);
        assertEquals(prevBalance + 10, b.getBalance(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 8);
        assertEquals(prevBalance + 200, b.getBalance(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 9);
        assertEquals(prevBalance + 100, b.getBalance(0));

        c.cardAction(p, 10);
        assertEquals(30, mb.getPlayerPos(0));
        assertTrue(((Jail) mb.getPlace(MonopolyBoard.JAIL)).prisonerDurations.containsKey(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 11);
        assertEquals(prevBalance - 50, b.getBalance(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 12);
        if (p.fineOrChanceHandler()) {
            assertEquals(prevBalance - 10, b.getBalance(p.getID()));
        }

        prevBalance = b.getBalance(0);
        c.cardAction(p, 13);
        assertEquals(prevBalance - 50, b.getBalance(0));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 14);
        assertEquals(1, mb.getPlayerPos(0));
        assertEquals(prevBalance - 60, b.getBalance(p.getID()));

        prevBalance = b.getBalance(0);
        c.cardAction(p, 15);
        assertEquals(prevBalance + 100, b.getBalance(0));
    }
}