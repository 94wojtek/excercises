package junit5.Weight;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.jupiter.api.Assertions.*;

class WeightTest {

    Weight pound = new Weight(WeightUnit.POUND, BigDecimal.ONE);
    @Test
    void shouldConvertWeights() {
        assertEquals(new BigDecimal("0.4536"), pound.convert(WeightUnit.KILOGRAM).getValue());
    }

    @Test
    void shouldThrowExceptionWhileConvertingToSameUnit() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> pound.convert(WeightUnit.POUND));
        assertEquals("You can't convert to the same unit.", e.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNegativeValue() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Weight(WeightUnit.KILOGRAM, BigDecimal.ONE.negate()));
        assertEquals("Weight can't be negative!", e.getMessage());
    }

    @Test
    void shouldDisplayCorrectly() {
        String repr = "POUND" + System.lineSeparator() + "1.0000";
        assertEquals(repr, pound.toString());
    }
}