package view;

import java.awt.Color;

import javax.swing.JLabel;

// Predefined styling for labels.
public class SLabel extends JLabel {

	private static final long serialVersionUID = -1098501638915108557L;

	public SLabel(String message) {
		this.setText(message);
		this.setForeground(Color.WHITE);
	}

	public SLabel() {
		this.setForeground(Color.WHITE);
	}
}
