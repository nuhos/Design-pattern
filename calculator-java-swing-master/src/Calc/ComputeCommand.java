package Calc;

public class ComputeCommand implements Command {

    private final ICalculatorModel model;

    public ComputeCommand(ICalculatorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.compute();
    }
}

