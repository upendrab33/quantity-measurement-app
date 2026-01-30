package com.bridgelabz;

import java.util.Scanner;

public class QuantityMeasurementAppMain {

    public static class Feet{
        private final double value;
        public Feet(double value)
        {
            this.value=value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if(this==obj)
                return true;
            if(obj==null || getClass()!=obj.getClass())
                return false;
            Feet f=(Feet) obj;
            return Double.compare(f.value, value)==0;
        }
    }
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter first value:");
        double feetValue1=sc.nextDouble();
        System.out.println("Enter second value:");
        double feetValue2=sc.nextDouble();
        Feet f1=new Feet(feetValue1);
        Feet f2=new Feet(feetValue2);
        if(f1.equals(f2))
        {
            System.out.println("The result of equality check:" +true);
        }else{
            System.out.println("The result of equality check:" +false);
        }
    }
}