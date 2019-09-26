package junit5.MockitoTraining;

public class ConnectionFactory {
    public UrlConnectionReader build(String url) {
        return new UrlConnectionReader(url);
    }
}
