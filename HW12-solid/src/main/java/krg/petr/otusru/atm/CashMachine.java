package krg.petr.otusru.atm;

import krg.petr.otusru.atm.exceptions.InsufficientMoneyException;

import java.util.*;

public class CashMachine {
    private final Map<Integer, Banknote> banknoteBaskets;


    public CashMachine() {
        this.banknoteBaskets = new HashMap<>();
    }

    public void addBanknote(int denomination, int quantity) {
        Banknote banknote = banknoteBaskets.getOrDefault(denomination, new Banknote(denomination, 0));
        banknote.addQuantity(quantity);
        banknoteBaskets.put(denomination,banknote);
    }

    public Map<Integer, Integer> getTotalAmount() {
        Map<Integer, Integer> totalAmount = new HashMap<>();
        for (Banknote banknote : banknoteBaskets.values()) {
            int amount = banknote.getDenomination() * banknote.getQuantity();
            totalAmount.put(banknote.getDenomination(), amount);
        }
        return totalAmount;
    }

    public Map<Integer, Integer> withdrawBanknotes(int amount) throws InsufficientMoneyException {
        Map<Integer, Integer> withdrawal = new HashMap<>();
        List<Integer> denominations = new ArrayList<>(banknoteBaskets.keySet());
        denominations.sort(Comparator.reverseOrder());

        for (int denomination : denominations) {
            Banknote banknote = banknoteBaskets.get(denomination);
            int count = amount / denomination;
            if (count > 0) {
                int availableCount = banknote != null ? banknote.getQuantity() : 0;
                int withdrawCount = Math.min(count, availableCount);
                if (withdrawCount > 0) {
                    withdrawal.put(denomination, withdrawCount);
                    amount -= withdrawCount * denomination;
                }
            }
        }

        if (amount > 0) {
            throw new InsufficientMoneyException("Cannot withdraw the requested amount");
        }

        for (Map.Entry<Integer, Integer> entry : withdrawal.entrySet()) {
            int denomination = entry.getKey();
            int quantity = entry.getValue();
            Banknote banknote = banknoteBaskets.get(denomination);
            banknote.reduceQuantity(quantity);
        }

        return withdrawal;
    }
}
