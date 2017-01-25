package controller;

// Some sort of "main controller" which manages all other controllers.
public class Engine {

	private MenuController menu;
	private StorageController storage;

	public Engine() {
		this.start();
	}

	private void start() {
		// TODO Add check to see if this PC is the same as last one used, remove all accounts if other PC (Check MAC addr).
		this.createStorageController();
		this.menu = new MenuController(this);
	}

	private void createStorageController() {
		this.storage = new StorageController(this);
	}

	public StorageController getStorageController() {
		return this.storage;
	}

}
