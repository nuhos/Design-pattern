package Calc;

public class CalculatorModel implements ICalculatorModel {

    private static CalculatorModel instance = null;

    private String currentOperand = "";
    private String previousOperand = "";
    private String operation = "";

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
        operation = "";
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
            operation = op;
            return;
        }
        if (currentOperand.equals("")) return;

        if (!previousOperand.equals("")) {
            compute(); // يسمح بالحساب المتسلسل
        }
        operation = op;
        previousOperand = currentOperand;
        currentOperand = "";
    }

    @Override
    public void compute() {
        if (currentOperand.equals("") || previousOperand.equals("")) return;

        float curr;
        float prev;
        try {
            curr = Float.parseFloat(currentOperand);
            prev = Float.parseFloat(previousOperand);
        } catch (NumberFormatException ex) {
            return;
        }

        float result;
        switch (operation) {
            case "+" -> result = prev + curr;
            case "-" -> result = prev - curr;
            case "×" -> result = prev * curr;
            case "÷" -> {
                if (curr == 0) {
                    clear();
                    currentOperand = "Error";
                    return;
                }
                result = prev / curr;
            }
            default -> { return; }
        }

        // تنسيق النتيجة كعدد صحيح إن أمكن
        currentOperand = ((result - (int) result) != 0) ? Float.toString(result) : Integer.toString((int) result);
        previousOperand = "";
        operation = "";
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
        return previousOperand + " " + operation;
    }
}
