package junit5.MockitoTraining;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExchangeApi {
    LocalDate date = null;
    String currencyCode = "";

    BigDecimal exchangeRate(LocalDate date, String currencyCode) throws IOException;
}
