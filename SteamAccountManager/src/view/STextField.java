package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

//Predefined styling for textfields.
public class STextField extends JTextField {

	private static final long serialVersionUID = 6066258362296629034L;

	public STextField() {
		this.setStyle();
	}

	public STextField(String message) {
		this.setText(message);
		this.setStyle();
	}

	private void setStyle() {
		this.setOpaque(false);
		this.setForeground(Color.WHITE);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
	}
}
