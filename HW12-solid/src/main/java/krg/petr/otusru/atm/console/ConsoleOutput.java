package krg.petr.otusru.atm.console;

import krg.petr.otusru.atm.interfaces.OutInterface;

import java.util.Map;

public class ConsoleOutput implements OutInterface {
    @Override
    public void printWithdrawBanknotes(Map<Integer, Integer> withdrawnBanknotes) {
        System.out.println("Withdrawn banknotes:");
        for (Map.Entry<Integer, Integer> entry : withdrawnBanknotes.entrySet()) {
            int denomination = entry.getKey();
            int count = entry.getValue();
            System.out.println(denomination + ": " + count);
        }
    }
    public void printTotalAmount(Map<Integer, Integer> totalAmount) {
        System.out.println("Total Amount:");
        for (Map.Entry<Integer, Integer> entry : totalAmount.entrySet()) {
            int denomination = entry.getKey();
            int amount = entry.getValue();
            System.out.println(denomination + ": " + amount);
        }
    }
}
