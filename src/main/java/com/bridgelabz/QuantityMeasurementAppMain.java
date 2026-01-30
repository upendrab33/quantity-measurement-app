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
            Feet f=(Feet) obj;
            return Double.compare(f.feetValue, feetValue)==0;
        }

        @Override
        public int hashCode(){
            return Objects.hash(feetValue);
        }
    }
    public static void main(String[] args) {
        try{
            Scanner sc= new Scanner(System.in);
            System.out.println("Enter first value:");
            double feetValue1=sc.nextDouble();
            System.out.println("Enter second value:");
            double feetValue2=sc.nextDouble();
            Feet f1=new Feet(feetValue1);
            Feet f2=new Feet(feetValue2);
            if(f1.equals(f2))
            {
                System.out.println("The result of equality check is:" +true);
            }else{
                System.out.println("The result of equality check is:" +false);
            }
        }catch (Exception ex){
            System.out.println("You entered a non-numeric, please enter numeric value");
            System.exit(1);
        }

    }
}