package junit5;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyNameExchangeTest {

    Euro cur = new Euro(BigDecimal.ONE, new BigDecimal(4.2924), new BigDecimal(0.0302));

    @Test
    void shouldCreateCurrencyObject() {

        assertAll(
                () -> assertEquals(CurrencyName.EUR, cur.getCurrencyName()),
                () -> assertEquals(BigDecimal.ONE.setScale(4, RoundingMode.CEILING), cur.getAmount()),
                () -> assertEquals(new BigDecimal(4.2924).setScale(4, RoundingMode.CEILING), cur.getRate()),
                () -> assertEquals(new BigDecimal(0.0302).setScale(4, RoundingMode.CEILING), cur.getSpread())
        );
    }

    @Test
    void shouldntAcceptNegativeValues() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Currency(BigDecimal.ONE.negate(), BigDecimal.ONE, BigDecimal.ONE)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Currency(BigDecimal.ONE, BigDecimal.ONE.negate(), BigDecimal.ONE)),
                () -> assertThrows(IllegalArgumentException.class, () ->  new Currency(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE.negate()))
        );
    }

    @Test
    void shouldThrowExceptionWhenNegativeAmount() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Currency(BigDecimal.ONE.negate(), BigDecimal.ONE, BigDecimal.ONE));
        assertEquals("Amount can't be negative.", e.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNegativeRate() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Currency(BigDecimal.ONE, BigDecimal.ONE.negate(), BigDecimal.ONE));
        assertEquals("Rate can't be negative.", e.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNegativeSpread() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () ->  new Currency(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE.negate()));
                assertEquals("Spread can't be negative.", e.getMessage());
    }

    @Test
    void shouldConvertToPln() {
        assertEquals(BigDecimal.TEN.setScale(4, RoundingMode.CEILING), cur.convertToPln(BigDecimal.TEN).getAmount());
    }

    @Test
    void shouldDisplayCorrectly() {
        StringBuilder reprBuild = new StringBuilder();
        reprBuild.append(cur.getCurrencyName());
        reprBuild.append(System.lineSeparator());
        reprBuild.append("Amount: ");
        reprBuild.append(cur.getAmount());
        reprBuild.append(System.lineSeparator());
        reprBuild.append("Rate: ");
        reprBuild.append(cur.getRate());
        reprBuild.append(System.lineSeparator());
        reprBuild.append("Spread: ");
        reprBuild.append(cur.getSpread());
        String repr = reprBuild.toString();

        assertEquals(repr, cur.toString());
    }
}