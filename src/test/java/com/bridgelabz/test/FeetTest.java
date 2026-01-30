package com.bridgelabz.test;

import com.bridgelabz.QuantityMeasurementAppMain;
import org.junit.Test;

import static org.junit.Assert.*;

public class FeetTest {

    @Test
    public void testEqualitySameValue(){
        QuantityMeasurementAppMain.Feet f1=new QuantityMeasurementAppMain.Feet(2.0);
        QuantityMeasurementAppMain.Feet f2=new QuantityMeasurementAppMain.Feet(2.0);
        assertTrue(f1.equals(f2));
    }
    @Test
    public void testEqualityDifferentValue(){
        QuantityMeasurementAppMain.Feet f1=new QuantityMeasurementAppMain.Feet(2);
        QuantityMeasurementAppMain.Feet f2=new QuantityMeasurementAppMain.Feet(3.5);
        assertFalse(f1.equals(f2));
    }
    @Test
    public void testEqualityNullCheck(){
        QuantityMeasurementAppMain.Feet f=new QuantityMeasurementAppMain.Feet(5.3);
        assertFalse(f.equals(null));
    }
    @Test
    public  void testEqualityNonNumericValue(){
        QuantityMeasurementAppMain.Feet f=new QuantityMeasurementAppMain.Feet(7.3);
        String value= String.valueOf(f.getValue()).concat("abc");
        assertFalse(value.matches("\\d+"));
    }
    @Test
    public void testEqualitySameReference(){
        QuantityMeasurementAppMain.Feet f=new QuantityMeasurementAppMain.Feet(7.3);
        assertTrue(f.equals(f));
    }

}
