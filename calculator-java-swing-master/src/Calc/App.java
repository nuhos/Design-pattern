package Calc;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorModel model = CalculatorModel.getInstance();
            CalculatorView view = new CalculatorView();
            CalculatorController controller = CalculatorController.getInstance(model, view);
            view.setController(controller);
            view.setVisible(true);
        });
    }
}