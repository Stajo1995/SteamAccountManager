package model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileWriter {

	public void write(String fileType, String line) {
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
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
			writer.append(line);
			((BufferedWriter) writer).newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
