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

class Main {
    public static void main(String[] args) {
        Euro eur = new Euro(BigDecimal.ONE, new BigDecimal(4.2745), new BigDecimal(0.2134));

        System.out.println(eur.convertToPln(eur.getRate()));
        System.out.println(eur.toString());
    }
}