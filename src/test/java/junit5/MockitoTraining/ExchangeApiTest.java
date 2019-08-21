package junit5.MockitoTraining;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.InputMismatchException;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ExchangeApiTest {

    @Mock
    ExchangeApi exchangeApiMock;
    Exchange exchangeTest;
    RatesAPI ratesAPITest;

    @Test
    void verifyRightArgsTypes() throws IOException {
        exchangeApiMock.exchangeRate(LocalDate.now(), "random");
        verify(exchangeApiMock).exchangeRate(any(LocalDate.class), anyString());
    }

    @Test
    void verifyExactArgs() throws IOException {
        exchangeApiMock.exchangeRate(LocalDate.now(), "usd");
        verify(exchangeApiMock).exchangeRate(eq(LocalDate.now()), eq("usd"));
    }

    @Test
    void shouldThrowExceptionWhenWrongCurrCode() throws IOException {
        String currencyCode = "random";
        if (!Currency.getAvailableCurrencies().toString().contains(currencyCode.toUpperCase())) {
            when(exchangeApiMock.exchangeRate(any(), eq(currencyCode)))
                    .thenThrow(new InputMismatchException("No such a currency"));
        }
        assertThrows(InputMismatchException.class,
                () -> exchangeApiMock.exchangeRate(LocalDate.now(), currencyCode));
    }

    @Test
    void shouldReturnCorrectRate() throws IOException {
        when(exchangeApiMock.exchangeRate(eq(LocalDate.of(2018, 11, 30)), eq("USD")))
                .thenReturn(BigDecimal.valueOf(3.7731));

        assertEquals(BigDecimal.valueOf(3.7731),
                exchangeApiMock.exchangeRate(LocalDate.of(2018, 11, 30), "USD"));
    }

    @Test
    void shouldRetrieveCorrectRate() throws IOException {
        ratesAPITest = new RatesAPI("http://api.nbp.pl/api/exchangerates/rates/a/GBP/2019-08-21/?format=json");
        assertEquals(BigDecimal.valueOf(4.7623), ratesAPITest.rateFromAPI());
    }

    @Test
    void shouldDisplayCorrectly() throws IOException {
        exchangeTest = new Exchange();
        exchangeTest.exchangeRate(LocalDate.of(2019, 8, 21), "GBP");
        String representation = "PLN converted to GBP for the date 2019-08-21 is 4.7623";
        assertEquals(representation, exchangeTest.toString());
    }
}