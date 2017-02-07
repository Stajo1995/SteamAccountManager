package controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class SteamController {

	private Engine engine;

	public SteamController(Engine engine) {
		this.engine = engine;
		if (this.engine.getStorageController().fileSize("Security") < 2) {
			this.initiateFirstRun();
		}
	}

	private void initiateFirstRun() {
		if (this.engine.getStorageController().isPath(Paths.get("C:\\Program Files (x86)\\Steam\\Steam.exe"))) {
			this.engine.getStorageController().write("Security", "\"C:\\Program Files (x86)\\Steam\\Steam.exe\"");
		} else {
			this.popupSteamLocationDialog(false);
		}
	}

	public void popupSteamLocationDialog(Boolean isRunFromExternalSource) {
		String inputPath = JOptionPane.showInputDialog(null,
				"Please enter the path to your Steam application (do not use quotes)\nThe path should end with \\Steam.exe",
				"Cannot locate Steam", JOptionPane.WARNING_MESSAGE);
		if (!isRunFromExternalSource && inputPath == null) {
			JOptionPane.showMessageDialog(null, "Error: The application cannot continue without locating Steam.",
					"Cannot locate Steam", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} else if (inputPath != null) {
			Path path = Paths.get(inputPath);

			if (this.engine.getStorageController().isPath(path)) {
				this.engine.getStorageController().handleSteamPath("\"" + inputPath + "\"");
			} else {
				JOptionPane.showMessageDialog(null, "Error: Invalid path provided.", "Cannot locate Steam",
						JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
	}
}
