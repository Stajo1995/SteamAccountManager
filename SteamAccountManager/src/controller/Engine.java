package controller;

// Some sort of "main controller" which manages all other controllers.
public class Engine {

	private StorageController storage;
	private SteamController steam;

	public Engine() {
		this.start();
	}

	private void start() {
		this.createStorageController();
		new SecurityController(this);
		this.createSteamController();
		new MenuController(this);
	}

	private void createStorageController() {
		this.storage = new StorageController();
	}

	private void createSteamController() {
		this.steam = new SteamController(this);
	}

	public StorageController getStorageController() {
		return this.storage;
	}

	public SteamController getSteamController() {
		return this.steam;
	}

}
