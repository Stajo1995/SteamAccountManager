package controller;

import java.util.ArrayList;

import view.MenuView;

public class MenuController {

	private Engine engine;
	private MenuView view;

	public MenuController(Engine engine) {
		this.engine = engine;
		this.view = new MenuView(this);
		this.addLabels();
	}

	public void addLabels() {
		if (!this.engine.getStorageController().isEmpty("Account")) {
			ArrayList<String> list = this.engine.getStorageController().readAllAccountNames();
			for (int c = 0; c < list.size(); c++) {
				this.view.addEntry(list.get(c));
			}
		}
	}

	public void addAccountButtonPressed(String userName, String pass) {
		if (this.engine.getStorageController().isDuplicateEntry(userName)) {
			this.view.setErrorLabel("That account is already stored");
		} else if (userName.replaceAll("\\s+", "").equals("") || pass.replaceAll("\\s+", "").equals("")) {
			this.view.setErrorLabel("Illegal username or password provided.");
		} else {
			this.write("Account", userName);
			this.write("Password", pass);
		}
	}

	public void loginAccount(int index) {
		
	}

	public void deleteAccount(int index) {
		this.engine.getStorageController().deleteLine(index);
	}

	private void write(String file, String line) {
		this.engine.getStorageController().write(file, line);
	}
}
