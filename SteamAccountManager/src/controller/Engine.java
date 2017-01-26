package controller;

// Some sort of "main controller" which manages all other controllers.
public class Engine {

	private StorageController storage;

	public Engine() {
		this.start();
	}

	private void start() {
		this.createStorageController();
		new SecurityController(this);
		new MenuController(this);
	}

	private void createStorageController() {
		this.storage = new StorageController();
	}

	public StorageController getStorageController() {
		return this.storage;
	}

}
