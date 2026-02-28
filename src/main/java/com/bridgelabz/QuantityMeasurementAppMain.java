package com.bridgelabz;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class QuantityMeasurementAppMain {
    public static void main(String[] args) {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);
        System.out.println(w1.equals(w2));

        Weight w3 = new Weight(1.0, WeightUnit.KILOGRAM);
        System.out.println(w3.convertTo(WeightUnit.GRAM));

        Weight w4 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w5 = new Weight(2.0, WeightUnit.KILOGRAM);
        System.out.println(w4.add(w5));

        Weight w6 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w7 = new Weight(1000.0, WeightUnit.GRAM);
        Weight result =
                Weight.add(w6, w7, WeightUnit.GRAM);
        System.out.println(result);

        Weight w = new Weight(1.0, WeightUnit.KILOGRAM);
        Length l = new Length(1.0, LengthUnit.FEET);
        boolean result1 = w.equals(l);
        System.out.println(result1);

    }
}