package krg.petr.otusru.atm.interfaces;

import java.util.Map;

public interface OutInterface {
    void printWithdrawBanknotes(Map<Integer, Integer> withdrawnBanknotes);
    void printTotalAmount(Map<Integer, Integer> totalAmount);
}
