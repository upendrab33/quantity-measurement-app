package com.bridgelabz.test;

import com.bridgelabz.Length;
import com.bridgelabz.QuantityMeasurementAppMain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;




public class LengthTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testFeetEquality(){
        Length length1 = new Length(5.0, Length.LengthUnit.FEET);
        Length length2 = new Length(5.0, Length.LengthUnit.FEET);
        assertTrue(length1.equals(length2));
    }
    @Test
    public void testInchesEquality(){
        Length length1 = new Length(30, Length.LengthUnit.INCHES);
        Length length2 = new Length(30, Length.LengthUnit.INCHES);
        assertTrue(length1.equals(length2));
    }
    @Test
    public void testFeetInchesComparison(){
        Length length1 = new Length(3.0, Length.LengthUnit.FEET);
        Length length2 = new Length(36, Length.LengthUnit.INCHES);
        assertTrue(length1.equals(length2));
    }

    @Test
    public void testFeetInequality(){
        Length length1 = new Length(5.0, Length.LengthUnit.FEET);
        Length length2 = new Length(7.0, Length.LengthUnit.FEET);
        assertFalse(length1.equals(length2));
    }
    @Test
    public void testInchesInequality(){
        Length length1 = new Length(30, Length.LengthUnit.INCHES);
        Length length2 = new Length(50, Length.LengthUnit.INCHES);
        assertFalse(length1.equals(length2));
    }
    @Test
    public void testCrossUnitInequality(){
        Length length1 = new Length(5.0, Length.LengthUnit.FEET);
        Length length2 = new Length(36, Length.LengthUnit.INCHES);
        assertFalse(length1.equals(length2));
    }
    @Test
    public void testNullComparison(){
        Length length1 = new Length(5.0, Length.LengthUnit.FEET);
        assertFalse(length1.equals(null));
    }
    @Test
    public void testConversion_FeetToInches() {
        double result = Length.convert(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        assertEquals(12.0, result);
    }

        @Test
        public void testConversion_InchesToFeet() {
            double result = Length.convert(24.0, Length.LengthUnit.INCHES, Length.LengthUnit.FEET);
            assertEquals(2.0, result);
        }

        @Test
        public void testConversion_YardsToInches() {
            double result = Length.convert(1.0, Length.LengthUnit.YARD, Length.LengthUnit.INCHES);
            assertEquals(36.0, result);
        }

        @Test
        public void testConversion_InchesToYards() {
            double result = Length.convert(72.0, Length.LengthUnit.INCHES, Length.LengthUnit.YARD);
            assertEquals(2.0, result);
        }

        @Test
        public void testConversion_CentimetersToInches() {
            double result = Length.convert(2.54, Length.LengthUnit.CENTIMETER, Length.LengthUnit.INCHES);
            assertEquals(1.0, result);
        }

        @Test
        public void testConversion_FeetToYards() {
            double result = Length.convert(6.0, Length.LengthUnit.FEET, Length.LengthUnit.YARD);
            assertEquals(2.0, result);
        }

        @Test
       public void testConversion_RoundTrip_PreservesValue() {
            double original = 123.456;

            double converted = Length.convert(original, Length.LengthUnit.FEET, Length.LengthUnit.YARD);
            double roundTrip = Length.convert(converted, Length.LengthUnit.YARD, Length.LengthUnit.FEET);

            assertEquals(original, roundTrip);
        }

        @Test
       public void testConversion_ZeroValue() {
            double result = Length.convert(0.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
            assertEquals(0.0, result);
        }

        @Test
        public void testConversion_NegativeValue() {
            double result = Length.convert(-1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
            assertEquals(-12.0, result);
        }

        @Test
       public void testConversion_InvalidUnit_Throws() {
            assertThrows(IllegalArgumentException.class, () ->
                    Length.convert(1.0, null, Length.LengthUnit.FEET)
            );

            assertThrows(IllegalArgumentException.class, () ->
                    Length.convert(1.0, Length.LengthUnit.FEET, null)
            );
        }

        @org.junit.jupiter.api.Test
       public  void testConversion_NaNOrInfinite_Throws() {
            assertThrows(IllegalArgumentException.class, () ->
                    Length.convert(Double.NaN, Length.LengthUnit.FEET, Length.LengthUnit.INCHES)
            );

            assertThrows(IllegalArgumentException.class, () ->
                    Length.convert(Double.POSITIVE_INFINITY, Length.LengthUnit.FEET, Length.LengthUnit.INCHES)
            );

            assertThrows(IllegalArgumentException.class, () ->
                    Length.convert(Double.NEGATIVE_INFINITY, Length.LengthUnit.FEET, Length.LengthUnit.INCHES)
            );

        }

        @Test
        public void testConversion_PrecisionTolerance() {
            double result = Length.convert(91.44, Length.LengthUnit.CENTIMETER, Length.LengthUnit.FEET);
            assertEquals(3.0, result);
        }



    // --------------------------------------------
    // Explicit Target Unit - FEET
    // --------------------------------------------
    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        Length result = Length.add(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.FEET
        );

        assertEquals(2.0, result.getInputValue(), EPSILON);
        assertEquals(Length.LengthUnit.FEET, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        Length result = Length.add(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.INCHES
        );

        assertEquals(24.0, result.getInputValue(), EPSILON);
        assertEquals(Length.LengthUnit.INCHES, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        Length result = Length.add(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.YARD
        );

        assertEquals(2.0 / 3.0, result.getInputValue(), EPSILON);
        assertEquals(Length.LengthUnit.YARD, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        Length result = Length.add(
                new Length(1.0, Length.LengthUnit.INCHES),
                new Length(1.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.CENTIMETER
        );

        assertEquals(5.08, result.getInputValue(), EPSILON);
        assertEquals(Length.LengthUnit.CENTIMETER, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        Length result = Length.add(
                new Length(2.0, Length.LengthUnit.YARD),
                new Length(3.0, Length.LengthUnit.FEET),
                Length.LengthUnit.YARD
        );

        assertEquals(3.0, result.getInputValue(), EPSILON);
        assertEquals(Length.LengthUnit.YARD, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        Length result = Length.add(
                new Length(2.0, Length.LengthUnit.YARD),
                new Length(3.0, Length.LengthUnit.FEET),
                Length.LengthUnit.FEET
        );

        assertEquals(9.0, result.getInputValue(), EPSILON);
        assertEquals(Length.LengthUnit.FEET, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);

        Length result1 = Length.add(a, b, Length.LengthUnit.YARD);
        Length result2 = Length.add(b, a, Length.LengthUnit.YARD);

        assertEquals(result1.getInputValue(), result2.getInputValue(), EPSILON);
        assertEquals(result1.getLengthUnit(), result2.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        Length result = Length.add(
                new Length(5.0, Length.LengthUnit.FEET),
                new Length(0.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.YARD
        );

        assertEquals(5.0 / 3.0, result.getInputValue(), EPSILON);
        assertEquals(Length.LengthUnit.YARD, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {
        Length result = Length.add(
                new Length(5.0, Length.LengthUnit.FEET),
                new Length(-2.0, Length.LengthUnit.FEET),
                Length.LengthUnit.INCHES
        );

        assertEquals(36.0, result.getInputValue(), EPSILON);
        assertEquals(Length.LengthUnit.INCHES, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                Length.add(
                        new Length(1.0, Length.LengthUnit.FEET),
                        new Length(12.0, Length.LengthUnit.INCHES),
                        null
                )
        );
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Length result = Length.add(
                new Length(1000.0, Length.LengthUnit.FEET),
                new Length(500.0, Length.LengthUnit.FEET),
                Length.LengthUnit.INCHES
        );

        assertEquals(18000.0, result.getInputValue(), EPSILON);
        assertEquals(Length.LengthUnit.INCHES, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        Length result = Length.add(
                new Length(12.0, Length.LengthUnit.INCHES),
                new Length(12.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.YARD
        );

        assertEquals(2.0 / 3.0, result.getInputValue(), EPSILON);
        assertEquals(Length.LengthUnit.YARD, result.getLengthUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_AllUnitCombinations() {
        for (Length.LengthUnit u1 : Length.LengthUnit.values()) {
            for (Length.LengthUnit u2 : Length.LengthUnit.values()) {
                for (Length.LengthUnit target : Length.LengthUnit.values()) {

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
                new Length(123.456, Length.LengthUnit.CENTIMETER),
                new Length(78.910, Length.LengthUnit.INCHES),
                Length.LengthUnit.FEET
        );

        Length result2 = Length.add(
                new Length(78.910, Length.LengthUnit.INCHES),
                new Length(123.456, Length.LengthUnit.CENTIMETER),
                Length.LengthUnit.FEET
        );

        assertEquals(result1.getInputValue(), result2.getInputValue(), EPSILON);
    }
}

