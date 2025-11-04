package Calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController implements ActionListener {

    private static CalculatorController instance = null;

public class CalculatorController {

    private static CalculatorController instance = null;

    private final ICalculatorModel model;
    private final CalculatorView view;

    private CalculatorController(ICalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public static CalculatorController getInstance(ICalculatorModel model, CalculatorView view) {
        if (instance == null) instance = new CalculatorController(model, view);

        if (instance == null) {
            instance = new CalculatorController(model, view);
        }
        return instance;
    }

    private void refresh() {
        view.updateDisplay(model.getCurrentText(), model.getPreviousText());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd == null) return;

        if (cmd.matches("\\d")) { model.appendNumber(cmd); refresh(); return; }

        switch (cmd) {
            case ".": model.appendDot(); break;
            case "C": model.clear(); break;
            case "←": model.deleteLast(); break;
            case "+/-": model.toggleSign(); break;
            case "=": model.compute(); break;
            default: model.chooseOperation(cmd); break;
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
