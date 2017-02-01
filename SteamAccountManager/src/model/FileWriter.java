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
import java.nio.file.Files;
import java.nio.file.Paths;

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
				ex.printStackTrace();
			}
			break;
		}

		try {
			new PrintWriter(path, "UTF-8").close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
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
				ex.printStackTrace();
			}
			break;
		}

		if (!isDir) {
			try {
				new PrintWriter(path, "UTF-8").close();
			} catch (IOException e) {
				e.printStackTrace();
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
				ex.printStackTrace();
			}
			break;
		}

		// TODO: Deleting a line from a file by line number.
		System.out.println("Deleting line " + lineNumber + " from " + path + " found by: " + fileType);
	}
}
