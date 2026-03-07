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
        this.unit.validateOperationSupport("add");
        other.unit.validateOperationSupport("add");
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);
        //return new Quantity<>(unit.convertFromBaseUnit(resultBase), unit);
        return new Quantity<>(unit.convertFromBaseUnit(resultBase),unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double resultInTarget = targetUnit.convertFromBaseUnit(resultBase);
        return new Quantity<>(round(resultInTarget), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double resultInTarget = targetUnit.convertFromBaseUnit(resultBase);
        return new Quantity<>(round(resultInTarget), targetUnit);
    }

    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {
        if (other == null) {
            throw new IllegalArgumentException("Operand cannot be null");
        }
        if (this.unit.getClass() != other.getUnit().getClass()) {
            throw new IllegalArgumentException("Cross-category arithmetic not allowed");
        }
        if (Double.isNaN(this.value) || Double.isInfinite(this.value) ||
                Double.isNaN(other.getValue()) || Double.isInfinite(other.getValue())) {
            throw new IllegalArgumentException("Values must be finite numbers");
        }
        if (targetUnitRequired && targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
    }

    public double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        // Convert both operands to base units
        double baseThis = this.unit.convertToBaseUnit(this.value);
        double baseOther = other.getUnit().convertToBaseUnit(other.getValue());

        // Perform the arithmetic operation using enum
        return operation.compute(baseThis, baseOther);
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
