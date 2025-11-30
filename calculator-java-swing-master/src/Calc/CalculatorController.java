package Calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;          // NEW: استخدمنا ماب عشان نربط الزر بالكوماند
import java.util.Map;              // NEW

public class CalculatorController implements ActionListener {

    private static CalculatorController instance = null;

    private final ICalculatorModel model;
    private final CalculatorView view;

    private final Map<String, Command> commands = new HashMap<>();
    // NEW: هذي أهم إضافة: جدول يربط actionCommand لكل زر بالكوماند المناسب

    private CalculatorController(ICalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        initCommands();
    }

    public static CalculatorController getInstance(ICalculatorModel model, CalculatorView view) {
        if (instance == null) {
            instance = new CalculatorController(model, view);
        }
        return instance;
    }

    private void initCommands() {
        // NEW: هنا ربطنا كل زر بكوماند بدال ما يكون switch

        // ==== أزرار الأرقام ====
        commands.put("0", new AppendNumberCommand(model, "0"));  // NEW
        commands.put("1", new AppendNumberCommand(model, "1"));  // NEW
        commands.put("2", new AppendNumberCommand(model, "2"));  // NEW
        commands.put("3", new AppendNumberCommand(model, "3"));  // NEW
        commands.put("4", new AppendNumberCommand(model, "4"));  // NEW
        commands.put("5", new AppendNumberCommand(model, "5"));  // NEW
        commands.put("6", new AppendNumberCommand(model, "6"));  // NEW
        commands.put("7", new AppendNumberCommand(model, "7"));  // NEW
        commands.put("8", new AppendNumberCommand(model, "8"));  // NEW
        commands.put("9", new AppendNumberCommand(model, "9"));  // NEW

        // ==== النقطة ====
        commands.put(".", new AppendDotCommand(model));          // NEW

        // ==== أوامر عامة ====
        commands.put("C",   new ClearCommand(model));           // NEW
        commands.put("←",   new DeleteLastCommand(model));      // NEW
        commands.put("+/-", new ToggleSignCommand(model));      // NEW
        commands.put("=",   new ComputeCommand(model));         // NEW

        // ==== العمليات الأساسية ====
        commands.put("+", new ChooseOperationCommand(model, "+")); // NEW
        commands.put("-", new ChooseOperationCommand(model, "-")); // NEW
        commands.put("×", new ChooseOperationCommand(model, "×")); // NEW (حسب زرّك)
        commands.put("÷", new ChooseOperationCommand(model, "÷")); // NEW

        // ==== العمليات العلمية (مع Adapter) ====
        commands.put("^",  new ChooseOperationCommand(model, "^"));   // NEW
        commands.put("sin", new ChooseOperationCommand(model, "sin")); // NEW
        commands.put("cos", new ChooseOperationCommand(model, "cos")); // NEW
        commands.put("x²", new ChooseOperationCommand(model, "x²"));   // NEW
        // لو عندك log .. اربطيه نفس الشي:
        // commands.put("log", new ChooseOperationCommand(model, "log"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String key = e.getActionCommand(); // SAME: ناخذ اسم الزر

        if (key == null) return;          // SAME

        Command cmd = commands.get(key); // NEW: نجيب الكوماند من الماب

        if (cmd == null) return;         // NEW: لو زر مو مربوط نتجاهله

        cmd.execute();                   // NEW: بدل ما نسوي switch / if

    }


    /*
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

    // Handle the x² button click
    public void onSquare() {
        model.computeUnaryOperation("x²"); 
        refresh();
    }

    // Function to handle SIN button click
    public void onSin() {
        model.computeUnaryOperation("sin"); 
        refresh();
    }
    
    // Function to handle COS button click
    public void onCos() {
        model.computeUnaryOperation("cos"); 
        refresh();
    }

     */
}
