package controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import model.FileReader;
import model.FileWriter;

public class StorageController {

	public static final String PATHSTRING = System.getenv("APPDATA") + "\\SteamAccountManager";
	public static final Path STORAGEPATH = Paths.get(PATHSTRING);
	public static final Path ACCOUNTPATH = Paths.get(PATHSTRING + "\\SAMAC");
	public static final Path PASSWORDPATH = Paths.get(PATHSTRING + "\\SAMPW");
	public static final Path SECURITYPATH = Paths.get(PATHSTRING + "\\SAMSC");

	private FileWriter writer;
	private FileReader reader;

	public StorageController() {
		this.writer = new FileWriter(this);
		this.reader = new FileReader(this);
	}

	public void write(String file, String line) {
		this.writer.write(file, line);
	}

	public void runSteam(int index) {
		this.reader.runSteam(index);
	}

	public boolean isPath(Path path) {
		return this.reader.isPath(path);
	}

	public int fileSize(String fileType) {
		return reader.fileSize(fileType);
	}

	public void handleSteamPath(String path) {
		if (this.reader.fileSize("Security") > 1) {
			String macAddr = this.readLine("Security", 0);
			this.empty("Security");
			this.write("Security", macAddr);
		}
		this.write("Security", path);
	}

	public ArrayList<String> getAllLines(String fileType) {
		return this.reader.getAllLines(fileType);
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

	public void createFile(String file) {
		this.writer.createFile(file);
	}

	public void deleteLine(int lineNumber) {
		this.writer.deleteLine("Account", lineNumber);
		this.writer.deleteLine("Password", lineNumber);
	}
}
