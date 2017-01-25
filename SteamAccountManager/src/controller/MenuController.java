package controller;

import view.MenuView;

public class MenuController {

	private Engine engine;
	private MenuView view;

	public MenuController(Engine engine) {
		this.engine = engine;
		this.view = new MenuView(this);
	}

	public void addAccountButtonPressed(String userName, String pass) {
		if (userName.equals("DEBUG")) { // TODO Verify if the account is already stored.
			this.view.setErrorLabel("That account is already stored");
		}
		else {
			this.write("Account", userName);
			this.write("Password", pass);
		}
	}

	private void write(String file, String line) {
		this.engine.getStorageController().write(file, line);
	}
}
