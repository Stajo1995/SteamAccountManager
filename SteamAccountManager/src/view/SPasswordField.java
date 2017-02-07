package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

//Predefined styling for passwordfields.
public class SPasswordField extends JPasswordField {

	private static final long serialVersionUID = -5797148041528339458L;

	public SPasswordField() {
		this.setStyle();
	}

	public SPasswordField(String message) {
		this.setText(message);
		this.setStyle();
	}

	private void setStyle() {
		this.setOpaque(false);
		this.setForeground(Color.WHITE);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
	}
}
