package Calc;

public interface ICalculatorModel {
    void clear();
    void appendNumber(String number);
    void appendDot();
    void chooseOperation(String operation);
    void compute();
    void deleteLast();
    void toggleSign();

    // New method for unary operations like square and trigonometric functions
    void computeUnaryOperation(String op);

    String getCurrentText();
    String getPreviousText();

    // Factory method to get operation based on operator string
    appOperation OperationFactory(String operator);

    // Observer pattern methods
    void attach(IObserver observer);
    void notifyObservers();
}