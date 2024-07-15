package com.rk.gme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private boolean playerXTurn;
    private boolean gameOver;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        playerXTurn = true;
        gameOver = false;

        initializeButtons();

        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    private void checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) && 
                buttons[i][0].getText().equals(buttons[i][2].getText()) && 
                !buttons[i][0].getText().equals("")) {
                endGame(buttons[i][0].getText() + " wins!");
                return;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) && 
                buttons[0][i].getText().equals(buttons[2][i].getText()) && 
                !buttons[0][i].getText().equals("")) {
                endGame(buttons[0][i].getText() + " wins!");
                return;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) && 
            buttons[0][0].getText().equals(buttons[2][2].getText()) && 
            !buttons[0][0].getText().equals("")) {
            endGame(buttons[0][0].getText() + " wins!");
            return;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) && 
            buttons[0][2].getText().equals(buttons[2][0].getText()) && 
            !buttons[0][2].getText().equals("")) {
            endGame(buttons[0][2].getText() + " wins!");
            return;
        }

        // Check for tie
        boolean tie = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    tie = false;
                    break;
                }
            }
        }
        if (tie) {
            endGame("It's a tie!");
            return;
        }
    }

    private void endGame(String message) {
        JOptionPane.showMessageDialog(this, message);
        gameOver = true;
    }

    private void aiMove() {
        // Simple AI: Find the first available empty spot and mark it
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    buttons[i][j].setText("O");
                    return;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) return;

        JButton clickedButton = (JButton) e.getSource();

        if (!clickedButton.getText().equals("")) return; // Cell already marked

        if (playerXTurn) {
            clickedButton.setText("X");
            checkWin();
            if (!gameOver) aiMove();
        } else {
            clickedButton.setText("O");
            checkWin();
        }

        playerXTurn = !playerXTurn;
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
