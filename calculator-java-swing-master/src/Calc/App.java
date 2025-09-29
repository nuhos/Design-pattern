package Calc;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ICalculatorModel model = new CalculatorModel();
            CalculatorView view = new CalculatorView();
            CalculatorController controller = new CalculatorController(model, view);
            view.setController(controller);
            view.setVisible(true);
        });
    }
}