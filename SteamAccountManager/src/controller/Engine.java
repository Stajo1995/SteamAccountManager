package controller;

// Some sort of "main controller" which manages all other controllers.
public class Engine {

	private MenuController Menu;

	public Engine() {
		this.Start();
	}

	private void Start() {
		this.Menu = new MenuController(this);
	}
}
