package com.rk.calc;

import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
@Log4j2
public class CalculatorApp extends JFrame implements ActionListener {
    private JTextField displayField;
    private double currentValue;
    private String currentOperator;

    public CalculatorApp() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);

        displayField = new JTextField();
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4     ));
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            displayField.setText(displayField.getText() + command);
        } else if (command.equals("C")) {
            displayField.setText("");
            currentValue = 0;
            currentOperator = null;
        } else if (command.matches("[+\\-*/]")) {
            currentValue = Double.parseDouble(displayField.getText());
            currentOperator = command;
            displayField.setText("");
        } else if (command.equals("=")) {
            double newValue = Double.parseDouble(displayField.getText());
            switch (currentOperator) {
                case "+":
                    currentValue += newValue;
                    break;
                case "-":
                    currentValue -= newValue;
                    break;
                case "*":
                    currentValue *= newValue;
                    break;
                case "/":
                    currentValue /= newValue;
                    break;
            }

            log.info("the value is : "+currentValue);
            displayField.setText(String.valueOf(currentValue));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorApp().setVisible(true));
    }
}
