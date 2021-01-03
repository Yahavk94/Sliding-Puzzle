package Methods;
import java.util.HashSet;
import java.util.Set;
import Infrastructure.Node;
import Tools.Result;
import Tools.Service;
import Utils.Direction;

/**
 * This class implements Iterative Deepening Depth First Search algorithm.
 * @author Yahav Karpel
 */

public class IDDFS implements Solvable {
	@Override
	public Node solve() {
		for (int i = 1; i < Integer.MAX_VALUE; i += 1) {
			Object result = limitedDFS(Node.INITIAL, i, new HashSet<>());
			if (result != Result.CUTOFF) {
				if (result != Result.FAIL) /* The goal was found */ {
					return (Node)result;
				}

				break;
			}
		}

		return null;
	}

	/**
	 * This method implements Limited Depth First Search algorithm.
	 */
	private Object limitedDFS(Node current, int depth, Set<String> avoidLoops) {
		if (current.isGoal()) /* The goal was found */ {
			return current;
		}

		if (depth == 0) /* Do not exceed the current limit */ {
			return Result.CUTOFF;
		}

		// Save the current path
		avoidLoops.add(current.encode());

		// Impose an artificial cutoff depth on the search
		boolean cutoff = false;

		for (int i = 0; i < Service.NUM_OF_OPERATORS; i += 1) /* Generate the next possible nodes */ {
			Node node = Service.expand(current, Direction.convert(i));
			if (node == null) /* Unsafe expansion */ {
				continue;
			}

			// A Loop has been detected
			if (avoidLoops.contains(node.encode())) {
				continue;
			}

			// Recurse
			Object result = limitedDFS(node, depth - 1, avoidLoops);

			if (result == Result.CUTOFF) {
				cutoff = true;
			} else if (result != Result.FAIL) {
				return result;
			}
		}

		// Remove traces
		avoidLoops.remove(current.encode());

		if (cutoff) {
			return Result.CUTOFF;
		}

		return Result.FAIL;
	}
}
