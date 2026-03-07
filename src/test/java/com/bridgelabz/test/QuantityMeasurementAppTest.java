package com.bridgelabz.test;

import com.bridgelabz.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;

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

        //UC12 test cases
        @Test
        public void testSubtraction_SameUnit_FeetMinusFeet() {
            Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                    .subtract(new Quantity<>(5.0, LengthUnit.FEET));
            assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
        }

    @Test
    public void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(6.0, LengthUnit.INCHES));
        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }

    @Test
    public void testSubtraction_SameUnit_LitreMinusLitre() {
        Quantity<VolumeUnit> quantity1 = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(3.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = quantity1.subtract(quantity2);
        assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE), result);
    }
    @Test
    public void testSubtraction_CrossUnit_InchesMinusFeet() {
        Quantity<LengthUnit> quantityInches = new Quantity<>(120.0, LengthUnit.INCHES);
        Quantity<LengthUnit> quantityFeet = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = quantityInches.subtract(quantityFeet);
        assertEquals(new Quantity<>(60.0, LengthUnit.INCHES), result);
    }
    @Test
    public void testSubtraction_ExplicitTargetUnit_Feet() {
        Quantity<LengthUnit> quantityFeet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantityInches = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = quantityFeet.subtract(quantityInches, LengthUnit.FEET);
        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }
    @Test
    public void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> quantityFeet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantityInches = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = quantityFeet.subtract(quantityInches, LengthUnit.INCHES);
        assertEquals(new Quantity<>(114.0, LengthUnit.INCHES), result);
    }
    @Test
    public void testSubtraction_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> quantityLitres1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantityLitres2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = quantityLitres1.subtract(quantityLitres2, VolumeUnit.MILLILITRE);
        assertEquals(new Quantity<>(3000.0, VolumeUnit.MILLILITRE), result);
    }
    @Test
    public void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> quantityFeet1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantityFeet2 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = quantityFeet1.subtract(quantityFeet2);
        assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), result);
    }
    @Test
    public void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> quantityFeet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantityInches = new Quantity<>(120.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = quantityFeet.subtract(quantityInches);
        assertEquals(new Quantity<>(0.0, LengthUnit.FEET), result);
    }

    @Test
    public void testSubtraction_WithZeroOperand() {
        Quantity<LengthUnit> quantityFeet = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> zeroInches = new Quantity<>(0.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = quantityFeet.subtract(zeroInches);

        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
    }

    @Test
    public void testSubtraction_WithNegativeValues() {
        Quantity<LengthUnit> quantityFeet = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> negativeFeet = new Quantity<>(-2.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = quantityFeet.subtract(negativeFeet);

        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
    }

    @Test
    public void testSubtraction_WithLargeValues() {
        Quantity<WeightUnit> quantityKg = new Quantity<>(1e6, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> subtractKg = new Quantity<>(5e5, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = quantityKg.subtract(subtractKg);

        assertEquals(new Quantity<>(5e5, WeightUnit.KILOGRAM), result);
    }

    @Test
    public void testSubtraction_WithSmallValues() {
        Quantity<LengthUnit> quantityFeet = new Quantity<>(0.001, LengthUnit.FEET);
        Quantity<LengthUnit> subtractFeet = new Quantity<>(0.0005, LengthUnit.FEET);
        Quantity<LengthUnit> result = quantityFeet.subtract(subtractFeet);

        assertEquals(0.0005, result.getValue(), 1e-6);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testSubtraction_NullOperand() {
        Quantity<LengthUnit> quantityFeet = new Quantity<>(10.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> {
            quantityFeet.subtract(null);
        });
    }
    @Test
    public void testSubtraction_NullTargetUnit() {
        Quantity<LengthUnit> quantity1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(5.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            quantity1.subtract(quantity2, null);
        });
    }

    @Test
    public void testSubtraction_CrossCategory() {
        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> {
            length.subtract((Quantity<LengthUnit>) (Quantity<?>) weight);
        });
    }

    @Test
    public void testSubtraction_AllMeasurementCategories() {

        Quantity<LengthUnit> length1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> lengthResult = length1.subtract(length2);
        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), lengthResult);

        Quantity<WeightUnit> weight1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weight2 = new Quantity<>(500.0, WeightUnit.GRAM);
        Quantity<WeightUnit> weightResult = weight1.subtract(weight2);
        assertEquals(new Quantity<>(9.5, WeightUnit.KILOGRAM), weightResult);

        Quantity<VolumeUnit> volume1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(200.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> volumeResult = volume1.subtract(volume2);
        assertEquals(new Quantity<>(4.8, VolumeUnit.LITRE), volumeResult);
    }
    @Test
    public void testSubtraction_ChainedOperations() {
        Quantity<LengthUnit> initial = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> step1 = initial.subtract(new Quantity<>(2.0, LengthUnit.FEET));
        Quantity<LengthUnit> result = step1.subtract(new Quantity<>(1.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result, "Chained subtraction should return 7.0 FEET");
    }

    @Test
    public void testDivision_SameUnit_FeetDividedByFeet() {
        Quantity<LengthUnit> numerator = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> denominator = new Quantity<>(2.0, LengthUnit.FEET);

        double result = numerator.divide(denominator);
        assertEquals(5.0, result, 1e-6, "Division of same-unit feet should return 5.0");
    }

    @Test
    public void testDivision_SameUnit_LitreDividedByLitre() {
        Quantity<VolumeUnit> numerator = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> denominator = new Quantity<>(5.0, VolumeUnit.LITRE);

        double result = numerator.divide(denominator);
        assertEquals(2.0, result, 1e-6, "Division of same-unit litres should return 2.0");
    }

    @Test
    public void testDivision_CrossUnit_FeetDividedByInches() {
        Quantity<LengthUnit> numerator = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> denominator = new Quantity<>(2.0, LengthUnit.FEET);

        double result = numerator.divide(denominator);
        assertEquals(1.0, result, 1e-6, "Division across units (inches/feet) should correctly return 1.0");
    }
    @Test
    public void testDivision_CrossUnit_KilogramDividedByGram() {
        Quantity<WeightUnit> numerator = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> denominator = new Quantity<>(2000.0, WeightUnit.GRAM);

        double result = numerator.divide(denominator);
        assertEquals(1.0, result, 1e-6, "2 kg ÷ 2000 g should return 1.0");
    }

    @Test
    public void testDivision_RatioGreaterThanOne() {
        Quantity<LengthUnit> numerator = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> denominator = new Quantity<>(2.0, LengthUnit.FEET);

        double result = numerator.divide(denominator);
        assertEquals(5.0, result, 1e-6, "10 feet ÷ 2 feet should return 5.0 (ratio > 1)");
    }

    @Test
    public void testDivision_RatioLessThanOne() {
        Quantity<LengthUnit> numerator = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> denominator = new Quantity<>(10.0, LengthUnit.FEET);

        double result = numerator.divide(denominator);
        assertEquals(0.5, result, 1e-6, "5 feet ÷ 10 feet should return 0.5 (ratio < 1)");
    }
    @Test
    public void testDivision_RatioEqualToOne() {
        Quantity<LengthUnit> numerator = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> denominator = new Quantity<>(10.0, LengthUnit.FEET);

        double result = numerator.divide(denominator);
        assertEquals(1.0, result, 1e-6, "10 feet ÷ 10 feet should return 1.0");
    }

    @Test
    public void testDivision_NonCommutative() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        double resultAB = a.divide(b);
        double resultBA = b.divide(a);

        assertEquals(2.0, resultAB, 1e-6, "10 ÷ 5 should return 2.0");
        assertEquals(0.5, resultBA, 1e-6, "5 ÷ 10 should return 0.5");
    }

    @Test
    public void testDivision_ByZero() {
        Quantity<LengthUnit> numerator = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> denominator = new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> numerator.divide(denominator),
                "Division by zero should throw ArithmeticException");
    }

    @Test
    public void testDivision_WithLargeRatio() {
        Quantity<WeightUnit> numerator = new Quantity<>(1e6, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> denominator = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        double result = numerator.divide(denominator);
        assertEquals(1e6, result, 1e-2, "1e6 kg ÷ 1 kg should return 1e6");
    }

    @Test
    public void testDivision_WithSmallRatio() {
        Quantity<WeightUnit> numerator = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> denominator = new Quantity<>(1e6, WeightUnit.KILOGRAM);

        double result = numerator.divide(denominator);
        assertEquals(1e-6, result, 1e-12, "1 kg ÷ 1e6 kg should return 1e-6");
    }
    @Test
    public void testDivision_NullOperand() {
        Quantity<LengthUnit> quantity = new Quantity<>(10.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class,
                () -> quantity.divide(null),
                "Dividing by null should throw IllegalArgumentException");
    }

    @Test
    public void testDivision_AllMeasurementCategories() {
        Quantity<LengthUnit> length = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> lengthFeet = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(1.0, length.divide(lengthFeet), 1e-6);

        Quantity<WeightUnit> weightKg = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weightG = new Quantity<>(2000.0, WeightUnit.GRAM);
        assertEquals(1.0, weightKg.divide(weightG), 1e-6);

        Quantity<VolumeUnit> volumeL = new Quantity<>(3.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volumeMl = new Quantity<>(3000.0, VolumeUnit.MILLILITRE);
        assertEquals(1.0, volumeL.divide(volumeMl), 1e-6);
    }

    @Test
    public void testSubtractionAndDivision_Integration() {
        Quantity<LengthUnit> total = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> subtract = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> divisor = new Quantity<>(4.0, LengthUnit.FEET);

        double result = total.subtract(subtract).divide(divisor);
        assertEquals(2.0, result, 1e-6, "Subtraction and division integration works correctly");
    }
    @Test
    public void testSubtractionAddition_Inverse() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(3.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = a.add(b).subtract(b);

        assertEquals(a.getValue(), result.getValue(), 1e-6,
                "Adding and then subtracting the same quantity returns the original value");
    }

    @Test
    public void testSubtraction_Immutability() {
        Quantity<LengthUnit> original = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> operand = new Quantity<>(3.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = original.subtract(operand);

        assertEquals(10.0, original.getValue(), 1e-6, "Original quantity remains unchanged after subtraction");
        assertEquals(7.0, result.getValue(), 1e-6, "Subtracted result is correct");
    }

    @Test
    public void testDivision_Immutability() {
        Quantity<LengthUnit> original = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> operand = new Quantity<>(2.0, LengthUnit.FEET);
        double result = original.divide(operand);

        assertEquals(10.0, original.getValue(), 1e-6, "Original quantity remains unchanged after division");
        assertEquals(5.0, result, 1e-6, "Division result is correct");
    }

    @Test
    public void testSubtraction_PrecisionAndRounding() {
        Quantity<LengthUnit> quantity1 = new Quantity<>(10.005, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(3.004, LengthUnit.FEET);
        Quantity<LengthUnit> result = quantity1.subtract(quantity2);

        assertEquals(7.00, result.getValue(), 1e-2, "Subtraction result is rounded to two decimal places");
    }

    @Test
    public void testDivision_PrecisionHandling() {
        Quantity<LengthUnit> quantity1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(3.0, LengthUnit.FEET);
        double result = quantity1.divide(quantity2);

        assertEquals(3.333333, result, 1e-6, "Division result maintains floating-point precision");
    }

    //UC13 test cases
    @Test
    void testRefactoring_Add_DelegatesViaHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(3.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(8.0, result.getValue(), 0.001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }
    @Test
    void testRefactoring_Subtract_DelegatesViaHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(4.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(6.0, result.getValue(), 0.001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }
    @Test
    void testRefactoring_Divide_DelegatesViaHelper() {
        Quantity<WeightUnit> q1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        double result = q1.divide(q2);

        assertEquals(5.0, result, 0.001);
    }
    @Test
    void testValidation_NullOperand_ConsistentAcrossOperations() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Exception addEx = assertThrows(IllegalArgumentException.class, () -> q1.add(null));
        Exception subEx = assertThrows(IllegalArgumentException.class, () -> q1.subtract(null));
        Exception divEx = assertThrows(IllegalArgumentException.class, () -> q1.divide(null));
        String expectedMessage = "Operand cannot be null";

        assertEquals(expectedMessage, addEx.getMessage());
        assertEquals(expectedMessage, subEx.getMessage());
        assertEquals(expectedMessage, divEx.getMessage());
    }
    @Test
    void testValidation_FiniteValue_ConsistentAcrossOperations() {
        Quantity<LengthUnit> qFinite = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> qNaN = new Quantity<>(Double.NaN, LengthUnit.FEET);
        Quantity<LengthUnit> qInfinite = new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> qFinite.add(qNaN));
        assertThrows(IllegalArgumentException.class, () -> qFinite.subtract(qNaN));
        assertThrows(IllegalArgumentException.class, () -> qFinite.divide(qNaN));

        assertThrows(IllegalArgumentException.class, () -> qFinite.add(qInfinite));
        assertThrows(IllegalArgumentException.class, () -> qFinite.subtract(qInfinite));
        assertThrows(IllegalArgumentException.class, () -> qFinite.divide(qInfinite));
    }
    @Test
    void testValidation_NullTargetUnit_AddSubtractReject() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.add(q2, null));
        assertThrows(IllegalArgumentException.class, () -> q1.subtract(q2, null));
    }
    @Test
    void testArithmeticOperation_Add_EnumComputation() {
        double result = ArithmeticOperation.ADD.compute(10.0, 5.0);
        assertEquals(15.0, result, 0.001);
    }
    @Test
    void testArithmeticOperation_Subtract_EnumComputation() {
        double result = ArithmeticOperation.SUBTRACT.compute(10.0, 5.0);
        assertEquals(5.0, result, 0.001);
    }
    @Test
    void testArithmeticOperation_Divide_EnumComputation() {
        double result = ArithmeticOperation.DIVIDE.compute(10.0, 5.0);
        assertEquals(2.0, result, 0.001);
    }
    @Test
    void testArithmeticOperation_DivideByZero_EnumThrows() {
        assertThrows(ArithmeticException.class, () -> ArithmeticOperation.DIVIDE.compute(10.0, 0.0));
    }
    @Test
    void testPerformBaseArithmetic_ConversionAndOperation() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        double addResult = q1.performBaseArithmetic(q2, ArithmeticOperation.ADD);
        double expectedAdd = 10.0 + (6.0 / 12.0); // inches to feet
        assertEquals(expectedAdd, addResult, 0.001);

        double subResult = q1.performBaseArithmetic(q2, ArithmeticOperation.SUBTRACT);
        double expectedSub = 10.0 - (6.0 / 12.0);
        assertEquals(expectedSub, subResult, 0.001);

        double divResult = q1.performBaseArithmetic(q2, ArithmeticOperation.DIVIDE);
        double expectedDiv = 10.0 / (6.0 / 12.0);
        assertEquals(expectedDiv, divResult, 0.001);
    }
    @Test
    void testRounding_AddSubtract_TwoDecimalPlaces() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.1234, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.5678, LengthUnit.FEET);

        Quantity<LengthUnit> addResult = q1.add(q2);
        assertEquals(12.69, addResult.getValue(), 0.001); // Rounded to 2 decimals

        Quantity<LengthUnit> subResult = q1.subtract(q2);
        assertEquals(7.56, subResult.getValue(), 0.001); // Rounded to 2 decimals
    }
    @Test
    void testRounding_Divide_NoRounding() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(3.0, LengthUnit.FEET);

        double result = q1.divide(q2);
        assertEquals(3.3333333333333335, result, 1e-15); // Raw double, no rounding
    }
    @Test
    void testImplicitTargetUnit_AddSubtract() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> addResult = q1.add(q2); // no target unit specified
        assertEquals(10.5, addResult.getValue(), 0.001);
        assertEquals(LengthUnit.FEET, addResult.getUnit()); // Implicit: uses first operand's unit

        Quantity<LengthUnit> subResult = q1.subtract(q2);
        assertEquals(9.5, subResult.getValue(), 0.001);
        assertEquals(LengthUnit.FEET, subResult.getUnit()); // Implicit: uses first operand's unit
    }
    @Test
    void testExplicitTargetUnit_AddSubtract_Overrides() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> addResult = q1.add(q2, LengthUnit.INCHES);
        assertEquals(126.0, addResult.getValue(), 0.001); // Converted to explicit target unit
        assertEquals(LengthUnit.INCHES, addResult.getUnit());

        Quantity<LengthUnit> subResult = q1.subtract(q2, LengthUnit.INCHES);
        assertEquals(114.0, subResult.getValue(), 0.001); // Converted to explicit target unit
        assertEquals(LengthUnit.INCHES, subResult.getUnit());
    }
    @Test
    void testImmutability_AfterAdd_ViaCentralizedHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2);

        // Original operands should remain unchanged
        assertEquals(10.0, q1.getValue(), 0.001);
        assertEquals(LengthUnit.FEET, q1.getUnit());

        assertEquals(6.0, q2.getValue(), 0.001);
        assertEquals(LengthUnit.INCHES, q2.getUnit());
    }
    @Test
    void testImmutability_AfterSubtract_ViaCentralizedHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(10.0, q1.getValue(), 0.001);
        assertEquals(LengthUnit.FEET, q1.getUnit());

        assertEquals(6.0, q2.getValue(), 0.001);
        assertEquals(LengthUnit.INCHES, q2.getUnit());
    }
    @Test
    void testImmutability_AfterDivide_ViaCentralizedHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(10.0, q1.getValue(), 0.001);
        assertEquals(LengthUnit.FEET, q1.getUnit());

        assertEquals(5.0, q2.getValue(), 0.001);
        assertEquals(LengthUnit.FEET, q2.getUnit());
    }
    @Test
    void testAllOperations_AcrossAllCategories() {
        Quantity<LengthUnit> length1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(6.0, LengthUnit.INCHES);
        assertDoesNotThrow(() -> length1.add(length2));
        assertDoesNotThrow(() -> length1.subtract(length2));
        assertDoesNotThrow(() -> length1.divide(length2));

        Quantity<WeightUnit> weight1 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weight2 = new Quantity<>(500.0, WeightUnit.GRAM);
        assertDoesNotThrow(() -> weight1.add(weight2));
        assertDoesNotThrow(() -> weight1.subtract(weight2));
        assertDoesNotThrow(() -> weight1.divide(weight2));

        Quantity<VolumeUnit> volume1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(200.0, VolumeUnit.MILLILITRE);
        assertDoesNotThrow(() -> volume1.add(volume2));
        assertDoesNotThrow(() -> volume1.subtract(volume2));
        assertDoesNotThrow(() -> volume1.divide(volume2));
    }
    @Test
    void testCodeDuplication_ValidationLogic_Eliminated() throws Exception {
        boolean validationFoundOutsideHelper = Arrays.stream(Quantity.class.getDeclaredMethods())
                .filter(m -> !m.getName().equals("validateArithmeticOperands"))
                .anyMatch(m -> Arrays.stream(m.getParameterTypes())
                        .anyMatch(p -> p == Quantity.class));

        assertFalse(validationFoundOutsideHelper,
                "Validation logic should only exist in centralized helper method.");
    }
    @Test
    void testEnumDispatch_AllOperations_CorrectlyDispatched() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        assertEquals(15.0, q1.add(q2).getValue(), 0.001);
        assertEquals(5.0, q1.subtract(q2).getValue(), 0.001);
        assertEquals(2.0, q1.divide(q2), 0.001);
    }
    @Test
    void testErrorMessage_Consistency_Across_Operations() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> q2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        Exception addEx = assertThrows(IllegalArgumentException.class, () -> q1.add((Quantity) q2));
        Exception subEx = assertThrows(IllegalArgumentException.class, () -> q1.subtract((Quantity) q2));
        Exception divEx = assertThrows(IllegalArgumentException.class, () -> q1.divide((Quantity) q2));

        String expectedMsg = "Quantities must be of the same measurement category";
        assertEquals(expectedMsg, addEx.getMessage());
        assertEquals(expectedMsg, subEx.getMessage());
        assertEquals(expectedMsg, divEx.getMessage());
    }
    @Test
    void testHelper_PrivateVisibility() throws NoSuchMethodException {
        // performBaseArithmetic should be private
        assertTrue(
                Modifier.isPrivate(
                        Quantity.class.getDeclaredMethod("performBaseArithmetic", Quantity.class, ArithmeticOperation.class).getModifiers()
                )
        );
    }
    @Test
    void testValidation_Helper_PrivateVisibility() throws NoSuchMethodException {
        assertTrue(
                Modifier.isPrivate(
                        Quantity.class.getDeclaredMethod("validateArithmeticOperands", Quantity.class, IMeasurable.class, boolean.class).getModifiers()
                )
        );
    }
    @Test
    void testArithmetic_Chain_Operations() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q3 = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> q4 = new Quantity<>(3.0, LengthUnit.FEET);

        double result = q1.add(q2).subtract(q3).divide(q4); // (10+5-2)/3 = 13/3 = 4.333...
        assertEquals(4.333, result, 0.001);
    }
    @Test
    void testEnumConstant_ADD_CorrectlyAdds() {
        double result = ArithmeticOperation.ADD.compute(7, 3);
        assertEquals(10.0, result, 0.0001);
    }
    @Test
    void testEnumConstant_SUBTRACT_CorrectlySubtracts() {
        double result = ArithmeticOperation.SUBTRACT.compute(7, 3);
        assertEquals(4.0, result, 0.0001);
    }
    @Test
    void testEnumConstant_DIVIDE_CorrectlyDivides() {
        double result = ArithmeticOperation.DIVIDE.compute(7, 2);
        assertEquals(3.5, result, 0.0001);
    }
    @Test
    void testHelper_BaseUnitConversion_Correct() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(24.0, LengthUnit.INCHES);
        double baseResult = q1.performBaseArithmetic(q2, ArithmeticOperation.SUBTRACT);
        assertEquals(8.0, baseResult, 0.0001);
    }
    @Test
    void testHelper_ResultConversion_Correct() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        double baseResult = q1.performBaseArithmetic(q2, ArithmeticOperation.SUBTRACT);
        double resultInInches = LengthUnit.INCHES.convertFromBaseUnit(baseResult);
        assertEquals(114.0, resultInInches, 0.0001);
    }
    @Test
    void testRefactoring_Validation_UnifiedBehavior() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = null;

        Exception exAdd = assertThrows(IllegalArgumentException.class, () -> q1.add(q2));
        Exception exSubtract = assertThrows(IllegalArgumentException.class, () -> q1.subtract(q2));
        Exception exDivide = assertThrows(IllegalArgumentException.class, () -> q1.divide(q2));

        assertEquals(exAdd.getMessage(), exSubtract.getMessage());
        assertEquals(exAdd.getMessage(), exDivide.getMessage());
    }

    //Test cases for Uc14
    @Test
    void testTemperatureEquality_CelsiusToCelsius_SameValue() {
        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        assertTrue(t1.equals(t2));
    }
    @Test
    void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
        Quantity<TemperatureUnit> t1 =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(t1.equals(t2));
    }
    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_0Celsius32Fahrenheit() {
        Quantity<TemperatureUnit> celsius =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(celsius.equals(fahrenheit));
    }
    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_100Celsius212Fahrenheit() {
        Quantity<TemperatureUnit> celsius =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(celsius.equals(fahrenheit));
    }
    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_Negative40Equal() {
        Quantity<TemperatureUnit> celsius =
                new Quantity<>(-40.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(celsius.equals(fahrenheit));
    }
    @Test
    void testTemperatureEquality_SymmetricProperty() {
        Quantity<TemperatureUnit> celsius =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(celsius.equals(fahrenheit));
        assertTrue(fahrenheit.equals(celsius));
    }
    @Test
    void testTemperatureEquality_ReflexiveProperty() {
        Quantity<TemperatureUnit> temperature =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        assertTrue(temperature.equals(temperature));
    }
    @Test
    void testTemperatureConversion_CelsiusToFahrenheit_VariousValues() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(-20.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t3 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        assertEquals(122.0,
                t1.convertTo(TemperatureUnit.FAHRENHEIT).getValue(), 0.001);

        assertEquals(-4.0,
                t2.convertTo(TemperatureUnit.FAHRENHEIT).getValue(), 0.001);

        assertEquals(212.0,
                t3.convertTo(TemperatureUnit.FAHRENHEIT).getValue(), 0.001);
    }
    @Test
    void testTemperatureConversion_FahrenheitToCelsius_VariousValues() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(122.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(-4.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> t3 =
                new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);

        assertEquals(50.0,
                t1.convertTo(TemperatureUnit.CELSIUS).getValue(), 0.001);

        assertEquals(-20.0,
                t2.convertTo(TemperatureUnit.CELSIUS).getValue(), 0.001);

        assertEquals(100.0,
                t3.convertTo(TemperatureUnit.CELSIUS).getValue(), 0.001);
    }
    @Test
    void testTemperatureConversion_RoundTrip_PreservesValue() {

        Quantity<TemperatureUnit> original =
                new Quantity<>(37.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> converted =
                original.convertTo(TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS);

        assertEquals(original.getValue(), converted.getValue(), 0.001);
    }
    @Test
    void testTemperatureConversion_SameUnit() {

        Quantity<TemperatureUnit> original =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> converted =
                original.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(25.0, converted.getValue(), 0.001);
        assertEquals(TemperatureUnit.CELSIUS, converted.getUnit());
    }
    @Test
    void testTemperatureConversion_ZeroValue() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                celsius.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(32.0, fahrenheit.getValue(), 0.001);
    }
    @Test
    void testTemperatureConversion_NegativeValues() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(-20.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                celsius.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(-4.0, fahrenheit.getValue(), 0.001);
    }
    @Test
    void testTemperatureConversion_LargeValues() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(1000.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                celsius.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(1832.0, fahrenheit.getValue(), 0.001);
    }
    @Test
    void testTemperatureUnsupportedOperation_Subtract() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class, () -> t1.subtract(t2));
    }
    @Test
    void testTemperatureUnsupportedOperation_ErrorMessage() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        UnsupportedOperationException exception =
                assertThrows(UnsupportedOperationException.class, () -> t1.add(t2));

        assertTrue(exception.getMessage().toLowerCase().contains("temperature"));
    }
    @Test
    void testTemperatureVsLengthIncompatibility() {

        Quantity<TemperatureUnit> temperature =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<LengthUnit> length =
                new Quantity<>(100.0, LengthUnit.FEET);

        assertFalse(temperature.equals(length));
    }
    @Test
    void testTemperatureVsWeightIncompatibility() {

        Quantity<TemperatureUnit> temperature =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<WeightUnit> weight =
                new Quantity<>(50.0, WeightUnit.KILOGRAM);

        assertFalse(temperature.equals(weight));
    }
    @Test
    void testTemperatureVsVolumeIncompatibility() {

        Quantity<TemperatureUnit> temperature =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        Quantity<VolumeUnit> volume =
                new Quantity<>(25.0, VolumeUnit.LITRE);

        assertFalse(temperature.equals(volume));
    }
    @Test
    void testIMeasurableInterface_Evolution_BackwardCompatible() {

        Quantity<LengthUnit> length1 = new Quantity<>(3.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(36.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = length1.add(length2);

        assertEquals(6.0, result.getValue(), 0.001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }
    @Test
    void testTemperatureUnit_NonLinearConversion() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                celsius.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(32.0, fahrenheit.getValue(), 0.001);
    }
    @Test
    void testTemperatureUnit_AllConstants() {

        assertNotNull(TemperatureUnit.CELSIUS);
        assertNotNull(TemperatureUnit.FAHRENHEIT);
    }
    @Test
    void testTemperatureUnit_NameMethod() {

        assertEquals("Celsius", TemperatureUnit.CELSIUS.getUnitName());
        assertEquals("Fahrenheit", TemperatureUnit.FAHRENHEIT.getUnitName());
    }
    @Test
    void testTemperatureUnit_ConversionFactor() {

        assertEquals(1.0, TemperatureUnit.CELSIUS.getConversionFactor(), 0.001);
    }
    @Test
    void testTemperatureNullUnitValidation() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(100.0, null);
        });
    }
    @Test
    void testTemperatureNullOperandValidation_InComparison() {

        Quantity<TemperatureUnit> temperature =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        assertFalse(temperature.equals(null));
    }
    @Test
    void testTemperatureDifferentValuesInequality() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        assertFalse(t1.equals(t2));
    }
    @Test
    void testTemperatureBackwardCompatibility_UC1_Through_UC13() {

        Quantity<LengthUnit> length1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = length1.add(length2);

        assertEquals(2.0, result.getValue(), 0.001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }
    @Test
    void testTemperatureConversionPrecision_Epsilon() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(37.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(98.6, TemperatureUnit.FAHRENHEIT);

        assertTrue(celsius.equals(fahrenheit));
    }
    @Test
    void testTemperatureConversionEdgeCase_VerySmallDifference() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(37.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(98.6001, TemperatureUnit.FAHRENHEIT);

        assertTrue(t1.equals(t2));
    }
    @Test
    void testTemperatureEnumImplementsIMeasurable() {

        assertTrue(IMeasurable.class.isAssignableFrom(TemperatureUnit.class));
    }
    @Test
    void testTemperatureCrossUnitAdditionAttempt() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(77.0, TemperatureUnit.FAHRENHEIT);

        assertThrows(UnsupportedOperationException.class,
                () -> celsius.add(fahrenheit));
    }
    @Test
    void testTemperatureValidateOperationSupport_MethodBehavior() {

        assertThrows(UnsupportedOperationException.class,
                () -> TemperatureUnit.CELSIUS.validateOperationSupport("addition"));
    }
    @Test
    void testTemperatureIntegrationWithGenericQuantity() {

        Quantity<TemperatureUnit> temperature =
                new Quantity<>(30.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> converted =
                temperature.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(86.0, converted.getValue(), 0.001);
        assertEquals(TemperatureUnit.FAHRENHEIT, converted.getUnit());
    }

}
