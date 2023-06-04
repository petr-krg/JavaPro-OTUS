package krg.petr.otusru.atm.interfaces;

import krg.petr.otusru.atm.currencis.CurrenciesEnum.Currency;

import java.util.Map;

public interface OutInterface {
    void printWithdrawBanknotes(Map<Integer, Integer> withdrawnBanknotes);
    void printTotalAmount(Map<Currency, Integer> totalAmount);
}
