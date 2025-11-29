package Calc;

public class ToggleSignCommand implements Command {

    private final ICalculatorModel model;

    public ToggleSignCommand(ICalculatorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.toggleSign();
    }
}