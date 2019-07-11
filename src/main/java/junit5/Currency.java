package junit5;

import java.io.IOException;
import java.math.BigDecimal;

public class Currency implements CurrencyUnit {
    private CurrencyName name;
    private BigDecimal amount;
    private BigDecimal rate;
    private BigDecimal spread;
    private String baseCurrencyUrl;

    public Currency(BigDecimal amount) {
        if(BigDecimal.ZERO.compareTo(amount) > 0) {
            throw new IllegalArgumentException("Amount can't be negative.");
        }
        this.amount = amount.setScale(SCALE, ROUNDING_MODE);
    }

    public Currency(BigDecimal amount, BigDecimal rate, BigDecimal spread) {
        if(BigDecimal.ZERO.compareTo(amount) > 0) {
            throw new IllegalArgumentException("Amount can't be negative.");
        }
        if(BigDecimal.ZERO.compareTo(rate) > 0) {
            throw new IllegalArgumentException("Rate can't be negative.");
        }
        if(BigDecimal.ZERO.compareTo(spread) > 0) {
            throw new IllegalArgumentException("Spread can't be negative.");
        }
        this.amount = amount.setScale(SCALE, ROUNDING_MODE);
        this.rate = rate.setScale(SCALE, ROUNDING_MODE);
        this.spread = spread.setScale(SCALE, ROUNDING_MODE);
    }

    public CurrencyName getCurrencyName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getSpread() {
        return spread;
    }

    @Override
    public String toString() {
        StringBuilder representation = new StringBuilder();
        representation.append(getCurrencyName());
        representation.append(System.lineSeparator());
        representation.append("Amount: ");
        representation.append(getAmount());
        representation.append(System.lineSeparator());
        if(getRate() != null) {
            representation.append("Rate: ");
            representation.append(getRate());
            representation.append(System.lineSeparator());
        }

        if(getSpread() != null) {
            representation.append("Spread: ");
            representation.append(getSpread());
        }

        return representation.toString();
    }

    public Currency convertTo(CurrencyName currencyToConvert) throws IOException {
        Currency toReturn = new Currency(BigDecimal.ONE);

        if (currencyToConvert.equals(this.getCurrencyName())) {
            throw new IllegalArgumentException("Can't convert to same currency.");
        }

        CurrencyName baseCurrencyName = this.getCurrencyName();
        switch (baseCurrencyName) {
            case EUR:
                baseCurrencyUrl = "https://api.exchangeratesapi.io/latest";
                break;
            case USD:
                baseCurrencyUrl = "https://api.exchangeratesapi.io/latest?base=USD";
                break;
            case PLN:
                baseCurrencyUrl = "https://api.exchangeratesapi.io/latest?base=PLN";
                break;
        }
        RatesGetter ratesGetter = new RatesGetter(baseCurrencyUrl);

        switch (currencyToConvert) {
            case EUR:
                toReturn = new Euro(this.amount.multiply(ratesGetter.rateFromAPI(CurrencyName.EUR.toString())));
                break;
            case PLN:
                toReturn = new Zloty(this.amount.multiply(ratesGetter.rateFromAPI(CurrencyName.PLN.toString())));
                break;
            case USD:
                toReturn = new Dollar(this.amount.multiply(ratesGetter.rateFromAPI(CurrencyName.USD.toString())));
                break;
        }
        return toReturn;
    }
}
