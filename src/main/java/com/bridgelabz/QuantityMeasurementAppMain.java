package com.bridgelabz;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class QuantityMeasurementAppMain {

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(
            Quantity<U> quantity1, Quantity<U> quantity2) {
        if (quantity1 == null || quantity2 == null) {
            throw new IllegalArgumentException("Quantities cannot be null");
        }
        return quantity1.subtract(quantity2);
    }
    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(
            Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) {
        if (quantity1 == null || quantity2 == null || targetUnit == null) {
            throw new IllegalArgumentException("Quantities and target unit cannot be null");
        }
        return quantity1.subtract(quantity2, targetUnit);
    }

    public static <U extends IMeasurable> double demonstrateDivision(
            Quantity<U> quantity1, Quantity<U> quantity2) {
        if (quantity1 == null || quantity2 == null) {
            throw new IllegalArgumentException("Quantities cannot be null");
        }
        return quantity1.divide(quantity2);
    }

    public static void main(String[] args) {

        Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> diff1 = demonstrateSubtraction(v1, v2);
        System.out.println(diff1);

        Quantity<VolumeUnit> diff2 = demonstrateSubtraction(v1, v2, VolumeUnit.MILLILITRE);
        System.out.println(diff2);

        double ratio =demonstrateDivision(v1, v2);
        System.out.println(ratio);

    }
}