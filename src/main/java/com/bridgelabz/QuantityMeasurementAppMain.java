package com.bridgelabz;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class QuantityMeasurementAppMain {

    public static void main(String[] args) {

        Quantity<VolumeUnit> volume1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> volume3 = new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("Equality:" +volume1.equals(volume2));
        System.out.println("Conversion:" +volume1.convertTo(VolumeUnit.MILLILITRE));
        System.out.println("Addition:" +volume1.add(volume2));
        System.out.println("Addition with target volume:" +volume1.add(volume2, VolumeUnit.LITRE));

    }
}