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

        // ===== CHANGED: معالجة العمليات اليونري (x², sin, cos) بشكل خاص =====
        if (op.equals("x²") || op.equals("sin") || op.equals("cos")) {
            if (currentOperand.isBlank() || currentOperand.equals("Error")) {
                return; // ما عندنا رقم نشتغل عليه
            }

            try {
                double a = Double.parseDouble(currentOperand);

                // نستخدم ScientificAdapter عن طريق الـ factory كالمعتاد
                operation = OperationFactory(op);
                operationString = op;

                double result = operation.calculate(a, 0); // الباراميتر الثاني مهمل في اليونري
                currentOperand = String.valueOf(result);

                // نرجع الحالة لوضع طبيعي
                previousOperand = "";
                operation = null;
                operationString = "";

            } catch (NumberFormatException e) {
                currentOperand = "Error";
                previousOperand = "";
                operation = null;
                operationString = "";
            }
            return; // مهم: عشان ما نخشه للمنطق الخاص بالعمليات الثنائية تحت
        }
        // ===== نهاية جزء العمليات اليونري =====

        // === نفس منطق العمليات الثنائية القديم (+, -, ×, ÷, ^) ===
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
        // العمليات الثنائية فقط تستخدم هنا
        if (operation == null) return;
        if (currentOperand.equals("") || previousOperand.equals("")) return;

        try {
            double num1 = Double.parseDouble(previousOperand);
            double num2 = Double.parseDouble(currentOperand);

            double result = operation.calculate(num1, num2);

            currentOperand = String.valueOf(result);
            previousOperand = "";
            operation = null;
            operationString = "";   // CHANGED: نفرّغ نص العملية بعد ما نخلص
        } catch (NumberFormatException e) {
            currentOperand = "Error";
            previousOperand = "";
            operation = null;
            operationString = "";
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
        if (previousOperand.equals("")) {
            return "";
        }
        return previousOperand + " " + operationString + " ";
    }

    @Override
    public appOperation OperationFactory(String operator) {
        switch (operator) {
            case "+":  return new Addition();
            case "-":  return new Subtraction();
            case "×":  return new Multiplication();
            case "÷":  return new Division();

            // Scientific operations via Adapter
            case "x²": return new ScientificAdapter("x²");
            case "^":  return new ScientificAdapter("^");
            case "sin":
            case "cos":
                return new ScientificAdapter(operator);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    // ===== CHANGED: نخليها بس تستدعي chooseOperation(op) =====
    @Override
    public void computeUnaryOperation(String op) {
        // كذا لو عندك Command خاص لليونري يستدعي هذه الميثود،
        // هي بدورها تستخدم نفس منطق chooseOperation لليونري
        chooseOperation(op);
    }
}
