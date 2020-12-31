package Methods;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import Infrastructure.Node;
import Tools.Service;
import Utils.Direction;

/**
 * This class implements Breadth First Search algorithm.
 * @author Yahav Karpel
 */

public class BFS implements Solvable {
	@Override
	public Node solve(Node initial) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(initial);

		// All the nodes that are available for expansion
		Map<String, Node> openList = new HashMap<>();

		// The initial node is available for expansion
		openList.put(initial.encode(), initial);

		// All the nodes that have been expanded
		Set<String> closedList = new HashSet<>();

		while (!queue.isEmpty()) {
			if (Service.WITH_OPEN) /* Display the content of the open list */ {
				Service.iteration(openList.values().iterator());
			}

			Node current = queue.remove();

			// Encode the current node
			String code = current.encode();

			// Move the current node from the open list to the closed list
			openList.remove(code);
			closedList.add(code);

			for (int i = 0; i < Service.NUM_OF_OPERATORS; i += 1) /* Generate the next possible nodes */ {
				Node node = Service.expand(current, Direction.convert(i));
				if (node == null) /* Unsafe expansion */ {
					continue;
				}

				// Encode the generated node
				code = node.encode();

				// The generated node has already been expanded
				if (closedList.contains(code)) {
					continue;
				}

				// The generated node is already available for expansion
				if (openList.containsKey(code)) {
					continue;
				}

				if (node.isGoal()) /* The goal was found */ {
					return node;
				}

				// The generated node is available for expansion
				queue.add(node);
				openList.put(code, node);
			}
		}

		return null;
	}
}
