package MonopolySimulator;

import MonopolySimulator.Players.DefaultPlayer;
import MonopolySimulator.Players.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JailTest {

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
    }

    // TODO: Expand on tests for different exit options

    @Test
    public void action() {
        mb.movePlayerTo(p, 30, false);
        mb.executeActionOnPlayer(p, 0);
        assertEquals(30, mb.getPlayerPos(p.getID()));
        assertTrue(((Jail) mb.getPlace(MonopolyBoard.JAIL)).prisonerDurations.containsKey(p.getID()));
        assertEquals(0, (int) ((Jail) mb.getPlace(MonopolyBoard.JAIL)).prisonerDurations.get(p.getID()));

        int prevBalance = b.getBalance(p.getID());
        mb.executeActionOnPlayer(p, 0);
        assertEquals(10, mb.getPlayerPos(p.getID()));
        assertFalse(((Jail) mb.getPlace(MonopolyBoard.JAIL)).prisonerDurations.containsKey(p.getID()));
        assertEquals(prevBalance - 50, b.getBalance(p.getID()));
    }

    @Test
    public void giveGOOJFCard() {
        ((Jail) mb.getPlace(MonopolyBoard.JAIL)).giveGOOJFCard(p, true);
        assertEquals(0, ((Jail) mb.getPlace(MonopolyBoard.JAIL)).chanceGOOJFOwner);

        ((Jail) mb.getPlace(MonopolyBoard.JAIL)).giveGOOJFCard(p, false);
        assertEquals(0, ((Jail) mb.getPlace(MonopolyBoard.JAIL)).chestGOOJFOwner);
    }

}