package Methods;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import Infrastructure.Node;
import Tools.Heuristic;
import Tools.Service;
import Utils.Direction;

/**
 * This class implements A* algorithm.
 * @author Yahav Karpel
 */

public class _03_aStar implements Solvable {
	@Override
	public Node solve() {
		Node initial = Node.initial;

		// Set the heuristic value of the initial node
		initial.setHeuristic(Heuristic.manhattanDistance2D(initial.getState()));

		Queue<Node> queue = new PriorityQueue<>();
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

			// Remove the current node from the open list
			openList.remove(current.getState().hash());

			if (current.getState().isGoal()) /* The goal was found */ {
				return current;
			}

			// Add the current node to the closed list
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

				// Set the heuristic value of the generated node
				node.setHeuristic(Heuristic.manhattanDistance2D(node.getState()));

				if (!openList.containsKey(node.getState().hash())) /* Added safely */ {
					queue.add(node);
					openList.put(node.getState().hash(), node);
					continue;
				}

				// Check if there is a cheaper path
				Node similar = openList.get(node.getState().hash());
				int fn = node.getWeight() + node.getHeuristic();
				if (similar.getWeight() + similar.getHeuristic() > fn) /* A cheaper path */ {
					queue.remove(similar);
					queue.add(node);
					openList.replace(node.getState().hash(), node);
				}
			}
		}

		return null;
	}
}
