package Calc;

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
        if (currentOperand.equals("") && !previousOperand.equals("")) {
            return;
        }

        if (!previousOperand.equals("")) {
            compute();
        }
        operation = OperationFactory.getOperation(op);
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
        return previousOperand;
    }
}
