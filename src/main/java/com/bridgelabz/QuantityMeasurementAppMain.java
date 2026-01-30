package com.bridgelabz;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class QuantityMeasurementAppMain {

    public static class Feet{
        private final double feetValue;
        public Feet(double feetValue)
        {
            this.feetValue=feetValue;
        }

        public double getValue() {
            return feetValue;
        }

        @Override
        public boolean equals(Object obj) {
            if(this==obj)
                return true;
            if(obj==null || getClass()!=obj.getClass())
                return false;
            if(obj instanceof Inches){
                Inches inc=(Inches) obj;
                return Double.compare(inchesToFeet(inc.inchesValue), feetValue)==0;
            }
            Feet f=(Feet) obj;
            return Double.compare(f.feetValue, feetValue)==0;
        }

        @Override
        public int hashCode(){
            return Objects.hash(feetValue);
        }
    }

    public static class Inches{
        private final double inchesValue;
        public Inches(double inchesValue)
        {
            this.inchesValue=inchesValue;
        }

        public double getValue() {
            return inchesValue;
        }

        @Override
        public boolean equals(Object obj) {
            if(this==obj)
                return true;
            if(obj==null || getClass()!=obj.getClass())
                return false;
            if(obj instanceof Feet){
                Feet f=(Feet) obj;
                return Double.compare(feetToInches(f.feetValue), inchesValue)==0;
            }
            Inches inc=(Inches) obj;
            return Double.compare(inc.inchesValue, inchesValue)==0;
        }

        @Override
        public int hashCode(){
            return Objects.hash(inchesValue);
        }
    }

    public static void feetEqualityCheck(Double feetValue1, Double feetValue2){

        Feet f1=new Feet(feetValue1);
        Feet f2=new Feet(feetValue2);
        if(f1.equals(f2))
        {
            System.out.println("The result of feet equality check is:" +true);
        }else{
            System.out.println("The result of feet equality check is:" +false);
        }
    }

    public static void inchesEqualityCheck(Double inchesValue1, Double inchesValue2){

        Inches inc1=new Inches(inchesValue1);
        Inches inc2=new Inches(inchesValue2);
        if(inc1.equals(inc2))
        {
            System.out.println("The result of inches equality check is:" +true);
        }else{
            System.out.println("The result of inches equality check is:" +false);
        }
    }

    public static double feetToInches(Double feetValue){
        return feetValue*12;
    }
    public static double inchesToFeet(Double inchesValue){
        return inchesValue/12;
    }

    public static void main(String[] args) {
            Scanner sc= new Scanner(System.in);
            System.out.println("Enter first input value:");
            double inputValue1=sc.nextDouble();
            System.out.println("Enter second input value:");
            double inputValue2=sc.nextDouble();
            feetEqualityCheck(inputValue1, inputValue2);
            inchesEqualityCheck(inputValue1, inputValue2);
    }
}