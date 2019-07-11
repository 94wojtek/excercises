package junit5;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;

public class RatesGetter {
    private String sURL;
    private URL url;
    private JsonParser jp;

    public RatesGetter(String baseCurrency) throws IOException {
        this.sURL = baseCurrency;
        this.url = new URL(sURL);
        this.jp = new JsonParser();
    }

    public BigDecimal rateFromAPI(String currency) throws IOException {
        URLConnection request = url.openConnection();
        request.connect();

        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootObj = root.getAsJsonObject();
        JsonObject ratesList = rootObj.getAsJsonObject("rates");

        return ratesList.get(currency).getAsBigDecimal();
    }


}
