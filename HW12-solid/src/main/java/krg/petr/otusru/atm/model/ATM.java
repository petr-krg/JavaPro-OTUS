package krg.petr.otusru.atm.model;

import krg.petr.otusru.atm.exceptions.InsufficientMoneyException;

import java.util.*;

public class ATM {
    private final Map<Integer, Banknote> banknoteBaskets;

    public ATM() {
        banknoteBaskets = new HashMap<>();
    }

    public void addBanknote(Banknote banknote) {
        Banknote existingBanknote = banknoteBaskets.getOrDefault(banknote.getDenomination(), new Banknote(banknote.getDenomination(), 0));
        banknoteBaskets.put(banknote.getDenomination(), existingBanknote.addQuantity(banknote.getQuantity()));
    }

    public Map<Integer, Integer> getTotalAmount() {
        Map<Integer, Integer> totalAmount = new HashMap<>();
        for (Banknote banknote : banknoteBaskets.values()) {
            totalAmount.put(banknote.getDenomination(), banknote.getDenomination() * banknote.getQuantity());
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
            if (count > 0 && banknote != null) {
                int availableCount = banknote.getQuantity();
                int withdrawalCount = Math.min(count, availableCount);
                if (withdrawalCount > 0) {
                    withdrawal.put(denomination, withdrawalCount);
                    amount -= withdrawalCount * denomination;
                    banknoteBaskets.put(denomination, banknote.reduceQuantity(withdrawalCount));
                }
            }
        }

        if (amount > 0) {
            throw new InsufficientMoneyException("Cannot withdraw the requested amount");
        }

        return withdrawal;
    }
}
