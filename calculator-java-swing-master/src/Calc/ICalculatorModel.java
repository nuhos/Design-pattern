package Calc;

public interface ICalculatorModel {
    void clear();
    void appendNumber(String number);
    void appendDot();
    void chooseOperation(String operation);
    void compute();
    void deleteLast();
    void toggleSign();

    String getCurrentText();
    String getPreviousText();
}