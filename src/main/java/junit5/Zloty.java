package junit5;

import java.math.BigDecimal;

public class Zloty extends Currency implements CurrencyUnit {
    private final CurrencyName pln = CurrencyName.PLN;

    public Zloty(BigDecimal amount) {
        super(amount);
    }

    public Zloty(BigDecimal amount, BigDecimal rate, BigDecimal spread) {
        super(amount, rate, spread);
    }

    @Override
    public CurrencyName getCurrencyName() {
        return pln;
    }
}
