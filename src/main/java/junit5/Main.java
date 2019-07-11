package junit5;

import java.io.IOException;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws IOException {

        Currency pln = new Zloty(BigDecimal.ONE);
        Currency eur = new Euro(BigDecimal.ONE);
        Currency usd = new Dollar(BigDecimal.ONE);

        System.out.println(eur);

        pln = usd.convertTo(CurrencyName.PLN);
        System.out.println(pln);

        eur = eur.convertTo(CurrencyName.EUR);
        System.out.println(eur);
    }
}
