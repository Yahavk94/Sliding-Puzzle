package Execute;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import Infrastructure.Node;
import Methods.BFS;
import Methods.DFBnB;
import Methods.IDDFS;
import Methods.IDaStar;
import Methods.aStar;
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
		if (Node.INITIAL.getBoard() != null) {
			if (method.equals("BFS")) {
				result = new BFS().solve();
			} else if (method.equals("IDDFS")) {
				result = new IDDFS().solve();
			} else if (method.equals("A*")) {
				result = new aStar().solve();
			} else if (method.equals("IDA*")) {
				result = new IDaStar().solve();
			} else if (method.equals("DFBnB")) {
				result = new DFBnB().solve();
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
