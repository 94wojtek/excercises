package junit5.MockitoTraining;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.InputMismatchException;

public class Exchange implements ExchangeApi {

    private static final String API_URL = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/?format=json";
    private String currencyCode;
    private LocalDate date;
    private BigDecimal rate;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getRate() {
        return rate;
    }

    @Override
    public BigDecimal exchangeRate(LocalDate date, String currencyCode) throws IOException {

        if (!Currency.getAvailableCurrencies().toString().contains(currencyCode.toUpperCase())) {
            throw new InputMismatchException("No such a currency");
        }

        this.currencyCode = currencyCode;
        this.date = date;
        String dateAsString = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String formattedApiUrl = String.format(API_URL, currencyCode, dateAsString);
        RatesAPI ratesAPI = new RatesAPI(formattedApiUrl);
        this.rate = ratesAPI.rateFromAPI();

        return rate;
    }

    @Override
    public String toString() {
        return "PLN converted to " + currencyCode + " for the date " + date + " is " + rate;
    }
}
