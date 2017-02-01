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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import controller.StorageController;

public class FileReader {

	private StorageController controller;

	public FileReader(StorageController controller) {
		this.controller = controller;
		this.verifyPaths();
	}

	private void verifyPaths() {
		if (Files.notExists(StorageController.STORAGEPATH)) {
			this.controller.createFile("dir");
		}
		if (Files.notExists(StorageController.SECURITYPATH)) {
			this.controller.createFile("Security");
		}
		if (Files.notExists(StorageController.ACCOUNTPATH)) {
			this.controller.createFile("Account");
		}
		if (Files.notExists(StorageController.PASSWORDPATH)) {
			this.controller.createFile("Password");
		}
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
				JOptionPane.showMessageDialog(null, "Error: Unreachable code reached.\nErrorcode: 3", "Error", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "Error: File not found; Account Storage.\nErrorcode: 4", "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			scanner.close();
		}
		return false;
	}

	public ArrayList<String> readAllAccountNames() {
		String line;
		ArrayList<String> list = new ArrayList<String>();
		try (InputStream fis = new FileInputStream(StorageController.ACCOUNTPATH.toString());
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: Cannot read from the Account Storage.\nErrorcode: 5", "Error", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "Error: Unreachable code reached.\nErrorcode: 6", "Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		}

		String line = null;
		try {
			line = Files.readAllLines(Paths.get(path)).get(lineNumber);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: Cannot read from the storage.\nErrorcode: 7", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return line;
	}
}
