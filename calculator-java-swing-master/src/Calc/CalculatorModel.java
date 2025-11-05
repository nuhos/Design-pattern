package Calc;

public class CalculatorModel implements ICalculatorModel {

    private static CalculatorModel instance = null;

    private String currentOperand = "";
    private String previousOperand = "";
    private appOperation operation = null;
    private String operationString = "";

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
        operationString = "";
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
        if (currentOperand.equals("") && !previousOperand.equals("")) {
            operation = OperationFactory(op);
            operationString = op;
            return;
        }

        if (currentOperand.equals("")) return;

        if (!previousOperand.equals("")) {
            compute();
        }

        operation = OperationFactory(op);
        operationString = op;
        previousOperand = currentOperand;
        currentOperand = "";
    }

    @Override
    public void compute() {
        if (currentOperand.equals("") || previousOperand.equals("")) return;

        double num1 = Double.parseDouble(previousOperand);
        double num2 = Double.parseDouble(currentOperand);

        double result = operation.calculate(num1, num2);

        currentOperand = String.valueOf(result);
        previousOperand = "";
        operation = null;
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
        if (previousOperand.equals("")) {
            return "";
        }
        return previousOperand + " " + operationString + " ";
    }

    @Override
    public appOperation OperationFactory(String operator) {
        switch (operator) {
            case "+":
                return new Addition();
            case "-":
                return new Subtraction();
            case "×":
                return new Multiplication();
            case "÷":
                return new Division();
            //added for scientific operations using Adapter pattern
            case "x²":
                return new ScientificAdapter("x²");
            case "^": 
                return new ScientificAdapter("^");
            case "sin":
            case "cos":
                return new ScientificAdapter(operator);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    // ✅ Adapter-style new operation
    /*public String square(String number) {
        try {
            double value = Double.parseDouble(number);
            double result = value * value;
            return String.valueOf(result);
        } catch (NumberFormatException e) {
            return "Error";
        }
    }*/

    // added method for performing unary operations like square and trigonometric functions
    @Override
    public void performUnaryOperation(String op) {
        if (currentOperand.isBlank() || currentOperand.equals("Error")) {
            return;
        }

        try {
            double curr = Double.parseDouble(currentOperand);
            appOperation unaryOp = OperationFactory(op);
            double result = unaryOp.calculate(curr, 0); 
            
            currentOperand = String.valueOf(result); 
        } catch (NumberFormatException e) {
            currentOperand = "Error";
        }
    }
}
