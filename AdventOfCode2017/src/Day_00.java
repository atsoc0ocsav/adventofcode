import java.io.File;
import java.util.List;

import utils.ReadFile;

public abstract class Day_00 {
	public Day_00() {
		solvePart1();
		solvePart2();
	}

	public abstract void solvePart1();

	public abstract void solvePart2();

	public List<String> getLines(String f) {
		File file = new File(f);
		return ReadFile.readLines(file);
	}
}
