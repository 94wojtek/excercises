package junit5;

import java.math.BigDecimal;

public class Euro extends Currency implements CurrencyUnit {
    private final CurrencyName eur = CurrencyName.EUR;

    public Euro(BigDecimal amount) {
        super(amount);
    }

    public Euro(BigDecimal amount, BigDecimal rate, BigDecimal spread) {
        super(amount, rate, spread);
    }

    @Override
    public CurrencyName getCurrencyName() {
        return eur;
    }
}