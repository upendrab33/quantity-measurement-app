package com.bridgelabz;

import java.util.Objects;

public class Length {
    private final double inputValue;
    private final LengthUnit lengthUnit;
    public Length(double inputValue, LengthUnit lengthUnit)
    {
        this.inputValue=inputValue;
        this.lengthUnit=lengthUnit;
    }
    public enum LengthUnit{
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
    private double convertToBaseUnit(){
        return inputValue * lengthUnit.getConversionFactor();
    }

    public boolean compare(Length length){
        return this.equals(length);
    }

    public static double convert(double value,
                                 LengthUnit sourceUnit,
                                 LengthUnit targetUnit) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite.");
        }

        if (sourceUnit == null || targetUnit == null) {
            throw new IllegalArgumentException("Units must not be null.");
        }

        // Convert to base (inches)
        double baseValue = sourceUnit.toBase(value);

        // Convert from base to target
        double convertedValue = targetUnit.fromBase(baseValue);

        return convertedValue;
    }

    public double getInputValue() {
        return inputValue;
    }

    public LengthUnit getLengthUnit() {
        return lengthUnit;
    }

    public static Length add(
            Length l1,
            Length l2,
            LengthUnit targetUnit) {

        if (l1 == null || l2 == null) {
            throw new IllegalArgumentException("Lengths cannot be null");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        validate(l1.inputValue, l1.lengthUnit);
        validate(l2.inputValue, l2.lengthUnit);

        // Convert both to base unit (feet)
        double l1Feet = l1.lengthUnit.toBase(l1.inputValue);
        double l2Feet = l2.lengthUnit.toBase(l2.inputValue);

        // Add in base unit
        double sumFeet = l1Feet + l2Feet;

        double resultValue = targetUnit.fromBase(sumFeet);

        return new Length(resultValue, targetUnit);
    }

    private static void validate(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("LengthUnit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
    }

    @Override
    public String toString() {
        return inputValue + " " + lengthUnit;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Length length=(Length) obj;
        return Double.compare(this.convertToBaseUnit(), length.convertToBaseUnit()) == 0;
    }

    @Override
    public int hashCode(){
        return Objects.hash(inputValue);
    }

}
