package com.bridgelabz.test;

import com.bridgelabz.Length;
import com.bridgelabz.LengthUnit;
import com.bridgelabz.QuantityMeasurementAppMain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LengthTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testFeetEquality(){
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(5.0, LengthUnit.FEET);
        assertTrue(length1.equals(length2));
    }
    @Test
    public void testInchesEquality(){
        Length length1 = new Length(30, LengthUnit.INCHES);
        Length length2 = new Length(30, LengthUnit.INCHES);
        assertTrue(length1.equals(length2));
    }
    @Test
    public void testFeetInchesComparison(){
        Length length1 = new Length(3.0, LengthUnit.FEET);
        Length length2 = new Length(36, LengthUnit.INCHES);
        assertTrue(length1.equals(length2));
    }

    @Test
    public void testFeetInequality(){
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(7.0, LengthUnit.FEET);
        assertFalse(length1.equals(length2));
    }
    @Test
    public void testInchesInequality(){
        Length length1 = new Length(30, LengthUnit.INCHES);
        Length length2 = new Length(50, LengthUnit.INCHES);
        assertFalse(length1.equals(length2));
    }
    @Test
    public void testCrossUnitInequality(){
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(36, LengthUnit.INCHES);
        assertFalse(length1.equals(length2));
    }
    @Test
    public void testNullComparison(){
        Length length1 = new Length(5.0, LengthUnit.FEET);
        assertFalse(length1.equals(null));
    }
    @Test
    public void testConversion_FeetToInches() {
        double result = Length.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(12.0, result);
    }

        @Test
        public void testConversion_InchesToFeet() {
            double result = Length.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET);
            assertEquals(2.0, result);
        }

        @Test
        public void testConversion_YardsToInches() {
            double result = Length.convert(1.0, LengthUnit.YARD, LengthUnit.INCHES);
            assertEquals(36.0, result);
        }

        @Test
        public void testConversion_InchesToYards() {
            double result = Length.convert(72.0, LengthUnit.INCHES, LengthUnit.YARD);
            assertEquals(2.0, result);
        }

        @Test
        public void testConversion_CentimetersToInches() {
            double result = Length.convert(2.54, LengthUnit.CENTIMETER, LengthUnit.INCHES);
            assertEquals(1.0, result);
        }

        @Test
        public void testConversion_FeetToYards() {
            double result = Length.convert(6.0, LengthUnit.FEET, LengthUnit.YARD);
            assertEquals(2.0, result);
        }

        @Test
       public void testConversion_RoundTrip_PreservesValue() {
            double original = 123.456;

            double converted = Length.convert(original, LengthUnit.FEET, LengthUnit.YARD);
            double roundTrip = Length.convert(converted, LengthUnit.YARD, LengthUnit.FEET);

            assertEquals(original, roundTrip);
        }

        @Test
       public void testConversion_ZeroValue() {
            double result = Length.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES);
            assertEquals(0.0, result);
        }

        @Test
        public void testConversion_NegativeValue() {
            double result = Length.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES);
            assertEquals(-12.0, result);
        }

        @Test
       public void testConversion_InvalidUnit_Throws() {
            assertThrows(IllegalArgumentException.class, () ->
                    Length.convert(1.0, null, LengthUnit.FEET)
            );

            assertThrows(IllegalArgumentException.class, () ->
                    Length.convert(1.0, LengthUnit.FEET, null)
            );
        }

        @org.junit.jupiter.api.Test
       public  void testConversion_NaNOrInfinite_Throws() {
            assertThrows(IllegalArgumentException.class, () ->
                    Length.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES)
            );

            assertThrows(IllegalArgumentException.class, () ->
                    Length.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCHES)
            );

            assertThrows(IllegalArgumentException.class, () ->
                    Length.convert(Double.NEGATIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCHES)
            );

        }

        @Test
        public void testConversion_PrecisionTolerance() {
            double result = Length.convert(91.44, LengthUnit.CENTIMETER, LengthUnit.FEET);
            assertEquals(3.0, result);
        }



    // --------------------------------------------
    // Explicit Target Unit - FEET
    // --------------------------------------------
    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        Length result = Length.add(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.FEET
        );

        assertEquals(2.0, result.getInputValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        Length result = Length.add(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES
        );

        assertEquals(24.0, result.getInputValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        Length result = Length.add(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.YARD
        );

        assertEquals(2.0 / 3.0, result.getInputValue(), EPSILON);
        assertEquals(LengthUnit.YARD, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        Length result = Length.add(
                new Length(1.0, LengthUnit.INCHES),
                new Length(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETER
        );

        assertEquals(5.08, result.getInputValue(), EPSILON);
        assertEquals(LengthUnit.CENTIMETER, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        Length result = Length.add(
                new Length(2.0, LengthUnit.YARD),
                new Length(3.0, LengthUnit.FEET),
                LengthUnit.YARD
        );

        assertEquals(3.0, result.getInputValue(), EPSILON);
        assertEquals(LengthUnit.YARD, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        Length result = Length.add(
                new Length(2.0, LengthUnit.YARD),
                new Length(3.0, LengthUnit.FEET),
                LengthUnit.FEET
        );

        assertEquals(9.0, result.getInputValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);

        Length result1 = Length.add(a, b, LengthUnit.YARD);
        Length result2 = Length.add(b, a, LengthUnit.YARD);

        assertEquals(result1.getInputValue(), result2.getInputValue(), EPSILON);
        assertEquals(result1.getLengthUnit(), result2.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        Length result = Length.add(
                new Length(5.0, LengthUnit.FEET),
                new Length(0.0, LengthUnit.INCHES),
                LengthUnit.YARD
        );

        assertEquals(5.0 / 3.0, result.getInputValue(), EPSILON);
        assertEquals(LengthUnit.YARD, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {
        Length result = Length.add(
                new Length(5.0, LengthUnit.FEET),
                new Length(-2.0, LengthUnit.FEET),
                LengthUnit.INCHES
        );

        assertEquals(36.0, result.getInputValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                Length.add(
                        new Length(1.0, LengthUnit.FEET),
                        new Length(12.0, LengthUnit.INCHES),
                        null
                )
        );
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Length result = Length.add(
                new Length(1000.0, LengthUnit.FEET),
                new Length(500.0, LengthUnit.FEET),
                LengthUnit.INCHES
        );

        assertEquals(18000.0, result.getInputValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        Length result = Length.add(
                new Length(12.0, LengthUnit.INCHES),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.YARD
        );

        assertEquals(2.0 / 3.0, result.getInputValue(), EPSILON);
        assertEquals(LengthUnit.YARD, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_AllUnitCombinations() {
        for (LengthUnit u1 : LengthUnit.values()) {
            for (LengthUnit u2 : LengthUnit.values()) {
                for (LengthUnit target : LengthUnit.values()) {

                    Length a = new Length(10.0, u1);
                    Length b = new Length(5.0, u2);

                    Length result = Length.add(a, b, target);

                    // Verify physical correctness in base unit
                    double expectedFeet =
                            u1.toBase(10.0) + u2.toBase(5.0);

                    double resultFeet =
                            result.getLengthUnit().toBase(result.getInputValue());

                    assertEquals(expectedFeet, resultFeet, EPSILON);
                }
            }
        }
    }

    @Test
    void testAddition_ExplicitTargetUnit_PrecisionTolerance() {
        Length result1 = Length.add(
                new Length(123.456, LengthUnit.CENTIMETER),
                new Length(78.910, LengthUnit.INCHES),
                LengthUnit.FEET
        );

        Length result2 = Length.add(
                new Length(78.910, LengthUnit.INCHES),
                new Length(123.456, LengthUnit.CENTIMETER),
                LengthUnit.FEET
        );

        assertEquals(result1.getInputValue(), result2.getInputValue(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0,
                LengthUnit.FEET.getConversionFactor(),
                EPSILON);
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0,
                LengthUnit.INCHES.getConversionFactor(),
                EPSILON);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0,
                LengthUnit.YARD.getConversionFactor(),
                EPSILON);
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0 / 30.48,
                LengthUnit.CENTIMETER.getConversionFactor(),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0,
                LengthUnit.FEET.toBase(5.0),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0,
                LengthUnit.INCHES.toBase(12.0),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0,
                LengthUnit.YARD.toBase(1.0),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0,
                LengthUnit.CENTIMETER.toBase(30.48),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0,
                LengthUnit.FEET.fromBase(2.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0,
                LengthUnit.INCHES.fromBase(1.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0,
                LengthUnit.YARD.fromBase(3.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48,
                LengthUnit.CENTIMETER.fromBase(1.0),
                EPSILON);
    }

    @Test
    void testQuantityLengthRefactored_Equality() {
        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);

        assertEquals(a, b);
    }

    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit() {
        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);

        Length result =
                Length.add(a, b, LengthUnit.YARD);

        assertEquals(2.0 / 3.0, result.getInputValue(), EPSILON);
        assertEquals(LengthUnit.YARD, result.getLengthUnit());
    }

    @Test
    void testQuantityLengthRefactored_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, null));
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testRoundTripConversion_RefactoredDesign() {
        double original = 42.0;

        double base = LengthUnit.YARD.toBase(original);
        double roundTrip =
                LengthUnit.YARD.fromBase(base);

        assertEquals(original, roundTrip, EPSILON);
    }

    @Test
    void testUnitImmutability() {
        LengthUnit unit = LengthUnit.FEET;

        assertEquals("FEET", unit.name());
        assertNotNull(unit);

        // Enums are inherently immutable and thread-safe.
        assertSame(LengthUnit.FEET, LengthUnit.valueOf("FEET"));
    }

}

