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
    private final String sURL = "http://data.fixer.io/api/latest?access_key=4e8986d5d1d4e294d44bddb46a48becc&format=1";
    private URL url = new URL(sURL);
    private URLConnection request = url.openConnection();
    private JsonParser jp = new JsonParser();
    private BigDecimal rate;

    public RatesGetter() throws IOException {
    }

    public BigDecimal rateFromAPI(String currency) throws IOException {
        request.connect();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootobj = root.getAsJsonObject();
        return rate = rootobj.get(currency).getAsBigDecimal();
    }

}
