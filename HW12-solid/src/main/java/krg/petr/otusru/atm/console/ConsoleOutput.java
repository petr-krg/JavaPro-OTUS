package krg.petr.otusru.atm.console;

import krg.petr.otusru.atm.currencis.CurrenciesEnum.Currency;
import krg.petr.otusru.atm.interfaces.OutInterface;

import java.util.Map;

public class ConsoleOutput implements OutInterface {
    @Override
    public void printWithdrawBanknotes(Map<Integer, Integer> withdrawnBanknotes) {
        System.out.println("Withdraw banknotes: ");
        for (Map.Entry<Integer, Integer> entry : withdrawnBanknotes.entrySet()) {
            int denomination = entry.getKey();
            int count = entry.getValue();
            System.out.println(denomination + ": " + count);
        }
    }
    public void printTotalAmount(Map<Currency, Integer> totalAmount) {
        System.out.println("Total Amount: ");
        for (Map.Entry<Currency, Integer> entry : totalAmount.entrySet()) {
            Currency currency = entry.getKey();
            int amount = entry.getValue();
            System.out.println("Currency: " + currency + ", Amount: " + amount);
        }
    }
}
