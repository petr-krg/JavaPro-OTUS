package krg.petr.otusru.atm.exceptions;

public class InsufficientMoneyException extends Exception{
    public InsufficientMoneyException(String messages) {
        super(messages);
    }
}
