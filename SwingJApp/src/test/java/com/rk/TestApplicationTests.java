package com.rk;

import com.rk.calc.CalculatorApp;
import com.rk.gme.TicTacToeGUI;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;

//@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {
		calc();
		ticTac();
	}
	private void calc(){
		SwingUtilities.invokeLater(() -> new CalculatorApp().setVisible(true));
	}

	private void ticTac(){
		new TicTacToeGUI();
	}

}
