package MonopolySimulator;

import MonopolySimulator.Players.DefaultPlayer;
import MonopolySimulator.Players.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class NonStreetTest {

    MonopolyBoard mb;
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
        Banker b = new Banker(uih, game);
        b.registerPlayer(p1.getID(), 1000);
        b.registerPlayer(p2.getID(), 1000);
        p1.assignBanker(b);
        p2.assignBanker(b);

        mb = new MonopolyBoard(uih, game, b, ps);
    }

    @Test
    public void updateDevelopmentLevels() {
        mb.getPlace(MonopolyBoard.KINGS_CROSS_STATION).action(p1, 0);
        assertEquals(p1.getID(), ((Station) mb.getPlace(MonopolyBoard.KINGS_CROSS_STATION)).getOwner().getID());
        assertEquals(1, ((Station) mb.getPlace(MonopolyBoard.KINGS_CROSS_STATION)).getDevelopmentLevel());

        mb.getPlace(MonopolyBoard.FENCHURCH_STATION).action(p1, 0);
        assertEquals(p1.getID(), ((Station) mb.getPlace(MonopolyBoard.FENCHURCH_STATION)).getOwner().getID());
        assertEquals(2, ((Station) mb.getPlace(MonopolyBoard.KINGS_CROSS_STATION)).getDevelopmentLevel());
        assertEquals(2, ((Station) mb.getPlace(MonopolyBoard.FENCHURCH_STATION)).getDevelopmentLevel());

        // TODO: Add test for selling once implemented
    }
}