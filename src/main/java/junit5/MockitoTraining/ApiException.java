package junit5.MockitoTraining;

public class ApiException extends RuntimeException {

    public ApiException(Throwable consume) {
        super(consume);
    }

    public ApiException(String message) {
        super(message);
    }
}
