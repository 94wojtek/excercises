package junit5.MockitoTraining;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExchangeApi {
    BigDecimal exchangeRate(LocalDate date, String currencyCode);
}
