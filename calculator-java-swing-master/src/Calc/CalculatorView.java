package Calc;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CalculatorView extends JFrame {

    private JTextField textField1;
    private JPanel jPanelMain;
    private JPanel jPanelScientific;
    private JPanel jPanelNumbers;

    // Number and operation buttons
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    private JButton jButton8;
    private JButton jButton9;
    private JButton jButton0;

    private JButton jButtonAdd;
    private JButton jButtonSub;
    private JButton jButtonMul;
    private JButton jButtonDiv;
    private JButton jButtonEq;
    private JButton jButtonC;
    private JButton jButtonDot;
    private JButton jButtonDel;
    private JButton jButtonSign;
    private JButton jButtonPower;

    // Scientific buttons
    private JButton jButtonSin;
    private JButton jButtonCos;
    private JButton jButtonLog;

    public CalculatorView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(350, 500);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);
        setLocationRelativeTo(null);

        // === Display ===
        textField1 = new JTextField();
        textField1.setEditable(false);
        textField1.setFont(new Font("SansSerif", Font.BOLD, 24));
        textField1.setHorizontalAlignment(SwingConstants.RIGHT);
        add(textField1, BorderLayout.NORTH);

        // === Main Panels ===
        jPanelMain = new JPanel(new BorderLayout(5, 5));
        jPanelScientific = new JPanel(new GridLayout(1, 3, 5, 5));
        jPanelNumbers = new JPanel(new GridLayout(5, 4, 5, 5));

        // === Scientific Buttons ===
        jButtonSin = new JButton("sin");
        jButtonCos = new JButton("cos");
        jButtonLog = new JButton("log");

        jPanelScientific.add(jButtonSin);
        jPanelScientific.add(jButtonCos);
        jPanelScientific.add(jButtonLog);

        // === Number and Operation Buttons ===
        jButton1 = new JButton("1");
        jButton2 = new JButton("2");
        jButton3 = new JButton("3");
        jButton4 = new JButton("4");
        jButton5 = new JButton("5");
        jButton6 = new JButton("6");
        jButton7 = new JButton("7");
        jButton8 = new JButton("8");
        jButton9 = new JButton("9");
        jButton0 = new JButton("0");

        jButtonAdd = new JButton("+");
        jButtonSub = new JButton("-");
        jButtonMul = new JButton("x");
        jButtonDiv = new JButton("/");
        jButtonEq = new JButton("=");
        jButtonC = new JButton("C");
        jButtonDot = new JButton(".");
        jButtonDel = new JButton("←");
        jButtonSign = new JButton("+/-");
        jButtonPower = new JButton("^");

        // Add buttons in grid layout (numbers + ops)
        JButton[] buttons = {
            jButton7, jButton8, jButton9, jButtonDiv,
            jButton4, jButton5, jButton6, jButtonMul,
            jButton1, jButton2, jButton3, jButtonSub,
            jButton0, jButtonDot, jButtonEq, jButtonAdd,
            jButtonC, jButtonDel, jButtonSign, jButtonPower
        };

        for (JButton btn : buttons) {
            btn.setFont(new Font("SansSerif", Font.BOLD, 18));
            btn.setActionCommand(btn.getText());
            jPanelNumbers.add(btn);
        }

        // Add panels to frame
        jPanelMain.add(jPanelScientific, BorderLayout.NORTH);
        jPanelMain.add(jPanelNumbers, BorderLayout.CENTER);
        add(jPanelMain, BorderLayout.CENTER);

        pack();
    }

    // === Controller binding ===
    public void setController(ActionListener controller) {
        for (Component comp : jPanelScientific.getComponents()) {
            if (comp instanceof JButton btn) btn.addActionListener(controller);
        }
        for (Component comp : jPanelNumbers.getComponents()) {
            if (comp instanceof JButton btn) btn.addActionListener(controller);
        }
    }

    // === Display updates ===
    public void updateDisplay(String current, String previous) {
        textField1.setText(current);
    }

    public void clearDisplay() {
        textField1.setText("");
    }

    public void appendDisplay(String text) {
        textField1.setText(textField1.getText() + text);
    }

    public String getDisplayText() {
        return textField1.getText();
    }
}
