package Calc;

public class Division implements appOperation {
    @Override
    public double calculate(double a, double b) {
        if (b == 0){
            throw new ArithmeticException("Division by zero is not allowed.");
        } 
        return a / b;
    }
}
