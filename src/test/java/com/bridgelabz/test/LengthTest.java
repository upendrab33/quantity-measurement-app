package com.bridgelabz.test;

import com.bridgelabz.Length;
import com.bridgelabz.QuantityMeasurementAppMain;
import org.junit.Test;

import static org.junit.Assert.*;

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
}
