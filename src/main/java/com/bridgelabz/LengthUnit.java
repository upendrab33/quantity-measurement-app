package com.bridgelabz;

public enum LengthUnit implements IMeasurable {
    FEET(1.0),
    INCHES(1.0/12.0),
    YARD(3.0),
    CENTIMETER(0.393701 / 12.0);
    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public String getUnitName() {
        return name();
    }
}
