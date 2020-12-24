package Methods;
import java.util.HashMap;
import java.util.Map;
import Infrastructure.Node;
import Tools.Service;
import Utils.Direction;

/**
 * This class implements Iterative Deepening Depth First Search algorithm.
 * @author Yahav Karpel
 */

public class _02_IDDFS implements Solvable {
	@Override
	public Node solve() {
		Node initial = Node.initial;

		// All the nodes that are on the current branch
		Map<String, Integer> avoidLoops = new HashMap<>();
		avoidLoops.put(initial.getState().hash(), initial.key);

		for (int i = 1; i < Integer.MAX_VALUE; i += 1) {
			Node result = limitedDFS(initial, i, avoidLoops);
			if (result != null) /* The goal was found */ {
				return result;
			}
		}

		return null;
	}

	/**
	 * This method implements Limited Depth First Search algorithm.
	 */
	private Node limitedDFS(Node current, int depth, Map<String, Integer> avoidLoops) {
		if (current.getState().isGoal()) /* The goal was found */ {
			return current;
		}

		if (depth == 0) /* Do not exceed the current limit */ {
			return null;
		}

		for (int i = 0; i < Service.NUM_OF_OPERATORS; i += 1) /* Generate the next possible nodes */ {
			Node node = Service.expand(current, Direction.convert(i));
			if (node == null) /* Unsafe expansion */ {
				continue;
			}

			// A Loop has been detected
			if (avoidLoops.containsKey(node.getState().hash())) {
				continue;
			}

			// Save the current potential path
			avoidLoops.put(node.getState().hash(), node.key);

			// Recurse
			Node result = limitedDFS(node, depth - 1, avoidLoops);

			if (result != null) /* The goal was found */ {
				return result;
			}

			// Remove traces 
			avoidLoops.remove(node.getState().hash());
		}

		return null;
	}
}
