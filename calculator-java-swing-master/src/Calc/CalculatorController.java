package Calc;

public class CalculatorController {

    private static CalculatorController instance = null;

    private final CalculatorModel model;
    private final CalculatorView view;

    private CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public static CalculatorController getInstance(CalculatorModel model, CalculatorView view) {
        if (instance == null) {
            instance = new CalculatorController(model, view);
        }
        return instance;
    }

    private void refresh() {
        view.updateDisplay(model.getCurrentText(), model.getPreviousText());
    }


    public void onAppendNumber(String digit) {
        model.appendNumber(digit);
        refresh();
    }

    public void onAppendDot() {
        model.appendDot();
        refresh();
    }

    public void onChooseOperation(String op) {
        model.chooseOperation(op);
        refresh();
    }

    public void onCompute() {
        model.compute();
        refresh();

        if ("Error".equals(model.getCurrentText())) {
            model.clear();
            refresh();
        }
    }

    public void onClear() {
        model.clear();
        refresh();
    }

    public void onDeleteLast() {
        model.deleteLast();
        refresh();
    }

    public void onToggleSign() {
        model.toggleSign();
        refresh();
    }
}
