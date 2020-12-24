package Methods;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import Infrastructure.Node;
import Tools.Service;
import Utils.Direction;

/**
 * This class implements Breadth First Search algorithm.
 * @author Yahav Karpel
 */

public class _01_BFS implements Solvable {
	@Override
	public Node solve() {
		Queue<Node> queue = new LinkedList<>();

		Node initial = Node.getInstance();
		queue.add(initial);

		// All the nodes that are available for expansion
		Map<String, Node> openList = new HashMap<>();

		// The initial node is available for expansion
		openList.put(initial.getState().hash(), initial);

		// All the nodes that have been expanded
		Map<String, Integer> closedList = new HashMap<>();

		while (!queue.isEmpty()) {
			if (Service.WITH_OPEN) /* Display the content of the open list */ {
				Service.iteration(openList.values().iterator());
			}

			Node current = queue.remove();

			// Move the current node from the open list to the closed list
			openList.remove(current.getState().hash());
			closedList.put(current.getState().hash(), current.key);

			for (int i = 0; i < Service.NUM_OF_OPERATORS; i += 1) /* Generate the next possible nodes */ {
				Node node = Service.expand(current, Direction.convert(i));
				if (node == null) /* Unsafe expansion */ {
					continue;
				}

				// The generated node has already been expanded
				if (closedList.containsKey(node.getState().hash())) {
					continue;
				}

				// The generated node is already available for expansion
				if (openList.containsKey(node.getState().hash())) {
					continue;
				}

				if (node.getState().isGoal()) /* The goal was found */ {
					return node;
				}

				// The generated node is available for expansion
				queue.add(node);
				openList.put(node.getState().hash(), node);
			}
		}

		return null;
	}
}
