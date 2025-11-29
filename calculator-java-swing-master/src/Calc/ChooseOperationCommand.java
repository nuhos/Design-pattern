package Calc;

public class ChooseOperationCommand implements Command {

    private final ICalculatorModel model;
    private final String operator;

    public ChooseOperationCommand(ICalculatorModel model, String operator) {
        this.model = model;
        this.operator = operator;
    }

    @Override
    public void execute() {
        model.chooseOperation(operator);
    }
}