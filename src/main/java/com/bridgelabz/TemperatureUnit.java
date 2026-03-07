package com.bridgelabz;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {
    CELSIUS(c -> c),
    FAHRENHEIT(f -> (f - 32) * 5/9);

    private final Function<Double, Double> toCelsius;
    private final SupportsArithmetic supportsArithmetic = () -> false;

    TemperatureUnit(Function<Double, Double> toCelsius) {
        this.toCelsius = toCelsius;
    }

    @Override
    public double getConversionFactor() {
        return 0;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toCelsius.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double value) {
        if (this == CELSIUS) return value;
        return value * 9/5 + 32; // Celsius → Fahrenheit
    }

    @Override
    public String getUnitName() {
        return "";
    }

    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
                "Operation '" + operation + "' not supported for TemperatureUnit");
    }
}
