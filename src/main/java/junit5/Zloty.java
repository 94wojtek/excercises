package junit5;

import junit5.CurrencyName;
import junit5.CurrencyUnit;

import java.io.IOException;
import java.math.BigDecimal;

public class Zloty extends Currency implements CurrencyUnit {
    private final CurrencyName pln = CurrencyName.PLN;

    public Zloty(BigDecimal amount) throws IOException {
        super(amount);
    }

    public Zloty(BigDecimal amount, BigDecimal rate, BigDecimal spread) throws IOException {
        super(amount, rate, spread);
    }

    @Override
    public CurrencyName getCurrencyName() {
        return pln;
    }
}
