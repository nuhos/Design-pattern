package Calc;

import javax.swing.SwingUtilities;

public class  CalculatorFacade {
    private static CalculatorFacade instance;
    private ICalculatorModel model;
    private CalculatorView view;
    private CalculatorController controller;

    private CalculatorFacade() {
        model = CalculatorModel.getInstance();
        view = new CalculatorView();
        controller = CalculatorController.getInstance(model, view);
        view.setController(controller);
    }

    // make CalculatorFacade singleton
    public static CalculatorFacade getInstance() {
        if (instance == null) {
            instance = new CalculatorFacade();
        }
        return instance;
    }

    // Launch the calculator GUI
    public void launchCalculator() {
        SwingUtilities.invokeLater(() -> {
            view.setVisible(true);
            view.setLocationRelativeTo(null);
            System.out.println("Calculator launched through Facade.");
        });
    }

}
