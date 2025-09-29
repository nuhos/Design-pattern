package Calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorView extends JFrame {


    private JPanel app;
    private JPanel resultsPanel;
    private JTextField previous;
    private JTextField current;
    private JPanel buttonsPanel;
    private JButton btnDel;
    private JButton btnClear;
    private JButton btnDiv;
    private JButton btnMult;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btnSub;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btnPlus;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btnPlusSub;
    private JButton btn0;
    private JButton btnDot;
    private JButton btnEqual;
    private JPanel titleBar;
    private JLabel title;
    private JButton btnMini;
    private JButton btnClose;


    private CalculatorController controller;

    public CalculatorView() {
        initComponents();

        getContentPane().setSize(400, 700);
        setLocation(new Point(500, 100));
        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        wireHoverEffects();
    }

    public void setController(CalculatorController controller) {
        this.controller = controller;
        wireActionEventsToController();
    }

    public void updateDisplay(String currentText, String previousText) {
        current.setText(currentText);
        previous.setText(previousText);
    }


    public JButton[] getNumberButtons() {
        return new JButton[]{btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
    }
    public JButton getBtnEqual() { return btnEqual; }
    public JButton getBtnClear() { return btnClear; }
    public JButton getBtnDel() { return btnDel; }
    public JButton getBtnPlus() { return btnPlus; }
    public JButton getBtnSub() { return btnSub; }
    public JButton getBtnMult() { return btnMult; }
    public JButton getBtnDiv() { return btnDiv; }
    public JButton getBtnDot() { return btnDot; }
    public JButton getBtnPlusSub() { return btnPlusSub; }


    private void initComponents() {
        app = new JPanel();
        resultsPanel = new JPanel();
        previous = new JTextField();
        current = new JTextField();
        buttonsPanel = new JPanel();

        btnDel = new JButton("←");
        btnClear = new JButton("C");
        btnDiv = new JButton("÷");
        btnMult = new JButton("×");

        btn7 = new JButton("7");
        btn8 = new JButton("8");
        btn9 = new JButton("9");
        btnSub = new JButton("-");

        btn4 = new JButton("4");
        btn5 = new JButton("5");
        btn6 = new JButton("6");
        btnPlus = new JButton("+");

        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btnPlusSub = new JButton("+/-");

        btn0 = new JButton("0");
        btnDot = new JButton(".");
        btnEqual = new JButton("=");

        titleBar = new JPanel();
        title = new JLabel("Calculator");
        btnMini = new JButton("-");
        btnClose = new JButton("×");


        app.setBackground(new Color(13, 12, 20));
        resultsPanel.setBackground(new Color(34, 34, 34));
        buttonsPanel.setBackground(new Color(21, 20, 22));
        titleBar.setBackground(new Color(21, 20, 22));

        setLayout(new BorderLayout());
        app.setLayout(null);


        resultsPanel.setLayout(new GridLayout(2, 1));
        resultsPanel.setBounds(0, 30, 320, 110);

        // previous
        previous.setEditable(false);
        previous.setBackground(new Color(21, 20, 22));
        previous.setFont(new Font("Century Gothic", Font.BOLD, 18));
        previous.setForeground(new Color(203, 198, 213));
        previous.setHorizontalAlignment(JTextField.RIGHT);
        previous.setBorder(null);

        // current
        current.setEditable(false);
        current.setBackground(new Color(41, 39, 44));
        current.setFont(new Font("Century Gothic", Font.BOLD, 24));
        current.setForeground(Color.WHITE);
        current.setHorizontalAlignment(JTextField.RIGHT);
        current.setBorder(null);

        resultsPanel.add(previous);
        resultsPanel.add(current);

        // Panel
        buttonsPanel.setLayout(null);
        buttonsPanel.setBounds(0, 140, 320, 390);


        JButton[] allButtons = {
                btnDel, btnClear, btnDiv, btnMult,
                btn7, btn8, btn9, btnSub,
                btn4, btn5, btn6, btnPlus,
                btn1, btn2, btn3, btnPlusSub,
                btn0, btnDot, btnEqual
        };
        for (JButton b : allButtons) {
            b.setFont(new Font("Century Gothic", Font.BOLD, 18));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setMargin(new Insets(0,0,0,0));
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            b.setBorder(BorderFactory.createLineBorder(new Color(41,39,44)));

            if (b == btnDiv || b == btnEqual || b == btnDel || b == btnMult || b == btnSub || b == btnPlus || b == btnClear) {
                b.setBackground(new Color(41, 39, 44));
            } else {
                b.setBackground(new Color(21, 20, 22));
            }
        }


        placeButton(btnDel,   20,  20, 70, 70);
        placeButton(btnClear, 90,  20, 70, 70);
        placeButton(btnDiv,   160, 20, 70, 70);
        placeButton(btnMult,  230, 20, 70, 70);

        placeButton(btn7,     20,  90, 70, 70);
        placeButton(btn8,     90,  90, 70, 70);
        placeButton(btn9,     160, 90, 70, 70);
        placeButton(btnSub,   230, 90, 70, 70);

        placeButton(btn4,     20,  160, 70, 70);
        placeButton(btn5,     90,  160, 70, 70);
        placeButton(btn6,     160, 160, 70, 70);
        placeButton(btnPlus,  230, 160, 70, 140);

        placeButton(btn1,     20,  230, 70, 70);
        placeButton(btn2,     90,  230, 70, 70);
        placeButton(btn3,     160, 230, 70, 70);
        placeButton(btnPlusSub,20, 300, 70, 70);

        placeButton(btn0,     90,  300, 70, 70);
        placeButton(btnDot,   160, 300, 70, 70);
        placeButton(btnEqual, 230, 300, 70, 70);


        title.setFont(new Font("Century Gothic", Font.BOLD, 17));
        title.setForeground(Color.WHITE);

        btnMini.setFont(new Font("Century Gothic", Font.BOLD, 24));
        btnMini.setForeground(Color.WHITE);
        btnMini.setBackground(new Color(21,20,22));
        btnMini.setBorder(null);
        btnMini.setFocusPainted(false);
        btnMini.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnClose.setFont(new Font("Century Gothic", Font.BOLD, 24));
        btnClose.setForeground(Color.WHITE);
        btnClose.setBackground(new Color(21,20,22));
        btnClose.setBorder(null);
        btnClose.setFocusPainted(false);
        btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        titleBar.setLayout(null);
        titleBar.setBounds(0, 0, 320, 30);
        title.setBounds(6, 2, 120, 25);
        btnMini.setBounds(260, 0, 30, 30);
        btnClose.setBounds(290, 0, 30, 30);
        titleBar.add(title);
        titleBar.add(btnMini);
        titleBar.add(btnClose);


        app.add(titleBar);
        app.add(resultsPanel);
        app.add(buttonsPanel);

        add(app, BorderLayout.CENTER);

        setSize(320, 530);
        setLocationRelativeTo(null);


        addDragBehaviorToTitleBar();

        btnClose.addActionListener(e -> System.exit(0));
        btnMini.addActionListener(e -> setState(JFrame.ICONIFIED));
        btnClose.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                btnClose.setBackground(new Color(255,75,75));
                btnClose.setForeground(new Color(31,30,33));
            }
            @Override public void mouseExited(MouseEvent e) {
                btnClose.setBackground(new Color(21,20,22));
                btnClose.setForeground(Color.WHITE);
            }
        });
        btnMini.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btnMini.setBackground(new Color(73,69,78)); }
            @Override public void mouseExited (MouseEvent e) { btnMini.setBackground(new Color(21,20,22)); }
        });
    }

    private void placeButton(JButton b, int x, int y, int w, int h) {
        b.setBounds(x, y, w, h);
        buttonsPanel.add(b);
    }

    private void wireHoverEffects() {
        JButton[] btns = {
                btn0, btn1, btn2, btn3, btn4,
                btn5, btn6, btn7, btn8, btn9,
                btnDiv, btnDot, btnEqual, btnDel,
                btnMult, btnPlus, btnPlusSub, btnSub, btnClear
        };
        for (JButton btn : btns) {
            btn.addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) {
                    ((JButton) e.getSource()).setBackground(new Color(73,69,78));
                }
                @Override public void mouseExited(MouseEvent e) {
                    Object b = e.getSource();
                    if (b == btnDiv || b == btnEqual || b == btnDel || b == btnMult || b == btnSub || b == btnPlus || b == btnClear) {
                        ((JButton) b).setBackground(new Color(41, 39, 44));
                    } else {
                        ((JButton) b).setBackground(new Color(21, 20, 22));
                    }
                }
            });
        }
    }

    private void wireActionEventsToController() {
        if (controller == null) return;


        ActionListener numberListener = e -> {
            JButton src = (JButton) e.getSource();
            controller.onAppendNumber(src.getText());
        };
        for (JButton b : getNumberButtons()) b.addActionListener(numberListener);


        getBtnPlus().addActionListener(e -> controller.onChooseOperation("+"));
        getBtnSub().addActionListener(e -> controller.onChooseOperation("-"));
        getBtnMult().addActionListener(e -> controller.onChooseOperation("×"));
        getBtnDiv().addActionListener(e -> controller.onChooseOperation("÷"));
        getBtnDot().addActionListener(e -> controller.onAppendDot());

        getBtnEqual().addActionListener(e -> controller.onCompute());
        getBtnClear().addActionListener(e -> controller.onClear());
        getBtnDel().addActionListener(e -> controller.onDeleteLast());
        getBtnPlusSub().addActionListener(e -> controller.onToggleSign());
    }


    private int dragX, dragY;
    private void addDragBehaviorToTitleBar() {
        titleBar.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                dragX = e.getX();
                dragY = e.getY();
            }
        });
        titleBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override public void mouseDragged(MouseEvent e) {
                int xx = e.getXOnScreen();
                int yy = e.getYOnScreen();
                setLocation(xx - dragX, yy - dragY);
            }
        });
    }
}
