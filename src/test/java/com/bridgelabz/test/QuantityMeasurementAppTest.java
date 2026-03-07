package com.bridgelabz.test;

import com.bridgelabz.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {
    private static final double EPSILON = 0.0001;
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



        @Test
        public void litreToLitre_SameValue() {
            assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                    .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
        }

        @Test
        public void litreToLitre_DifferentValue() {
            assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                    .equals(new Quantity<>(2.0, VolumeUnit.LITRE)));
        }

        @Test
        public void litreToMillilitre_EquivalentValue() {
            assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                    .equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
        }

        @Test
        public void millilitreToLitre_EquivalentValue() {
            assertTrue(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                    .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
        }

        @Test
        public void litreToGallon_EquivalentValue() {
            assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                    .equals(new Quantity<>(0.264172, VolumeUnit.GALLON)));
        }

        @Test
        public void gallonToLitre_EquivalentValue() {
            assertTrue(new Quantity<>(1.0, VolumeUnit.GALLON)
                    .equals(new Quantity<>(3.78541, VolumeUnit.LITRE)));
        }

        @Test
        public void volumeVsLength_Incompatible() {
            assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                    .equals(new Quantity<>(1.0, LengthUnit.FEET)));
        }

        @Test
        public void volumeVsWeight_Incompatible() {
            assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                    .equals(new Quantity<>(1.0, WeightUnit.KILOGRAM)));
        }

        @Test
        public void nullComparison() {
            assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE).equals(null));
        }

        @Test
        public void sameReference() {
            Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);
            assertTrue(q.equals(q));
        }

        @Test
        public void nullUnit() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Quantity<>(1.0, null));
        }

        @Test
        public void zeroValue() {
            assertTrue(new Quantity<>(0.0, VolumeUnit.LITRE)
                    .equals(new Quantity<>(0.0, VolumeUnit.MILLILITRE)));
        }

        @Test
        public void negativeVolume() {
            assertTrue(new Quantity<>(-1.0, VolumeUnit.LITRE)
                    .equals(new Quantity<>(-1000.0, VolumeUnit.MILLILITRE)));
        }

        @Test
        public void largeVolumeValue() {
            assertTrue(new Quantity<>(1000000.0, VolumeUnit.MILLILITRE)
                    .equals(new Quantity<>(1000.0, VolumeUnit.LITRE)));
        }

        @Test
        public void smallVolumeValue() {
            assertTrue(new Quantity<>(0.001, VolumeUnit.LITRE)
                    .equals(new Quantity<>(1.0, VolumeUnit.MILLILITRE)));
        }

        @Test
        public void litreToMillilitre() {
            Quantity<VolumeUnit> result =
                    new Quantity<>(1.0, VolumeUnit.LITRE)
                            .convertTo(VolumeUnit.MILLILITRE);

            assertEquals(1000.0, result.getValue(), EPSILON);
        }

        @Test
        public void millilitreToLitre() {
            Quantity<VolumeUnit> result =
                    new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                            .convertTo(VolumeUnit.LITRE);

            assertEquals(1.0, result.getValue(), EPSILON);
        }

        @Test
        public void gallonToLitre() {
            Quantity<VolumeUnit> result =
                    new Quantity<>(1.0, VolumeUnit.GALLON)
                            .convertTo(VolumeUnit.LITRE);

            assertEquals(3.78541, result.getValue(), EPSILON);
        }

        @Test
        public void litreToGallon() {
            Quantity<VolumeUnit> result =
                    new Quantity<>(3.78541, VolumeUnit.LITRE)
                            .convertTo(VolumeUnit.GALLON);

            assertEquals(1.0, result.getValue(), EPSILON);
        }

        @Test
        public void millilitreToGallon() {
            Quantity<VolumeUnit> result =
                    new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                            .convertTo(VolumeUnit.GALLON);

            assertEquals(0.264172, result.getValue(), EPSILON);
        }

        @Test
        public void sameUnit() {
            Quantity<VolumeUnit> result =
                    new Quantity<>(5.0, VolumeUnit.LITRE)
                            .convertTo(VolumeUnit.LITRE);

            assertEquals(5.0, result.getValue(), EPSILON);
        }

        @Test
        public void testConversion_ZeroValue() {
            Quantity<VolumeUnit> result =
                    new Quantity<>(0.0, VolumeUnit.LITRE)
                            .convertTo(VolumeUnit.MILLILITRE);

            assertEquals(0.0, result.getValue(), EPSILON);
        }

        @Test
        public void negativeValue() {
            Quantity<VolumeUnit> result =
                    new Quantity<>(-1.0, VolumeUnit.LITRE)
                            .convertTo(VolumeUnit.MILLILITRE);

            assertEquals(-1000.0, result.getValue(), EPSILON);
        }

        @Test
        public void roundTrip() {

            Quantity<VolumeUnit> result =
                    new Quantity<>(1.5, VolumeUnit.LITRE)
                            .convertTo(VolumeUnit.MILLILITRE)
                            .convertTo(VolumeUnit.LITRE);

            assertEquals(1.5, result.getValue(), EPSILON);
        }

        @Test
        public void sameUnit_LitrePlusLitre() {
            Quantity<VolumeUnit> result =
                    new Quantity<>(1.0, VolumeUnit.LITRE)
                            .add(new Quantity<>(2.0, VolumeUnit.LITRE));

            assertEquals(3.0, result.getValue(), EPSILON);
        }

        @Test
        public void sameUnit_MillilitrePlusMillilitre() {
            Quantity<VolumeUnit> result =
                    new Quantity<>(500.0, VolumeUnit.MILLILITRE)
                            .add(new Quantity<>(500.0, VolumeUnit.MILLILITRE));

            assertEquals(1000.0, result.getValue(), EPSILON);
        }

        @Test
        public void crossUnit_LitrePlusMillilitre() {

            Quantity<VolumeUnit> result =
                    new Quantity<>(1.0, VolumeUnit.LITRE)
                            .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

            assertEquals(2.0, result.getValue(), EPSILON);
        }

        @Test
        public void crossUnit_MillilitrePlusLitre() {

            Quantity<VolumeUnit> result =
                    new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                            .add(new Quantity<>(1.0, VolumeUnit.LITRE));

            assertEquals(2000.0, result.getValue(), EPSILON);
        }

        @Test
        public void crossUnit_GallonPlusLitre() {

            Quantity<VolumeUnit> result =
                    new Quantity<>(1.0, VolumeUnit.GALLON)
                            .add(new Quantity<>(3.78541, VolumeUnit.LITRE));

            assertEquals(2.0, result.getValue(), EPSILON);
        }

        @Test
        public void explicitTargetUnit_Litre() {

            Quantity<VolumeUnit> result =
                    new Quantity<>(1.0, VolumeUnit.LITRE)
                            .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                                    VolumeUnit.LITRE);

            assertEquals(2.0, result.getValue(), EPSILON);
        }

        @Test
        public void explicitTargetUnit_Millilitre() {

            Quantity<VolumeUnit> result =
                    new Quantity<>(1.0, VolumeUnit.LITRE)
                            .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                                    VolumeUnit.MILLILITRE);

            assertEquals(2000.0, result.getValue(), EPSILON);
        }

        @Test
        public void explicitTargetUnit_Gallon() {

            Quantity<VolumeUnit> result =
                    new Quantity<>(3.78541, VolumeUnit.LITRE)
                            .add(new Quantity<>(3.78541, VolumeUnit.LITRE),
                                    VolumeUnit.GALLON);

            assertEquals(2.0, result.getValue(), EPSILON);
        }

        @Test
        public void withZero() {

            Quantity<VolumeUnit> result =
                    new Quantity<>(5.0, VolumeUnit.LITRE)
                            .add(new Quantity<>(0.0, VolumeUnit.MILLILITRE));

            assertEquals(5.0, result.getValue(), EPSILON);
        }

        @Test
        public void testVolumeUnitEnum_LitreConstant() {
            assertEquals(1.0,
                    VolumeUnit.LITRE.getConversionFactor(),
                    EPSILON);
        }

        @Test
        public void testVolumeUnitEnum_MillilitreConstant() {
            assertEquals(0.001,
                    VolumeUnit.MILLILITRE.getConversionFactor(),
                    EPSILON);
        }

        @Test
        public void testVolumeUnitEnum_GallonConstant() {
            assertEquals(3.78541,
                    VolumeUnit.GALLON.getConversionFactor(),
                    EPSILON);
        }

}
