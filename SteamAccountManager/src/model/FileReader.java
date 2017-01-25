package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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
			// handle this
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

}
