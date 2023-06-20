package krg.petr.otusru.atm.model;

public final class Banknote {
    private final int denomination;
    private final int quantity;

    public Banknote(int denomination, int quantity) {
        this.denomination = denomination;
        this.quantity = quantity;
    }

    public int getDenomination() {
        return denomination;
    }

    public int getQuantity() {
        return quantity;
    }

    public Banknote addQuantity(int quantity) {
        return new Banknote(denomination, this.quantity + quantity);
    }

    public Banknote reduceQuantity(int quantity) {
        return new Banknote(denomination, this.quantity - quantity);
    }
}
