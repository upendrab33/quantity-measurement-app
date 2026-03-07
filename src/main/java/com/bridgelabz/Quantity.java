package com.bridgelabz;

import java.util.Objects;

public class Quantity <U extends IMeasurable>{

    private static final double EPSILON = 1e-6;
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }


    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(converted, targetUnit);
    }


    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base1 = unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(result, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        if (other == null) throw new IllegalArgumentException("Other quantity cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cannot subtract quantities of different categories");
        }

        double baseValueThis = unit.convertToBaseUnit(this.value);
        double baseValueOther = other.unit.convertToBaseUnit(other.value);
        double resultBase = baseValueThis - baseValueOther;
        double resultValue = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(resultValue), targetUnit);
    }

    public double divide(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException("Other quantity cannot be null");
        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cannot divide quantities of different categories");
        }

        double baseValueThis = unit.convertToBaseUnit(this.value);
        double baseValueOther = other.unit.convertToBaseUnit(other.value);

        if (Math.abs(baseValueOther) < EPSILON) {
            throw new ArithmeticException("Division by zero quantity");
        }

        return baseValueThis / baseValueOther;
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        // Critical: Prevent cross-category equality
        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Objects.hash(unit.getClass(), base);
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}
