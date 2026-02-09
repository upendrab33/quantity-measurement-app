package com.bridgelabz;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class QuantityMeasurementAppMain {
    public static boolean demonstratelengthEquality(Length length1, Length length2)
    {
        return length1.equals(length2);
    }
    public static void demonstrateFeetEquality(){
        Length length1=new Length(2.0, Length.LengthUnit.FEET);
        Length length2=new Length(2.0, Length.LengthUnit.FEET);
        System.out.println("Feet equality status: " +demonstratelengthEquality(length1,length2));
    }
    public static void demonstrateInchesEquality(){
        Length length1=new Length(30, Length.LengthUnit.INCHES);
        Length length2=new Length(30, Length.LengthUnit.INCHES);
        System.out.println("Inches equality status: " +demonstratelengthEquality(length1,length2));

    }
    public static void demonstrateFeetInchesComparison(){

        Length length1=new Length(2.0, Length.LengthUnit.FEET);
        Length length2=new Length(24, Length.LengthUnit.INCHES);
        System.out.println("Feet to Inches equality status: " +demonstratelengthEquality(length1,length2));
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }
}