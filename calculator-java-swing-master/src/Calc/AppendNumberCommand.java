package Calc;

public class AppendNumberCommand implements Command {

    private final ICalculatorModel model;
    private final String digit;

    public AppendNumberCommand(ICalculatorModel model, String digit) {
        this.model = model;
        this.digit = digit;
    }

    @Override
    public void execute() {
        model.appendNumber(digit);
    }
}