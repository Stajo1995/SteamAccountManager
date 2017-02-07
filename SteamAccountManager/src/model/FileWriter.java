package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.StorageController;

public class FileWriter {

	private StorageController controller;

	public FileWriter(StorageController controller) {
		this.controller = controller;
	}

	public void write(String fileType, String line) {
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
				ErrorHandler.crash(3, 'e');
			}
			break;
		}

		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "UTF-8"));
			writer.append(line);
			((BufferedWriter) writer).newLine();
			writer.close();
		} catch (IOException e) {
			ErrorHandler.crash(7, 'a');
		}
	}

	public void emptyFile(String fileType) {
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
				ErrorHandler.crash(3, 'f');
			}
			break;
		}

		try {
			new PrintWriter(path, "UTF-8").close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			ErrorHandler.crash(7, 'b');
		}
	}

	public void createFile(String fileType) {
		String path = null;
		Boolean isDir = false;

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
		case "dir":
			path = StorageController.STORAGEPATH.toString();
			new File(path).mkdir();
			isDir = true;
			break;
		default:
			try {
				throw new Exception();
			} catch (Exception ex) {
				ErrorHandler.crash(3, 'g');
			}
			break;
		}

		if (!isDir) {
			try {
				new PrintWriter(path, "UTF-8").close();
			} catch (IOException e) {
				ErrorHandler.crash(7, 'c');
			}
		}
	}

	public void deleteLine(String fileType, int lineNumber) {
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
				ErrorHandler.crash(3, 'h');
			}
			break;
		}

		ArrayList<String> list = this.controller.getAllLines(fileType);
		list.remove(lineNumber);

		try {
			this.emptyFile(fileType);
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "UTF-8"));

			for (int i = 0; i < list.size(); i++) {

				writer.append(list.get(i));
				((BufferedWriter) writer).newLine();
			}

			writer.close();
		} catch (IOException e) {
			ErrorHandler.crash(7, 'd');
		}
	}
}
