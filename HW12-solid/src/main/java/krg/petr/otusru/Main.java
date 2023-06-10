package krg.petr.otusru;

import krg.petr.otusru.atm.CashMachine;
import krg.petr.otusru.atm.console.ConsoleOutput;
import krg.petr.otusru.atm.exceptions.InsufficientMoneyException;
import krg.petr.otusru.atm.interfaces.OutInterface;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws InsufficientMoneyException {
        CashMachine cashMachine = new CashMachine();
        OutInterface print = new ConsoleOutput();

        cashMachine.addBanknote(1000, 5);
        cashMachine.addBanknote(5000, 3);
        cashMachine.addBanknote(500, 4);

        Map<Integer, Integer> totalAmount = cashMachine.getTotalAmount();
        print.printTotalAmount(totalAmount);

        int withdrawAmount = 8000;
        Map<Integer, Integer> withdrawnBanknotes = cashMachine.withdrawBanknotes(withdrawAmount);
        print.printWithdrawBanknotes(withdrawnBanknotes);

        totalAmount = cashMachine.getTotalAmount();
        print.printTotalAmount(totalAmount);
    }
}