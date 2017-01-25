package model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileWriter {

	public void write(String filetype, String line) {
		Path file = null;
		switch (filetype) {
		case "Account":
			file = Paths.get("Storage/AccountNameStorage");
			break;
		case "Password":
			file = Paths.get("Storage/PasswordStorage");
			break;
		case "Security":
			file = Paths.get("Storage/SecurityStorage");
			break;
		default:
			try {
				throw new Exception();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		}

		List<String> lines = Arrays.asList(line);
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
