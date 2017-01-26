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

public class FileReader {

	public boolean isEmpty(String fileType) {
		File file = null;

		switch (fileType) {
		case "Account":
			file = new File("Storage/AccountNameStorage");
			break;
		case "Password":
			file = new File("Storage/PasswordStorage");
			break;
		case "Security":
			file = new File("Storage/SecurityStorage");
			break;
		default:
			try {
				throw new Exception();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		}

		if (file.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isDuplicateEntry(String entry) {
		File file = new File("Storage/AccountNameStorage");
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
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return false;
	}

	public ArrayList<String> readAllAccountNames() {
		String line;
		ArrayList<String> list = new ArrayList<String>();
		try (InputStream fis = new FileInputStream("Storage/AccountNameStorage");
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String readLine(String fileType, int lineNumber) {
		String file = null;

		switch (fileType) {
		case "Account":
			file = "Storage/AccountNameStorage";
			break;
		case "Password":
			file = "Storage/PasswordStorage";
			break;
		case "Security":
			file = "Storage/SecurityStorage";
			break;
		default:
			try {
				throw new Exception();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		}

		String line = null;
		try {
			line = Files.readAllLines(Paths.get(file)).get(lineNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
}
