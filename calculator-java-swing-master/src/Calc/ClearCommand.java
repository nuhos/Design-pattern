package Calc;

public class ClearCommand implements Command {

    private final ICalculatorModel model;

    public ClearCommand(ICalculatorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.clear();
    }
}
