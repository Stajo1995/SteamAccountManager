package model;

import javax.swing.JOptionPane;

public abstract class ErrorHandler {

	private static void exit(int errorCode) {
		System.exit(errorCode);
	}

	private static void displayError(int errorCode, char errorIdent) {
		JOptionPane.showMessageDialog(null,
				"Error: " + ErrorHandler.errorMessage(errorCode) + "\nErrorcode: " + errorCode + errorIdent, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void crash(int errorCode, char errorIdent) {
		ErrorHandler.displayError(errorCode, errorIdent);
		ErrorHandler.exit(errorCode);
	}

	private static String errorMessage(int errorCode) {
		String message = null;
		switch (errorCode) {

		case 1:
			message = "Unable to get MAC address for security puposes.";
			break;
		case 2:
			message = "Cannot launch Steam, path seems incorrect.";
			break;
		case 3:
			message = "Unreachable code reached.";
			break;
		case 4:
			message = "File not found; Account Storage.";
			break;
		case 5:
			message = "Cannot read from the Account Storage.";
			break;
		case 6:
			message = "Cannot read from the Storage.";
			break;
		case 7:
			message = "Cannot write to the Storage.";
			break;
		}
		return message;
	}
}
