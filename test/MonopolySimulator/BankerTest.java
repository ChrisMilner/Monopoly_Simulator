package MonopolySimulator;

import MonopolySimulator.Players.DefaultPlayer;
import MonopolySimulator.Players.Player;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import static org.junit.Assert.*;

public class BankerTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private UIHandler uih;

    @Before
    public void setUp() throws Exception {
        uih = new CLIHandler();
    }

    @Test
    public void registerPlayer() {
        Banker bank = new Banker(uih);

        System.out.println("Register Player: Test 1 (Basic Usage)");
        bank.registerPlayer(0, 100);
        assertEquals(bank.getBalance(0), 100);

        System.out.println("Register Player: Test 2 (Bounds)");
        bank.registerPlayer(1, 0);
        assertEquals(bank.getBalance(1), 0);

        System.out.println("Register Player: Test 3 (Bounds)");
        bank.registerPlayer(2, 1000000000);
        assertEquals(bank.getBalance(2), 1000000000);

        System.out.println("Register Player: Test 4 (Bounds)");
        bank.registerPlayer(1000000000, 50);
        assertEquals(bank.getBalance(1000000000), 50);

        System.out.println("Register Player: Test 5 (Errors)");
        exit.expectSystemExit();
        bank.registerPlayer(1, 100);
    }

    @Test
    public void alterBalance() {
        Banker bank = new Banker(uih);
        Player p = new DefaultPlayer(0, "Player 0");
        bank.registerPlayer(0, 0);

        System.out.println("Alter Balance: Test 1 (Basic Usage)");
        bank.alterBalance(p, 100);
        assertEquals(bank.getBalance(0), 100);

        System.out.println("Alter Balance: Test 2 (Basic Usage)");
        bank.alterBalance(p, -99);
        assertEquals(bank.getBalance(0), 1);

        System.out.println("Alter Balance: Test 3 (Bounds)");
        bank.alterBalance(p, 1000000000);
        assertEquals(bank.getBalance(0), 1000000001);

        System.out.println("Alter Balance: Test 4 (Bounds)");
        bank.alterBalance(p, 0);
        assertEquals(bank.getBalance(0), 1000000001);

        System.out.println("Alter Balance: Test 5 (Bankruptcy)");
        assertEquals(bank.alterBalance(p, -2000000000), -1);

        System.out.println("Alter Balance: Test 6 (Errors)");
        exit.expectSystemExit();
        bank.alterBalance(new DefaultPlayer(1, "Player 1"), 100);
    }

    @Test
    public void transaction() {
        Banker bank = new Banker(uih);
        Player p0 = new DefaultPlayer(0, "Player 0");
        Player p1 = new DefaultPlayer(1, "Player 1");
        bank.registerPlayer(0, 1000);
        bank.registerPlayer(1, 1000);

        System.out.println("Transaction: Test 1 (Basic Usage)");
        bank.transaction(p0, p1, 100);
        assertEquals(bank.getBalance(0), 900);
        assertEquals(bank.getBalance(1), 1100);
    }

}