package Methods;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import Infrastructure.Node;
import Tools.Heuristic;
import Tools.Service;
import Utils.Direction;

/**
 * This class implements Iterative Deepening A* algorithm.
 * @author Yahav Karpel
 */

public class _04_IDaStar implements Solvable {
	@Override
	public Node solve() {
		Node initial = Node.initial;

		// Initialize the threshold
		int threshold = Heuristic.manhattanDistance2D(initial.getState());

		Stack<Node> nodes = new Stack<>();

		// All the nodes that are currently in the stack
		Map<String, Node> avoidLoops = new HashMap<>();

		while (threshold != Integer.MAX_VALUE) {
			initial.setMark(false);
			nodes.push(initial);
			avoidLoops.put(initial.getState().encode(), initial);

			// Should be set to a strict upper bound
			int minF = Integer.MAX_VALUE;

			while (!nodes.isEmpty()) {
				Node current = nodes.pop();

				if (current.isMarked()) /* Marked as out */ {
					avoidLoops.remove(current.getState().encode());
					continue;
				}

				current.setMark(true);
				nodes.push(current);

				for (int i = 0; i < Service.NUM_OF_OPERATORS; i += 1) /* Generate the next possible nodes */ {
					Node node = Service.expand(current, Direction.convert(i));
					if (node == null) /* Unsafe expansion */ {
						continue;
					}

					// Encode the state of the generated node
					String code = node.getState().encode();

					// Set the heuristic value of the generated node
					node.setHeuristic(Heuristic.manhattanDistance2D(node.getState()));

					int fn = node.getWeight() + node.getHeuristic();
					if (fn > threshold) /* Do not exceed the current threshold */ {
						if (fn < minF) /* Update if necessary */ {
							minF = fn;
						}

						continue;
					}

					if (avoidLoops.containsKey(code)) {
						if (avoidLoops.get(code).isMarked()) /* Marked as out */ {
							continue;
						}

						// Not marked as out
						Node similar = avoidLoops.get(code);
						if (similar.getWeight() + similar.getHeuristic() > fn) /* A cheaper path */ {
							similar.setMark(true);
							avoidLoops.remove(code);
						} else /* The older one is cheaper */ {
							continue;
						}
					}

					if (node.getState().isGoal()) /* The goal was found */ {
						return node;
					}

					nodes.push(node);
					avoidLoops.put(code, node);
				}
			}

			threshold = minF;
		}

		return null;
	}
}
