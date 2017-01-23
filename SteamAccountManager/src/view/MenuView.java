package view;

import java.awt.Color;

import javax.swing.JPanel;

import controller.MenuController;

public class MenuView extends View {

	private JPanel panel;
	private SLabel usernameLabel;
	private SLabel passwordLabel;
	private SLabel errorLabel;
	private STextField usernameInput;
	private SPasswordField passwordInput;
	private SButton continueButton;
	private SButton signUpButton;

	private MenuController Controller;

	public MenuView(MenuController menuController) {
		this.Controller = menuController;
		this.BuildView();
	}

	// This generates the content of the view.
	private void BuildView() {
		panel = new JPanel();
		panel.setOpaque(true);
		panel.setBackground(new Color(58, 195, 239));
		panel.setLayout(null);

		usernameLabel = new SLabel();
		usernameLabel.setText("Username:");
		usernameLabel.setBounds((SCREEN_SIZE_DIM.width - 210) / 2, 5, 220, 20);
		panel.add(usernameLabel);

		usernameInput = new STextField();
		usernameInput.setBounds(usernameLabel.getX(), usernameLabel.getY() + 20, 220, 20);
		panel.add(usernameInput);

		passwordLabel = new SLabel();
		passwordLabel.setText("Password:");
		passwordLabel.setBounds(usernameLabel.getX(), usernameInput.getY() + 20, 220, 20);
		panel.add(passwordLabel);

		passwordInput = new SPasswordField();
		passwordInput.setBounds(usernameLabel.getX(), passwordLabel.getY() + 20, 220, 20);
		panel.add(passwordInput);

		errorLabel = new SLabel();
		errorLabel.setForeground(Color.RED);
		errorLabel.setText("");
		errorLabel.setBounds(usernameLabel.getX(), passwordInput.getY() + 20, 350, 20);
		panel.add(errorLabel);

		continueButton = new SButton("Login");
		continueButton.addActionListener(e -> this.continueButtonPressed());
		continueButton.setBounds(usernameLabel.getX(), errorLabel.getY() + 20, 220, 20);
		panel.add(continueButton);

		signUpButton = new SButton("Registreer");
		signUpButton.addActionListener(e -> this.signUpButtonPressed());
		signUpButton.setBounds(usernameLabel.getX(), continueButton.getY() + 40, 220, 20);
		panel.add(signUpButton);

		this.add(panel);
		this.getRootPane().setDefaultButton(continueButton);
		this.setVisible(true);
		this.setDefaultFocus();
	}

	// This triggers when the sign up button has been pressed.
	private void signUpButtonPressed() {
		this.emptyErrorLabel();
		System.out.println(1); // DEBUG
	}

	// This triggers when the continue button has been pressed.
	private void continueButtonPressed() {
		System.out.println(2); // DEBUG
	}

	// This empties the error label.
	private void emptyErrorLabel() {
		errorLabel.setText("");
		errorLabel.repaint();
	}

	// This sets the error label with a new string.
	public void setErrorLabel(String NewLabel) {
		errorLabel.setText(NewLabel);
		errorLabel.repaint();
	}
	
	// Sets the default focus to the primary input textfield.
	private void setDefaultFocus() {
		this.usernameInput.requestFocusInWindow();
	}


}
