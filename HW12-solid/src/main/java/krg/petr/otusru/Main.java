package krg.petr.otusru;

import krg.petr.otusru.atm.CashMachine;
import krg.petr.otusru.atm.console.ConsoleOutput;
import krg.petr.otusru.atm.currencis.CurrenciesEnum.Currency;
import krg.petr.otusru.atm.interfaces.OutInterface;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CashMachine cashMachine = new CashMachine();
        OutInterface print = new ConsoleOutput();

        Currency currencyRub = Currency.RUB;
        System.out.println("Currency: " + currencyRub);
        System.out.println("Full Name: " + currencyRub.getFullName());

        cashMachine.addBanknote(currencyRub, 1000, 5);
        cashMachine.addBanknote(currencyRub, 5000, 3);
        cashMachine.addBanknote(currencyRub, 500, 4);

        Currency currencyKzt = Currency.KZT;
        System.out.println("Currency: " + currencyKzt);
        System.out.println("Full Name: " + currencyKzt.getFullName());
        cashMachine.addBanknote(currencyKzt, 1000, 4);
        cashMachine.addBanknote(currencyKzt, 5000, 5);
        cashMachine.addBanknote(currencyKzt, 500, 8);

        Map<Currency, Integer> totalAmount = cashMachine.getTotalAmount();
        print.printTotalAmount(totalAmount);

        int withdrawAmount = 8000;
        Map<Integer, Integer> withdrawnBanknotes = cashMachine.withdrawBanknotes(withdrawAmount, currencyRub);
        print.printWithdrawBanknotes(withdrawnBanknotes);

        withdrawnBanknotes = cashMachine.withdrawBanknotes(withdrawAmount, currencyKzt);
        print.printWithdrawBanknotes(withdrawnBanknotes);

        totalAmount = cashMachine.getTotalAmount();
        print.printTotalAmount(totalAmount);
    }
}