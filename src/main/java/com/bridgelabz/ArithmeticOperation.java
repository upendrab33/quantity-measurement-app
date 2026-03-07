package com.bridgelabz;

public enum ArithmeticOperation {
    ADD {
        @Override
        public double compute(double thisBase, double otherBase) {
            return thisBase + otherBase;
        }
    },
    SUBTRACT {
        @Override
        public double compute(double thisBase, double otherBase) {
            return thisBase - otherBase;
        }
    },
    DIVIDE {
        @Override
        public double compute(double thisBase, double otherBase) {
            if (otherBase == 0.0) {
                throw new ArithmeticException("Division by zero");
            }
            return thisBase / otherBase;
        }
    };

    public abstract double compute(double thisBase, double otherBase);
}
