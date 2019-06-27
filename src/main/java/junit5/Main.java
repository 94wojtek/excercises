package junit5;

import java.io.IOException;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws IOException {
        RatesGetter ratesGetter = new RatesGetter();
        Euro eur = new Euro(BigDecimal.ONE, new BigDecimal(4.2745), new BigDecimal(0.2134));
        Zloty pln = new Zloty(BigDecimal.ONE);
        System.out.println(eur.toString());

        // Zloty pln = eur.convertToPln(eur.getRate());
        Zloty pln1 = eur.convertToPln(ratesGetter.rateFromAPI(pln.getCurrencyName().toString()));
        System.out.println(pln);

        eur = pln.convertToEur(new BigDecimal(0.2326));
        System.out.println(eur);
    }
}
