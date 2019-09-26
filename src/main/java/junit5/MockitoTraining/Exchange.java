package junit5.MockitoTraining;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.InputMismatchException;

public class Exchange implements ExchangeApi {

    private static final String API_URL = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/?format=json";
    private static JsonParser jp = new JsonParser();
    private final ConnectionFactory connectionFactory;
    private BigDecimal rate;

    public Exchange() {
        this(new ConnectionFactory());
    }

    public Exchange(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public BigDecimal getRate() {
        return rate;
    }

    @Override
    public BigDecimal exchangeRate(LocalDate date, String currencyCode) {

        if (!Currency.getAvailableCurrencies().contains(Currency.getInstance(currencyCode.toUpperCase()))) {
            throw new InputMismatchException("No such a currency");
        }

        String formattedUrl = String.format(API_URL, currencyCode, date);

        try (UrlConnectionReader urlConnectionReader = connectionFactory.build(formattedUrl)) {
            JsonElement root = jp.parse(urlConnectionReader.response());
            JsonObject rootObj = root.getAsJsonObject();
            JsonArray ratesArr = rootObj.getAsJsonArray("rates");
            JsonObject currencyObj = ratesArr.get(0).getAsJsonObject();
            rate = currencyObj.get("mid").getAsBigDecimal();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return rate;
    }

    public static void main(String[] args) {
        Exchange ex = new Exchange();
        ex.exchangeRate(LocalDate.now(), "gbp");
        System.out.println(ex.getRate());
    }
}
