package com.bridgelabz.test;

import com.bridgelabz.Length;
import com.bridgelabz.QuantityMeasurementAppMain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertThrows;


public class LengthTest {

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
}
