package view;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import controller.MenuController;

public class MenuView extends View {

	private JPanel panel;
	private SLabel usernameLabel;
	private SLabel passwordLabel;
	private SLabel errorLabel;
	private STextField usernameInput;
	private SPasswordField passwordInput;
	private SButton addAccountButton;
	private SButton helpButton;
	private DefaultListModel<String> listModel;
	private JList<String> list;

	private MenuController controller;

	// TODO: Add a Steam button which sets the STeam directory.
	public MenuView(MenuController menuController) {
		this.controller = menuController;
		this.BuildView();
	}

	// This generates the content of the view.
	private void BuildView() {
		panel = new JPanel();
		panel.setBackground(new Color(58, 195, 239));
		panel.setLayout(null);

		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		list.setOpaque(true);
		list.setBackground(new Color(255, 255, 100));
		list.setFont(list.getFont().deriveFont(22.0f));
		list.setFixedCellHeight(44);
		this.add(list);

		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList<String> list = (JList<String>) evt.getSource();
				Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex());
				if (r != null && r.contains(evt.getPoint()) && evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
					loginAccount(list.locationToIndex(evt.getPoint()));
				} else if (r != null && r.contains(evt.getPoint()) && evt.getButton() == MouseEvent.BUTTON3 && evt.getClickCount() == 2) {
					deleteAccount(list.locationToIndex(evt.getPoint()));
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(list,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(100, 150, 1070, 500);
		panel.add(scrollPane);

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

		helpButton = new SButton("Help");
		helpButton.addActionListener(e -> this.helpButtonPressed());
		helpButton.setBounds(addAccountButton.getX() + 530, addAccountButton.getY(), 100, 20);
		panel.add(helpButton);

		this.add(panel);
		this.getRootPane().setDefaultButton(addAccountButton);
		this.setVisible(true);
		this.setDefaultFocus();
	}

	private void helpButtonPressed() {
		JOptionPane.showMessageDialog(null, "Welcome to the Steam Account Manager by Stajo, use the 'Add Account' button with your Steam account credentials to (locally) store your accounts for quick swapping between them.\nSet your Steam directory by clicking the 'Steam' button in the top right.\nDouble left click on a username to log into that account.\nDouble right click on a username to delete that account from the storage.\nThis software is licensed under the MIT license.", "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	// This triggers when the add account button has been pressed.
	private void addAccountButtonPressed() {
		String passwordInputConverted = new String(passwordInput.getPassword()); // SECURITY FLAW: stores password in memory until garbage is collected.
		if (this.usernameInput.getText().equals("") || passwordInputConverted.equals("")) {
			this.setErrorLabel("Username and password fields may not be empty.");
		} else {
			this.emptyErrorLabel();
			this.controller.addAccountButtonPressed(usernameInput.getText(),passwordInputConverted);
			this.emptyInput();
			this.listModel.removeAllElements();
			this.controller.addLabels();
			this.repaint();
		}
	}
	
	public void addEntry(String entry) {
		this.listModel.addElement(" " + entry + " ");
	}

	private void emptyInput() {
		this.usernameInput.setText("");
		this.passwordInput.setText("");
	}

	private void loginAccount(int index) {
		this.setErrorLabel("[DEBUG] Logging in  account with account: " + index);
		// TODO Login with Steam.
		this.controller.loginAccount(index);
	}
	
	private void deleteAccount(int index) {
		this.controller.deleteAccount(index);
		this.listModel.removeAllElements();
		this.controller.addLabels();
		this.repaint();
	}

	// This empties the error label.
	private void emptyErrorLabel() {
		this.errorLabel.setText("");
		this.errorLabel.repaint();
	}

	// This sets the error label with a new string.
	public void setErrorLabel(String NewLabel) {
		this.errorLabel.setText(NewLabel);
		this.errorLabel.repaint();
	}

	// Sets the default focus to the primary input textfield.
	private void setDefaultFocus() {
		this.usernameInput.requestFocusInWindow();
	}
}
