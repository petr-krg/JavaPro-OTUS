package krg.petr.otusru.atm;

import krg.petr.otusru.atm.currencis.CurrenciesEnum.Currency;
import krg.petr.otusru.atm.exceptions.InsufficientMoneyException;

import java.util.*;

public class CashMachine {
    private final Map<Currency, Map<Integer, Integer>> banknoteBaskets;

    public CashMachine() {
        banknoteBaskets = new HashMap<>();
    }

    public void addBanknote(Currency currency, int denomination, int quantity) {
        banknoteBaskets.putIfAbsent(currency, new HashMap<>());
        int currentQuantity = banknoteBaskets.get(currency).getOrDefault(denomination, 0);
        banknoteBaskets.get(currency).put(denomination, currentQuantity + quantity);
    }

    public Map<Currency, Integer> getTotalAmount() {
        Map<Currency, Integer> totalAmount = new HashMap<>();
        for (Map.Entry<Currency, Map<Integer, Integer>> currencyEntry : banknoteBaskets.entrySet()) {
            Currency currency = currencyEntry.getKey();
            HashMap<Integer, Integer> basket = (HashMap<Integer, Integer>) currencyEntry.getValue();
            int amount = 0;
            for (Map.Entry<Integer, Integer> denominationEntry : basket.entrySet()) {
                int denomination = denominationEntry.getKey();
                int quantity = denominationEntry.getValue();
                amount += denomination * quantity;
            }
            totalAmount.put(currency, amount);
        }
        return totalAmount;
    }

    public Map<Integer, Integer> withdrawBanknotes(int amount, Currency currency) {
        Map<Integer, Integer> withdrawal = new HashMap<>();

        Map<Integer, Integer> basket = banknoteBaskets.get(currency);
        if (basket == null) {
            throw new IllegalArgumentException("Currency not supported");
        }

        List<Integer> denominations = new ArrayList<>(basket.keySet());
        denominations.sort(Comparator.reverseOrder());

        for (int denomination : denominations) {
            int count = amount / denomination;
            if (count > 0) {
                int availableCount = basket.getOrDefault(denomination, 0);
                int withdrawalCount = Math.min(count, availableCount);
                if (withdrawalCount > 0) {
                    withdrawal.put(denomination, withdrawalCount);
                    amount -= withdrawalCount * denomination;
                }
            }
        }

        if (amount > 0) {
            try {
                throw new InsufficientMoneyException("Cannot withdraw the requested amount");
            } catch (InsufficientMoneyException e) {
                throw new RuntimeException(e);
            }
        }

        for (Map.Entry<Integer, Integer> entry : withdrawal.entrySet()) {
            int denomination = entry.getKey();
            int quantity = entry.getValue();
            basket.put(denomination, basket.get(denomination) - quantity);
        }

        return withdrawal;
    }

}
