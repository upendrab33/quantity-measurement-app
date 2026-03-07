package com.bridgelabz.test;

import com.bridgelabz.IMeasurable;
import com.bridgelabz.LengthUnit;
import com.bridgelabz.Quantity;
import com.bridgelabz.WeightUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {

    @Test
    public void lengthFeetEqualsInches() {
        Quantity feet = new Quantity(1, LengthUnit.FEET);
        Quantity inches = new Quantity(12, LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
    }

    @Test
    public void lengthYardsEqualsFeet() {
        Quantity yards = new Quantity(1, LengthUnit.YARD);
        Quantity feet = new Quantity(3, LengthUnit.FEET);

        assertTrue(yards.equals(feet));
    }

    @Test
    public void weightKilogramEqualsGrams() {
        Quantity kg = new Quantity(1, WeightUnit.KILOGRAM);
        Quantity grams = new Quantity(1000, WeightUnit.GRAM);

        assertTrue(kg.equals(grams));
    }

    @Test
    public void weightPoundEqualsGrams() {
        Quantity pound = new Quantity(1, WeightUnit.POUND);
        Quantity grams = new Quantity(453.592, WeightUnit.GRAM);

        assertTrue(pound.equals(grams));
    }

    @Test
    public void convertLengthFeetToInches() {
        Quantity feet = new Quantity(1, LengthUnit.FEET);

        Quantity result = feet.convertTo(LengthUnit.INCHES);

        assertEquals(12, result.getValue(), 0.01);
    }

    @Test
    public void addLengthFeetAndInches() {
        Quantity feet = new Quantity(1, LengthUnit.FEET);
        Quantity inches = new Quantity(6, LengthUnit.INCHES);

        Quantity result = feet.add(inches);

        assertEquals(18, result.convertTo(LengthUnit.INCHES).getValue(), 0.01);
    }

    @Test
    public void addWeightKilogramsAndGrams() {
        Quantity kg = new Quantity(1, WeightUnit.KILOGRAM);
        Quantity grams = new Quantity(500, WeightUnit.GRAM);

        Quantity result = kg.add(grams);

        assertEquals(1.5, result.convertTo(WeightUnit.KILOGRAM).getValue(), 0.01);
    }


    @Test
    public void testGenericTypeSafetyWithWeight() {
        Quantity weight1 = new Quantity(1, WeightUnit.KILOGRAM);
        Quantity weight2 = new Quantity(500, WeightUnit.GRAM);

        assertTrue(weight1.add(weight2) != null);
    }

    @Test
    public void preventCrossTypeComparisonLengthVsWeight() {
        Quantity feet = new Quantity(1, LengthUnit.FEET);
        Quantity kg = new Quantity(1, WeightUnit.KILOGRAM);

        assertFalse(feet.equals(kg));
    }

    @Test
    public void preventAdditionLengthVsWeight() {
        Quantity feet = new Quantity(1, LengthUnit.FEET);
        Quantity kg = new Quantity(1, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> {
            feet.add(kg);
        });
    }
    @Test
    public void preventCrossTypeConversionLengthToWeight() {
        Quantity feet = new Quantity(1, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            feet.convertTo(WeightUnit.KILOGRAM);
        });
    }

    @Test
    public void addLengthYardsAndFeet() {
        Quantity yards = new Quantity(1, LengthUnit.YARD);
        Quantity feet = new Quantity(2, LengthUnit.FEET);

        Quantity result = yards.add(feet);

        assertEquals(5, result.convertTo(LengthUnit.FEET).getValue(), 0.01);
    }

    @Test
    public void backwardCompatibilityLengthFeetEqualsInches() {
        Quantity feet = new Quantity(1, LengthUnit.FEET);
        Quantity inches = new Quantity(12, LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
    }

    @Test
    public void backwardCompatibilityWeightKilogramEqualsGrams() {
        Quantity kg = new Quantity(1, WeightUnit.KILOGRAM);
        Quantity grams = new Quantity(1000, WeightUnit.GRAM);

        assertTrue(kg.equals(grams));
    }

    @Test
    public void backwardCompatibilityConvertLengthFeetToInches() {
        Quantity feet = new Quantity(1, LengthUnit.FEET);

        Quantity result = feet.convertTo(LengthUnit.INCHES);

        assertEquals(12, result.getValue(), 0.01);
    }

    @Test
    public void backwardCompatibilityConvertWeightKilogramsToGrams() {
        Quantity kg = new Quantity(2, WeightUnit.KILOGRAM);

        Quantity result = kg.convertTo(WeightUnit.GRAM);

        assertEquals(2000, result.getValue(), 0.01);
    }

    @Test
    public void backwardCompatibilityAddLengthInSameUnit() {
        Quantity feet1 = new Quantity(3, LengthUnit.FEET);
        Quantity feet2 = new Quantity(2, LengthUnit.FEET);

        Quantity result = feet1.add(feet2);

        assertEquals(5, result.getValue(), 0.01);
    }

    @Test
    public void backwardCompatibilityAddWeightInSameUnit() {
        Quantity grams1 = new Quantity(200, WeightUnit.GRAM);
        Quantity grams2 = new Quantity(300, WeightUnit.GRAM);

        Quantity result = grams1.add(grams2);

        assertEquals(500, result.getValue(), 0.01);
    }

    @Test
    public void backwardCompatibilityLengthYardsEqualsFeet() {
        Quantity yards = new Quantity(1, LengthUnit.YARD);
        Quantity feet = new Quantity(3, LengthUnit.FEET);

        assertTrue(yards.equals(feet));
    }

    @Test
    public void backwardCompatibilityWeightPoundEqualsGrams() {
        Quantity pound = new Quantity(1, WeightUnit.POUND);
        Quantity grams = new Quantity(453.592, WeightUnit.GRAM);

        assertTrue(pound.equals(grams));
    }

    @Test
    public void backwardCompatibilityChainedAdditionsLength() {
        Quantity feet = new Quantity(1, LengthUnit.FEET);
        Quantity inches = new Quantity(6, LengthUnit.INCHES);
        Quantity yards = new Quantity(1, LengthUnit.YARD);

        Quantity result = feet.add(inches).add(yards);

        assertEquals(54, result.convertTo(LengthUnit.INCHES).getValue(), 0.01);
    }

}
