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

    public static void main(String[] args) {
        demonstrateLengthComparison(1.0, Length.LengthUnit.FEET, 12.0, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(1.0, Length.LengthUnit.YARD, 36.0, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(100.0, Length.LengthUnit.CENTIMETER, 39.3701, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(3.0, Length.LengthUnit.FEET, 1.0, Length.LengthUnit.YARD);
        demonstrateLengthComparison(30.48, Length.LengthUnit.CENTIMETER, 1.0, Length.LengthUnit.FEET);
    }
}