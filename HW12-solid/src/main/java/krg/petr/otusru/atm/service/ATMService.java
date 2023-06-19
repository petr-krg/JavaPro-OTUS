package krg.petr.otusru.atm.service;

import krg.petr.otusru.atm.exceptions.InsufficientMoneyException;
import krg.petr.otusru.atm.model.ATM;
import krg.petr.otusru.atm.model.Banknote;

import java.util.Map;

public class ATMService {
    private final ATM atm;

    public ATMService() {
        atm = new ATM();
    }

    public void addBanknotes(Banknote banknote) {
        atm.addBanknote(banknote);
    }

    public Map<Integer, Integer> getTotalAmount() {
        return atm.getTotalAmount();
    }

    public Map<Integer, Integer> withdrawBanknotes(int amount) throws InsufficientMoneyException {
        return atm.withdrawBanknotes(amount);
    }

    public static void printWithdrawnBanknotes(Map<Integer, Integer> withdrawnBanknotes) {
        System.out.println("Withdrawn banknotes: ");
        for (Map.Entry<Integer, Integer> entry : withdrawnBanknotes.entrySet()) {
            int denomination = entry.getKey();
            int count = entry.getValue();
            System.out.println(denomination + ": " + count);
        }
    }

    public static void printTotalAmount(Map<Integer, Integer> totalAmount) {
        System.out.println("Total Amount: ");
        for (Map.Entry<Integer, Integer> entry : totalAmount.entrySet()) {
            int denomination = entry.getKey();
            int amount = entry.getValue();
            System.out.println("Denomination: " + denomination + ", Amount: " + amount);
        }
    }
}
