package Calc;

import java.util.Locale;

public class CalculatorModel implements ICalculatorModel {

    private static CalculatorModel instance = null;

    private String currentOperand = "";
    private String previousOperand = "";
    private appOperation operation = null;

    private CalculatorModel() {}

    public static CalculatorModel getInstance() {
        if (instance == null) {
            instance = new CalculatorModel();
        }
        return instance;
    }

    @Override
    public void clear() {
        currentOperand = "";
        previousOperand = "";
        operation = null;
    }

    @Override
    public void appendNumber(String number) {
        if (currentOperand.equals("0") && number.equals("0")) return;
        if (currentOperand.equals("0") && !number.equals("0") && !number.equals(".")) {
            currentOperand = "";
        }
        currentOperand += number;
    }

    @Override
    public void appendDot() {
        if (currentOperand.isBlank()) {
            currentOperand = "0.";
            return;
        }
        if (!currentOperand.contains(".")) {
            currentOperand += ".";
        }
    }

    @Override
    public void chooseOperation(String op) {
        // === Adapter extension for sin, cos, log ===
        if (op.equals("sin") || op.equals("cos") || op.equals("log")) {
            // For these, user presses op first, then number, then =
            operation = OperationFactory(op);
            previousOperand = "";
            currentOperand = "";
            return;
        }

        // === Original calculator logic ===
        if (currentOperand.equals("") && !previousOperand.equals("")) {
            return;
        }

        if (!previousOperand.equals("")) {
            compute();
        }

        operation = OperationFactory(op);
        previousOperand = currentOperand;
        currentOperand = "";
    }

    @Override
    public void compute() {
        if (operation == null) return;

        try {
            double a;
            double b = 0;

            // handle unary ops
            if (previousOperand.isEmpty() && !currentOperand.isEmpty()) {
                a = Double.parseDouble(currentOperand);
            } else {
                a = Double.parseDouble(previousOperand);
                if (!currentOperand.isEmpty()) {
                    b = Double.parseDouble(currentOperand);
                }
            }

            double result = operation.calculate(a, b);
            currentOperand = formatResult(result);
            previousOperand = "";
            operation = null;

        } catch (Exception e) {
            currentOperand = "Error";
            previousOperand = "";
            operation = null;
        }
    }

    @Override
    public void deleteLast() {
        if (!currentOperand.equals("") && !currentOperand.equals("Error")) {
            currentOperand = currentOperand.substring(0, currentOperand.length() - 1);
        }
    }

    @Override
    public void toggleSign() {
        if (!currentOperand.isBlank() && !currentOperand.equals("Error")) {
            float tmp = -Float.parseFloat(currentOperand);
            currentOperand = ((tmp - (int) tmp) != 0) ? Float.toString(tmp) : Integer.toString((int) tmp);
        }
    }

    @Override
    public String getCurrentText() {
        return currentOperand;
    }

    @Override
    public String getPreviousText() {
        return previousOperand;
    }

    @Override
    public appOperation OperationFactory(String operator) {
        // original 4 ops untouched
        switch (operator) {
            case "+": return new Addition();
            case "-": return new Subtraction();
            case "x":
            case "×": return new Multiplication();
            case "/":
            case "÷": return new Division();

            // Adapter extension
            case "^": return new ScientificAdapter("power");
            case "log": return new ScientificAdapter("log");
            case "sin": return new ScientificAdapter("sin");
            case "cos": return new ScientificAdapter("cos");
            default: throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private String formatResult(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) return "Error";
        String s = String.format(Locale.US, "%.6f", value);
        return s.replaceAll("\\.?0+$", "");
    }
}
