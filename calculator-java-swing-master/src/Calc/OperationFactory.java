package Calc;

public class OperationFactory {
    public static appOperation getOperation(String operator) {
        switch (operator) {
            case "+":
                return new Addition();
            case "-":
                return new Subtraction();
            case "×":
                return new Multiplication();
            case "÷":
                return new Division();
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
