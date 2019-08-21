package junit5.MockitoTraining;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;

public class RatesAPI {
    private String sURL;
    private URL url;
    private JsonParser jp;

    public RatesAPI(String baseCurrencyURL) throws IOException {
        this.sURL = baseCurrencyURL;
        this.url = new URL(sURL);
        this.jp = new JsonParser();
    }

    public BigDecimal rateFromAPI() throws IOException {
        URLConnection request = url.openConnection();
        request.connect();

        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootObj = root.getAsJsonObject();
        JsonArray ratesArr = rootObj.getAsJsonArray("rates");
        JsonObject currencyObj = ratesArr.get(0).getAsJsonObject();

        return currencyObj.get("mid").getAsBigDecimal();
    }
}

