package controller;

import model.FileReader;
import model.FileWriter;

public class StorageController {

	private FileWriter writer;
	private FileReader reader;
	private Engine engine;

	public StorageController(Engine engine) {
		this.writer = new FileWriter();
		this.reader = new FileReader();
		this.engine = engine;
	}

	public void write(String file, String line) {
		this.writer.write(file, line);
	}
	
}
