package com.bridgelabz;

public enum LengthUnit {
    FEET(1.0),
    INCHES(1.0/12.0),
    YARD(3.0),
    CENTIMETER(0.393701 / 12.0);
    private final double conversionFactor;
    LengthUnit(double conversionFactor)
    {
        this.conversionFactor=conversionFactor;
    }
    public double getConversionFactor() {
        return conversionFactor;
    }
    public double fromBase(double baseValue) {
        return baseValue / conversionFactor;
    }
    public double toBase(double value) {
        return value * conversionFactor;
    }
}
