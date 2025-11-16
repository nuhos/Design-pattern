package Calc;

import javax.swing.SwingUtilities;
import java.util.Locale;

public class App {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // force western digits

        SwingUtilities.invokeLater(() -> {
            CalculatorFacade facade = CalculatorFacade.getInstance();
            facade.launchCalculator();
            System.out.println("Calculator app started successfully.");
        });
    }
}

