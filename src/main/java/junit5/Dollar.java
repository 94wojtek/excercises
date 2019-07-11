package junit5;

import java.math.BigDecimal;

public class Dollar extends Currency implements CurrencyUnit {
    private final CurrencyName usd = CurrencyName.USD;

    public Dollar(BigDecimal amount) {
        super(amount);
    }

    public Dollar(BigDecimal amount, BigDecimal rate, BigDecimal spread) {
        super(amount, rate, spread);
    }

    @Override
    public CurrencyName getCurrencyName() {
        return usd;
    }

}
