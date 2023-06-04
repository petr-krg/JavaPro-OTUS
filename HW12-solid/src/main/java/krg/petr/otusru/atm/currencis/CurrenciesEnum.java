package krg.petr.otusru.atm.currencis;

public class CurrenciesEnum {
    public enum Currency {
        RUB("Russian ruble"),
        KZT("Kazakhstan tenge"),
        USD("USA dollar");

        private String fullName;

        Currency(String fullName) {
            this.fullName = fullName;
        }

        public String getFullName() {
            return fullName;
        }
    }
}
