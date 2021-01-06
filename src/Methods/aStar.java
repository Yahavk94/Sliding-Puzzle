package Methods;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import Infrastructure.Node;
import Tools.Heuristic;
import Tools.Service;
import Utils.Direction;

/**
 * This class implements A* algorithm.
 * @author Yahav Karpel
 */

public class aStar implements Solvable {
	@Override
	public Node solve() {
		Queue<Node> queue = new PriorityQueue<>();
		Node.INITIAL.setHeuristic(Heuristic.manhattanDistance2D(Node.INITIAL));
		queue.add(Node.INITIAL);

		// All the nodes that are available for expansion
		Map<String, Node> openList = new HashMap<>();

		// The initial node is available for expansion
		openList.put(Node.INITIAL.encode(), Node.INITIAL);

		// All the nodes that have been expanded
		Set<String> closedList = new HashSet<>();

		while (!queue.isEmpty()) {
			if (Service.WITH_OPEN) /* Display the content of the open list */ {
				Service.iteration(openList.values().iterator());
			}

			Node current = queue.remove();

			// Encode the current node
			String code = current.encode();

			// Remove the current node from the open list
			openList.remove(code);

			if (current.isGoal()) /* The goal was found */ {
				return current;
			}

			// Add the current node to the closed list
			closedList.add(code);

			for (int i = 0; i < Service.NUM_OF_OPERATORS; i += 1) /* Generate the next possible nodes */ {
				Node generated = Service.expand(current, Direction.convert(i));
				if (generated == null) /* Unsafe expansion */ {
					continue;
				}

				// Encode the generated node
				code = generated.encode();

				// The generated node has already been expanded
				if (closedList.contains(code)) {
					continue;
				}

				// Set the heuristic value of the generated node
				generated.setHeuristic(Heuristic.manhattanDistance2D(generated));

				if (!openList.containsKey(code)) /* Added safely */ {
					queue.add(generated);
					openList.put(code, generated);
					continue;
				}

				// Check if there is a cheaper path
				Node similar = openList.get(code);
				int fn = generated.getWeight() + generated.getHeuristic();
				if (similar.getWeight() + similar.getHeuristic() > fn) /* A cheaper path */ {
					queue.remove(similar);
					queue.add(generated);
					openList.replace(code, generated);
				}
			}
		}

		return null;
	}
}
