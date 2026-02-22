package com.bridgelabz;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class QuantityMeasurementAppMain {
    public static boolean demonstrateLengthEquality(Length length1, Length length2)
    {
        return length1.equals(length2);
    }

    public static void demonstrateLengthComparison(Double inputValue1, Length.LengthUnit lengthUnit1, Double inputValue2, Length.LengthUnit lengthUnit2){

        Length length1=new Length(inputValue1, lengthUnit1);
        Length length2=new Length(inputValue2, lengthUnit2);
        System.out.println("Length equality status: " +demonstrateLengthEquality(length1,length2));
    }
    public static void demonstrateLengthConversion(Double inputValue, Length.LengthUnit lengthUnit, Length.LengthUnit toUnit){
        System.out.println(Length.convert(inputValue,lengthUnit, toUnit));
    }
    public static void main(String[] args) {
        demonstrateLengthConversion(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, Length.LengthUnit.YARD, Length.LengthUnit.FEET);
        demonstrateLengthConversion(36.0, Length.LengthUnit.INCHES, Length.LengthUnit.YARD);
        demonstrateLengthConversion(1.0, Length.LengthUnit.CENTIMETER, Length.LengthUnit.INCHES);
        demonstrateLengthConversion(0.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
    }
}