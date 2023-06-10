package krg.petr.otusru.atm;

public class Banknote {
    private final int denomination;
    private int quantity;

    public Banknote(int denomination, int quantity) {
        this.denomination = denomination;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDenomination() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void reduceQuantity(int quantity) {
        this.quantity -= quantity;
    }
}
