package view;

import java.awt.Color;

import javax.swing.JButton;

//Predefined styling for buttons.
public class SButton extends JButton {

	private static final long serialVersionUID = 7904261225684810899L;

	public SButton(String message) {
		this.setText(message);
		this.setStyle();
	}

	public SButton() {
		this.setStyle();
	}

	private void setStyle() {
		this.setOpaque(true);
		this.setBorderPainted(false);
		this.setBackground(Color.WHITE);
		this.setFocusPainted(false);
	}
}
