package junit5;

import java.io.IOException;
import java.math.BigDecimal;

public class Dollar extends Currency implements CurrencyUnit {
    private final CurrencyName usd = CurrencyName.USD;

    public Dollar(BigDecimal amount) throws IOException {
        super(amount);
    }

    public Dollar(BigDecimal amount, BigDecimal rate, BigDecimal spread) throws IOException {
        super(amount, rate, spread);
    }

    @Override
    public CurrencyName getCurrencyName() {
        return usd;
    }

}
