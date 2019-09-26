package junit5.MockitoTraining;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ExchangeApiTest {

    private Exchange exchange;
    private final static String SAMPLE_RESPONSE = "{\"table\":\"A\",\"currency\":\"funt szterling\",\"code\":\"GBP\",\"rates\":[{\"no\":\"186/A/NBP/2019\",\"effectiveDate\":\"2019-09-25\",\"mid\":4.9552}]}";

    @Mock
    private ConnectionFactory connectionFactoryMock;

    @Mock
    private UrlConnectionReader urlConnectionReaderMock;

    @BeforeEach
    void setUp() {
        when(connectionFactoryMock.build(anyString())).thenReturn(urlConnectionReaderMock);
        exchange = new Exchange(connectionFactoryMock);
    }

    @Test
    void shouldReturnCorrectRate() {
        when(urlConnectionReaderMock.response()).thenReturn(SAMPLE_RESPONSE);
        BigDecimal rate = exchange.exchangeRate(LocalDate.of(2019, 9, 25), "GBP");
        assertThat(rate, is(new BigDecimal("4.9552")));
    }

    @Test
    void shouldConnectToRightUrl() {
        when(urlConnectionReaderMock.response()).thenReturn(SAMPLE_RESPONSE);
        exchange.exchangeRate(LocalDate.of(2019, 9, 25), "GBP");
        verify(connectionFactoryMock).build("http://api.nbp.pl/api/exchangerates/rates/a/GBP/2019-09-25/?format=json");
    }
}