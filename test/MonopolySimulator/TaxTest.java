package MonopolySimulator;

import MonopolySimulator.Players.DefaultPlayer;
import MonopolySimulator.Players.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TaxTest {

    private Tax t;
    private Player p;
    private Banker b;

    @Before
    public void setUp() throws Exception {
        UIHandler uih = new CLIHandler();
        MonopolyGame game = new MonopolyGame(uih, new ArrayList<>());
        b = new Banker(uih, game);
        t = new Tax(uih, b, "Super Tax", 100);
        p = new DefaultPlayer(0, "Player 0");

        b.registerPlayer(p.getID(), 1000);
        p.assignBanker(b);
    }

    @Test
    public void action() {
        int prevBalance = b.getBalance(p.getID());
        t.action(p, 0);
        assertEquals(prevBalance - 100, b.getBalance(p.getID()));
    }
}