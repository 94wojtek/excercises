package junit5;

import java.io.IOException;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws IOException {

        Currency eur = new Currency(CurrencyName.EUR, BigDecimal.ONE);
        Currency usd = new Currency(CurrencyName.USD, BigDecimal.ONE);

        System.out.println(eur);

        Currency pln = usd.convertTo(CurrencyName.PLN);
        System.out.println(pln);

        eur = eur.convertTo(CurrencyName.EUR);
        System.out.println(eur);
    }
}
