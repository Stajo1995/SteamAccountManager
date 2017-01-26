package controller;

import java.util.ArrayList;

import model.FileReader;
import model.FileWriter;

public class StorageController {

	private FileWriter writer;
	private FileReader reader;

	public StorageController() {
		this.writer = new FileWriter();
		this.reader = new FileReader();
	}

	public void write(String file, String line) {
		this.writer.write(file, line);
	}

	public ArrayList<String> readAllAccountNames() {
		return this.reader.readAllAccountNames();
	}

	public boolean isEmpty(String fileType) {
		return this.reader.isEmpty(fileType);
	}

	public boolean isDuplicateEntry(String entry) {
		return this.reader.isDuplicateEntry(entry);
	}

	public String readLine(String fileType, int lineNumber) {
		return this.reader.readLine(fileType, lineNumber);
	}

	public void empty(String file) {
		this.writer.emptyFile(file);
	}

}
