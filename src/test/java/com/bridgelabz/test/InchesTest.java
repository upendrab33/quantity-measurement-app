package com.bridgelabz.test;

import com.bridgelabz.QuantityMeasurementAppMain;
import org.junit.Test;

import static org.junit.Assert.*;

public class InchesTest {

    @Test
    public void testEqualitySameValue(){
        QuantityMeasurementAppMain.Inches inc1=new QuantityMeasurementAppMain.Inches(20);
        QuantityMeasurementAppMain.Inches inc2=new QuantityMeasurementAppMain.Inches(20);
        assertTrue(inc1.equals(inc2));
    }
    @Test
    public void testEqualityDifferentValue(){
        QuantityMeasurementAppMain.Inches inc1=new QuantityMeasurementAppMain.Inches(20);
        QuantityMeasurementAppMain.Inches inc2=new QuantityMeasurementAppMain.Inches(30);
        assertFalse(inc1.equals(inc2));
    }
    @Test
    public void testEqualityNullCheck(){
        QuantityMeasurementAppMain.Inches inc=new QuantityMeasurementAppMain.Inches(20);
        assertFalse(inc.equals(null));
    }
    @Test
    public  void testEqualityNonNumericValue(){
        QuantityMeasurementAppMain.Inches inc=new QuantityMeasurementAppMain.Inches(30);
        String value= String.valueOf(inc.getValue()).concat("abc");
        assertFalse(value.matches("\\d+"));
    }
    @Test
    public void testEqualitySameReference(){
        QuantityMeasurementAppMain.Inches inc=new QuantityMeasurementAppMain.Inches(30);
        assertTrue(inc.equals(inc));
    }

}
