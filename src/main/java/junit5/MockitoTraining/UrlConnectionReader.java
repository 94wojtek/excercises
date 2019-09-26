package junit5.MockitoTraining;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlConnectionReader implements Closeable {

    private HttpURLConnection connection;

    //must implement this method to allow to use this class in try-with-resources??
    @Override
    public void close() throws IOException {
        connection.disconnect();
    }

    /*
    //Create connection from passed String URL address
    //new URL(...).openConnection() returns URLConnection object which is down casted to HttpURLConnection
     */
    UrlConnectionReader(String url) {
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    getInputStream() opens connection to URL and returns an InputStream representing the data
    No need to use connect() method on connection object because of implicit call by getInputStream()
    Good practice to wrap InputStreamReader to BufferedReader so to improve efficiency of bytes reading

    Each line read from URL's content is added to StringBuilder object by loop as long as line != null
     */
    public String response() {

        validateResponse();
        StringBuilder response = new StringBuilder();
        try (BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = responseReader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return response.toString();
    }

    void validateResponse() {
        try {
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                String responseMessage = connection.getResponseMessage();
                throw new ApiException(String.format("Response code: [%d], %s", responseCode, responseMessage));
            }
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }
}
