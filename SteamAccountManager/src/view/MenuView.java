package view;

import java.awt.Color;

import javax.swing.JPanel;

import controller.MenuController;

public class MenuView extends View {

	private JPanel panel;
	private JPanel accountsPanel;
	private SLabel usernameLabel;
	private SLabel passwordLabel;
	private SLabel errorLabel;
	private STextField usernameInput;
	private SPasswordField passwordInput;
	private SButton addAccountButton;

	private MenuController controller;

	public MenuView(MenuController menuController) {
		this.controller = menuController;
		this.BuildView();
	}

	// This generates the content of the view.
	private void BuildView() {
		panel = new JPanel();
		//panel.setOpaque(true);
		panel.setBackground(new Color(58, 195, 239));
		panel.setLayout(null);

		accountsPanel = new JPanel();
		accountsPanel.setOpaque(true);
		accountsPanel.setBackground(new Color(255, 255, 100));
		accountsPanel.setLayout(null);
		accountsPanel.setBounds(100,150,1070,590); // 1280,720
		panel.add(accountsPanel);

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

		addAccountButton = new SButton("Add Account");
		addAccountButton.addActionListener(e -> this.addAccountButtonPressed());
		addAccountButton.setBounds(usernameLabel.getX(), errorLabel.getY() + 20, 220, 20);
		panel.add(addAccountButton);

		this.add(panel);
		this.getRootPane().setDefaultButton(addAccountButton);
		this.setVisible(true);
		this.setDefaultFocus();
	}

	// This triggers when the add account button has been pressed.
	private void addAccountButtonPressed() {
		this.emptyErrorLabel();
		System.out.println("DEBUG 1"); // DEBUG
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
