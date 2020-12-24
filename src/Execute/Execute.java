package Execute;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import Infrastructure.Initial;
import Infrastructure.Node;
import Methods._01_BFS;
import Methods._02_IDDFS;
import Methods._03_aStar;
import Methods._04_IDaStar;
import Methods._05_DFBnB;
import Utils.Input;

/**
 * This class represents the sliding puzzle solver.
 * @author Yahav Karpel
 */

public class Execute {
	public static void main(String[] args) throws IOException {
		PrintWriter output = new PrintWriter(new FileWriter("Output.txt"));

		String method = Input.instance.remove(0);
		boolean time = Input.instance.remove(0).equals("with time");

		long start = System.currentTimeMillis();

		Node result = null;
		if (Initial.canBeSolved()) {
			if (method.equals("BFS")) {
				result = new _01_BFS().solve();
			} else if (method.equals("IDDFS")) {
				result = new _02_IDDFS().solve();
			} else if (method.equals("A*")) {
				result = new _03_aStar().solve();
			} else if (method.equals("IDA*")) {
				result = new _04_IDaStar().solve();
			} else if (method.equals("DFBnB")) {
				result = new _05_DFBnB().solve();
			}
		}

		long finish = System.currentTimeMillis();

		if (result != null) {
			output.println(result.getPath());
			output.println("Num: " + Node.totalNodes());
			output.println("Cost: " + result.getWeight());
		} else {
			output.println("No path");
			output.println("Num: " + Node.totalNodes());
		}

		if (time) {
			output.print((double)(finish - start) / 1000 + " seconds");
		}

		output.close();
	}
}
