package Calc;

import javax.swing.SwingUtilities;
import java.util.Locale;

public class App {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // force western digits

        SwingUtilities.invokeLater(() -> {
            ICalculatorModel model = CalculatorModel.getInstance();
            CalculatorView view = new CalculatorView();
            CalculatorController controller = CalculatorController.getInstance(model, view);
            view.setController(controller);
            view.setVisible(true);
            view.setLocationRelativeTo(null);
            System.out.println("Calculator app started successfully.");
        });
    }
}

