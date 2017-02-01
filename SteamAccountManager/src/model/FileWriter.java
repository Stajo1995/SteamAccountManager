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

import javax.swing.JOptionPane;

import controller.StorageController;

public class FileWriter {

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
				ex.printStackTrace();
			}
			break;
		}

		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "UTF-8"));
			writer.append(line);
			((BufferedWriter) writer).newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
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
				JOptionPane.showMessageDialog(null, "Error: Unreachable code reached.\nErrorcode: 8", "Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		}

		try {
			new PrintWriter(path, "UTF-8").close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			JOptionPane.showMessageDialog(null, "Error: Cannot write to the storage.\nErrorcode: 9", "Error", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "Error: Unreachable code reached.\nErrorcode: 10", "Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		}

		if (!isDir) {
			try {
				new PrintWriter(path, "UTF-8").close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error: Cannot write to the storage.\nErrorcode: 11", "Error", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "Error: Unreachable code reached.\nErrorcode: 12", "Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		}

		// TODO: Deleting a line from a file by line number.
		System.out.println("Deleting line " + lineNumber + " from " + path + " found by: " + fileType);
	}
}
