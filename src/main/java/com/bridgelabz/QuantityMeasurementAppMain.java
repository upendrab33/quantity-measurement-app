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

        System.out.println("\n=== Temperature Demonstration ===");

        // Equality Demonstration
        Quantity<TemperatureUnit> temp1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> temp2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        System.out.println("0°C equals 32°F: " + temp1.equals(temp2));

        // Conversion Demonstration
        Quantity<TemperatureUnit> celsius = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> fahrenheit = celsius.convertTo(TemperatureUnit.FAHRENHEIT);

        System.out.println("100°C = " + fahrenheit.getValue() + "°F");

        // Unsupported Operation Demonstration
        try {
            celsius.add(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot add absolute temperatures: " + e.getMessage());
        }

    }
}