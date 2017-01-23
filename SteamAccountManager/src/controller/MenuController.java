package controller;

import view.MenuView;

public class MenuController {

	private Engine engine;
	private MenuView View;

	public MenuController(Engine engine) {
		this.engine = engine;
		this.View = new MenuView(this);
	}

}
