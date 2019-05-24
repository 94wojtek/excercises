package junit5.Weight;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Weight implements WeightUnitScale {
    private WeightUnit unit;
    private BigDecimal value;
    private BigDecimal convertedValue;
    private static final BigDecimal POUND_TO_KILOGRAM_RATIO = new BigDecimal("0.453592").setScale(SCALE, ROUNDING_MODE);

    public Weight(WeightUnit unit, BigDecimal value) {
        if (BigDecimal.ZERO.compareTo(value) > 0) {
            throw new IllegalArgumentException("Weight can't be negative!");
        }
        this.unit = unit;
        this.value = value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    public BigDecimal getValue() {
        return value.setScale(SCALE, ROUNDING_MODE);
    }

    @Override
    public String toString() {
        StringBuilder repr = new StringBuilder();
        repr.append(this.getUnit().name());
        repr.append(System.lineSeparator());
        repr.append(this.getValue());

        return repr.toString();
    }

    public Weight convert(WeightUnit convertTo) {
        if(this.unit.equals(convertTo)) {
            throw new IllegalArgumentException("You can't convert to the same unit.");
        }
        if(this.unit.equals(WeightUnit.POUND)) {
            convertedValue = this.value.multiply(POUND_TO_KILOGRAM_RATIO);
            return new Weight(convertTo, convertedValue.setScale(SCALE, ROUNDING_MODE));
        }

        convertedValue = this.value.divide(POUND_TO_KILOGRAM_RATIO, 4, RoundingMode.CEILING);
        return new Weight(convertTo, convertedValue);
    }
}
