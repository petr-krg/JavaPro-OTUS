package krg.petr.otusru;

import krg.petr.otusru.atm.exceptions.InsufficientMoneyException;
import krg.petr.otusru.atm.model.Banknote;
import krg.petr.otusru.atm.service.ATMService;

public class Main {
    public static void main(String[] args) throws InsufficientMoneyException {
        ATMService atmService = new ATMService();

        atmService.addBanknotes(new Banknote(1000, 5));
        atmService.addBanknotes(new Banknote(5000, 3));
        atmService.addBanknotes(new Banknote(500, 4));

        ATMService.printTotalAmount(atmService.getTotalAmount());

        int withdrawAmount = 8000;
        ATMService.printWithdrawnBanknotes(atmService.withdrawBanknotes(withdrawAmount));

        ATMService.printTotalAmount(atmService.getTotalAmount());
    }
}