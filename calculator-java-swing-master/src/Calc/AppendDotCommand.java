package Calc;

public class AppendDotCommand implements Command {

    private final ICalculatorModel model;

    public AppendDotCommand(ICalculatorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.appendDot();
    }
}