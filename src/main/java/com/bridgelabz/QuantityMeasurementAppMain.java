package com.bridgelabz;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class QuantityMeasurementAppMain {
    public static boolean demonstrateLengthEquality(Length length1, Length length2)
    {
        return length1.equals(length2);
    }

    public static void demonstrateLengthComparison(Double inputValue1, LengthUnit lengthUnit1, Double inputValue2, LengthUnit lengthUnit2){

        Length length1=new Length(inputValue1, lengthUnit1);
        Length length2=new Length(inputValue2, lengthUnit2);
        System.out.println("Length equality status: " +demonstrateLengthEquality(length1,length2));
    }
    public static void demonstrateLengthConversion(Double inputValue, LengthUnit lengthUnit, LengthUnit toUnit){
        System.out.println(Length.convert(inputValue,lengthUnit, toUnit));
    }
    public static void main(String[] args) {
        Length l = new Length(1, LengthUnit.FEET);
        Length l1 = new Length(12, LengthUnit.INCHES);

        Length result =
                Length.add(l, l1, LengthUnit.FEET);

        System.out.println(result);
    }
}