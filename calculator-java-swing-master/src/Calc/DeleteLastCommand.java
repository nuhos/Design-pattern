package Calc;

public class DeleteLastCommand implements Command {

    private final ICalculatorModel model;

    public DeleteLastCommand(ICalculatorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.deleteLast();
    }
}
