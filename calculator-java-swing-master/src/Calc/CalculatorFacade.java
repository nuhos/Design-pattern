package Calc;

import javax.swing.SwingUtilities;

public class  CalculatorFacade {
    private static CalculatorFacade instance;
    private ICalculatorModel model;
    private CalculatorView view;
    private CalculatorController controller;

    private CalculatorFacade() {
        initializeSubsystems();

    }

    // make CalculatorFacade singleton
public static CalculatorFacade getInstance() {
        if (instance == null) {
            instance = new CalculatorFacade();
        }
        return instance;
    }

     /**
     * Create and connect all subsystems (Model, View, Controller)
     */
    private void initializeSubsystems() {
        // === Create model ===
        model = CalculatorModel.getInstance();

        // === Create view ===
        view = new CalculatorView();

        // === Create controller and connect ===
        controller = CalculatorController.getInstance(model, view);
        view.setController(controller);
    }

    /**
     * Launch the calculator GUI
     */
    public void launchCalculator() {
        SwingUtilities.invokeLater(() -> {
            view.setVisible(true);
            view.setLocationRelativeTo(null);
            System.out.println("Calculator launched through Facade.");
        });
    }

}
