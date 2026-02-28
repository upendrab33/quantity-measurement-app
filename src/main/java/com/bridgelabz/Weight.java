package com.bridgelabz;

import java.util.Objects;

public class Weight {

        private final double value;
        private final WeightUnit unit;

        public Weight(double value, WeightUnit unit) {
            validate(value, unit);
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public WeightUnit getUnit() {
            return unit;
        }

        public Weight convertTo(WeightUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double baseValue = unit.convertToBaseUnit(value);
            double converted = targetUnit.convertFromBaseUnit(baseValue);

            return new Weight(converted, targetUnit);
        }

        public Weight add(Weight other) {

            if (other == null) {
                throw new IllegalArgumentException("Weight cannot be null");
            }

            double sumBase =
                    this.unit.convertToBaseUnit(this.value) +
                            other.unit.convertToBaseUnit(other.value);

            double resultValue =
                    this.unit.convertFromBaseUnit(sumBase);

            return new Weight(resultValue, this.unit);
        }

        public static Weight add(
                Weight w1,
                Weight w2,
                WeightUnit targetUnit) {

            if (w1 == null || w2 == null) {
                throw new IllegalArgumentException("Weights cannot be null");
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double sumBase =
                    w1.unit.convertToBaseUnit(w1.value) +
                            w2.unit.convertToBaseUnit(w2.value);

            double resultValue =
                    targetUnit.convertFromBaseUnit(sumBase);

            return new Weight(resultValue, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Weight other = (Weight) obj;

            double thisBase = unit.convertToBaseUnit(value);
            double otherBase = other.unit.convertToBaseUnit(other.value);

            return Double.compare(thisBase, otherBase) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(unit.convertToBaseUnit(value));
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }

        private static void validate(double value, WeightUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite");
            }
        }
}
