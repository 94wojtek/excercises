package junit5;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyNameExchangeTest {

    Currency cur = new Currency(CurrencyName.EUR, BigDecimal.ONE, new BigDecimal(4.2924), new BigDecimal(0.0302));

    @Test
    void shouldCreateCurrObjWithCorrectName() {
        assertEquals(CurrencyName.EUR, cur.getCurrencyName());
    }

    @Test
    void shouldCreateCurrObjWithCorrectAmount() {
        assertEquals(BigDecimal.ONE.setScale(4, RoundingMode.CEILING), cur.getAmount());
    }

    @Test
    void shouldCreateCurrObjWithCorrectRate() {
        assertEquals(new BigDecimal(4.2924).setScale(4, RoundingMode.CEILING), cur.getRate());
    }

    @Test
    void shouldCreateCurrObjWithCorrectSpread() {
        assertEquals(new BigDecimal(0.0302).setScale(4, RoundingMode.CEILING), cur.getSpread());
    }

    @Test
    void shouldntAcceptNegativeAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new Currency(CurrencyName.EUR, BigDecimal.ONE.negate(), BigDecimal.ONE, BigDecimal.ONE));
    }

    @Test
    void shouldntAcceptNegativeRate() {
        assertThrows(IllegalArgumentException.class,
                () -> new Currency(CurrencyName.EUR, BigDecimal.ONE, BigDecimal.ONE.negate(), BigDecimal.ONE));
    }

    @Test
    void shouldntAcceptNegativeSpread() {
        assertThrows(IllegalArgumentException.class,
                () -> new Currency(CurrencyName.EUR, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE.negate()));
    }

    @Test
    void shouldThrowExceptionWhenNegativeAmount() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Currency(CurrencyName.EUR, BigDecimal.ONE.negate(), BigDecimal.ONE, BigDecimal.ONE));
        assertEquals("Amount can't be negative.", e.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNegativeRate() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Currency(CurrencyName.EUR, BigDecimal.ONE, BigDecimal.ONE.negate(), BigDecimal.ONE));
        assertEquals("Rate can't be negative.", e.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNegativeSpread() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Currency(CurrencyName.EUR, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE.negate()));
                assertEquals("Spread can't be negative.", e.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenConvertToSameCurrency() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> cur.convertTo(CurrencyName.EUR));
                assertEquals("Can't convert to same currency.", e.getMessage());
    }

    @Test
    void shouldConvertToPln() throws IOException {
        assertEquals(BigDecimal.valueOf(4.2670).setScale(4), cur.convertTo(CurrencyName.PLN).getAmount());
    }

    @Test
    void shouldConvertToUsd() throws IOException {
        assertEquals(BigDecimal.valueOf(1.1138).setScale(4), cur.convertTo(CurrencyName.USD).getAmount());
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