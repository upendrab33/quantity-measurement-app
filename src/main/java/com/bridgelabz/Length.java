package com.bridgelabz;

public class Length {
    private final double inputValue;
    private final LengthUnit lengthUnit;
    public Length(double inputValue, LengthUnit lengthUnit)
    {
        this.inputValue=inputValue;
        this.lengthUnit=lengthUnit;
    }
    public enum LengthUnit{
        FEET(2.1),INCHES(3);
        private final double conversionFactor;
        LengthUnit(double baseUnit)
        {
            this.conversionFactor=baseUnit;
        }
        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    private double convertToBaseUnit(){
        return 0.0;
    }

    public boolean compare(Length length){

        return true;
    }

    @Override
    public boolean equals(Object obj){

        return true;
    }
}
