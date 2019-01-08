package MonopolySimulator;

import MonopolySimulator.Players.Player;

import java.util.HashMap;

public class Banker {

    private HashMap<Integer, Integer> records;
    private UIHandler uih;

    Banker(UIHandler uih) {
        this.uih = uih;

        records = new HashMap<>();
    }

    void registerPlayer(int id, int balance) {
        if (records.containsKey(id)) {
            uih.error("Tried to register an already registered player (ID: " + id + ")");
            AssertionError err = new AssertionError("Registered Players cannot be re-registered");
            uih.exception(err);
        }

        records.put(id, balance);
    }

    int alterBalance(Player p, int amount) {
        if (!records.containsKey(p.getID())) {
            uih.error("Tried to alter the balance of an unregistered player (ID: " + p.getID() + ")");
            AssertionError err = new AssertionError("An unregistered player's balance cannot be queried");
            uih.exception(err);
        }

        if (getBalance(p.getID()) + amount < 0) {
            p.cantPayHandler(amount);

            if (getBalance(p.getID()) + amount < 0) {
                // TODO: Handle Bankruptcy
                return -1;
            }
        }

        int newBalance = records.get(p.getID()) + amount;
        records.replace(p.getID(), newBalance);

        return newBalance;
    }

    int transaction(Player payer, Player payee, int amount) {
        int newBalance = alterBalance(payer, -amount);

        if (newBalance >= 0)
            alterBalance(payee, amount);

        return newBalance;
    }

    public int getBalance(int id) {
        int balance = 0;

        try {
            balance = records.get(id);
        } catch (NullPointerException e) {
            uih.exception(e);
        }

        return balance;
    }
}
