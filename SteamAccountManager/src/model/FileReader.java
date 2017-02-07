package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import controller.StorageController;

public class FileReader {

	private StorageController controller;

	public FileReader(StorageController controller) {
		this.controller = controller;
		this.verifyPaths();
	}

	private void verifyPaths() {
		if (!this.isPath(StorageController.STORAGEPATH)) {
			this.controller.createFile("dir");
		}
		if (!this.isPath(StorageController.SECURITYPATH)) {
			this.controller.createFile("Security");
		}
		if (!this.isPath(StorageController.ACCOUNTPATH)) {
			this.controller.createFile("Account");
		}
		if (!this.isPath(StorageController.PASSWORDPATH)) {
			this.controller.createFile("Password");
		}
	}

	public boolean isPath(Path path) {
		if (Files.notExists(path)) {
			return false;
		}
		return true;
	}

	// TODO Windows only for now.
	public void runSteam(int index) {
		try {
			String line;
			String pidInfo = "";
			Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

			while ((line = input.readLine()) != null) {
				pidInfo += line;
			}

			input.close();

			if (pidInfo.contains("Steam.exe")) {
				Runtime.getRuntime().exec("taskkill /F /IM Steam.exe");
				// Sleep 1 second, for some reason the taskkill will kill the
				// Steam Process that is about to start if ran right away.
				Thread.sleep(1000);
			}

			Runtime.getRuntime().exec(this.readLine("Security", 1) + "-login " + this.readLine("Account", index) + " "
					+ this.readLine("Password", index));
			;
		} catch (IOException | InterruptedException e) {
			ErrorHandler.crash(2, 'a');
		}
		System.exit(0);
	}

	public boolean isEmpty(String fileType) {
		String path = null;

		switch (fileType) {
		case "Account":
			path = StorageController.ACCOUNTPATH.toString();
			break;
		case "Password":
			path = StorageController.PASSWORDPATH.toString();
			break;
		case "Security":
			path = StorageController.SECURITYPATH.toString();
			break;
		default:
			try {
				throw new Exception();
			} catch (Exception ex) {
				ErrorHandler.crash(3, 'a');
			}
			break;
		}

		if (new File(path).length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isDuplicateEntry(String entry) {
		File file = new File(StorageController.ACCOUNTPATH.toString());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (entry.equals(line)) {
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			ErrorHandler.crash(4, 'a');
		} finally {
			scanner.close();
		}
		return false;
	}

	public ArrayList<String> getAllLines(String fileType) {
		String path = null;

		switch (fileType) {
		case "Account":
			path = StorageController.ACCOUNTPATH.toString();
			break;
		case "Password":
			path = StorageController.PASSWORDPATH.toString();
			break;
		case "Security":
			path = StorageController.SECURITYPATH.toString();
			break;
		default:
			try {
				throw new Exception();
			} catch (Exception ex) {
				ErrorHandler.crash(3, 'b');
			}
			break;
		}

		String line;
		ArrayList<String> list = new ArrayList<String>();
		try (InputStream fis = new FileInputStream(path);
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			ErrorHandler.crash(5, 'a');
		}
		return list;
	}

	public String readLine(String fileType, int lineNumber) {
		String path = null;

		switch (fileType) {
		case "Account":
			path = StorageController.ACCOUNTPATH.toString();
			break;
		case "Password":
			path = StorageController.PASSWORDPATH.toString();
			break;
		case "Security":
			path = StorageController.SECURITYPATH.toString();
			break;
		default:
			try {
				throw new Exception();
			} catch (Exception ex) {
				ErrorHandler.crash(3, 'c');
			}
			break;
		}

		String line = null;
		try {
			line = Files.readAllLines(Paths.get(path)).get(lineNumber);
		} catch (IOException | IndexOutOfBoundsException e) {
			ErrorHandler.crash(6, 'a');
		}
		return line;
	}

	public int fileSize(String fileType) {
		String path = null;

		switch (fileType) {
		case "Account":
			path = StorageController.ACCOUNTPATH.toString();
			break;
		case "Password":
			path = StorageController.PASSWORDPATH.toString();
			break;
		case "Security":
			path = StorageController.SECURITYPATH.toString();
			break;
		default:
			try {
				throw new Exception();
			} catch (Exception ex) {
				ErrorHandler.crash(3, 'd');
			}
			break;
		}
		int lines = 0;

		try (InputStream fis = new FileInputStream(path);
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			while ((br.readLine()) != null) {
				lines++;
			}
		} catch (IOException e) {
			ErrorHandler.crash(6, 'a');
		}
		return lines;
	}
}
