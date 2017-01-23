package view;

import java.awt.Color;

import javax.swing.JLabel;

// Predefined styling for labels.
public class SLabel extends JLabel {

	public SLabel(String message) {
		this.setText(message);
		this.setForeground(Color.WHITE);
	}

	public SLabel() {
		this.setForeground(Color.WHITE);
	}
}
