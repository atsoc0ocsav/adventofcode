package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
	public static List<String> readLines(File file) {
		BufferedReader br = null;
		FileReader fr = null;
		List<String> lines = new ArrayList<String>();

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			System.err.printf("[%s] %s%n", ReadFile.class.getName(), e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.err.printf("[%s] %s%n", ReadFile.class.getName(), e.getMessage());
				}
			}

			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					System.err.printf("[%s] %s%n", ReadFile.class.getName(), e.getMessage());
				}
			}
		}
		return lines;
	}
}
