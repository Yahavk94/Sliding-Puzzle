package Utils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * This class represents the input file.
 * @author Yahav Karpel
 */

public class Input {
	public static List<String> instance = readLines();

	/**
	 * This method reads all the lines from the input file.
	 */
	private static List<String> readLines() {
		try {
			return Files.readAllLines(Paths.get("Input.txt"));
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
