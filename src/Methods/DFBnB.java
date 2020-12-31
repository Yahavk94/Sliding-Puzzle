package Methods;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import Infrastructure.Node;
import Tools.Heuristic;
import Tools.Service;
import Utils.Color;
import Utils.Dimension;
import Utils.Direction;

/**
 * This class implements Depth First Branch and Bound algorithm.
 * @author Yahav Karpel
 */

public class DFBnB implements Solvable {
	@Override
	public Node solve(Node initial) {
		Stack<Node> nodes = new Stack<>();
		initial.setMark(false);
		nodes.push(initial);

		// Limit the threshold
		int threshold = thresholdDFBnB();

		// All the nodes that are currently in the stack
		Map<String, Node> avoidLoops = new HashMap<>();
		avoidLoops.put(initial.encode(), initial);

		Node result = null;

		while (!nodes.isEmpty()) {
			Node current = nodes.pop();

			if (current.isMarked()) /* Marked as out */ {
				avoidLoops.remove(current.encode());
				continue;
			}

			current.setMark(true);
			nodes.push(current);

			// Store the generated nodes
			List<Node> storage = new ArrayList<>();

			for (int i = 0; i < Service.NUM_OF_OPERATORS; i += 1) /* Generate the next possible nodes */ {
				Node node = Service.expand(current, Direction.convert(i));
				if (node == null) /* Unsafe expansion */ {
					continue;
				}

				// Set the heuristic value of the generated node
				node.setHeuristic(Heuristic.manhattanDistance2D(node));

				// Store the generated node
				storage.add(node);
			}

			// Sort the generated nodes in ascending order
			Collections.sort(storage);

			for (int i = 0; i < storage.size(); i += 1) {
				Node node = storage.get(i);

				int fn = node.getWeight() + node.getHeuristic();
				if (fn >= threshold) /* Do not exceed the threshold */ {
					storage.subList(i, storage.size()).clear();
					continue;
				}

				// Encode the stored node
				String code = node.encode();

				if (avoidLoops.containsKey(code)) {
					if (avoidLoops.get(code).isMarked()) /* Marked as out */ {
						storage.remove(i--);
					} else /* Not marked as out */ {
						Node similar = avoidLoops.get(code);
						if (similar.getWeight() + similar.getHeuristic() > fn) /* A cheaper path */ {
							similar.setMark(true);
							avoidLoops.remove(code);
						} else /* The older one is cheaper */ {
							storage.remove(i--);
						}
					}

					continue;
				}

				if (node.isGoal()) /* The goal was found */ {
					threshold = fn;
					result = node;
					storage.subList(i, storage.size()).clear();
				}
			}

			Collections.reverse(storage);
			while (!storage.isEmpty()) /* Insert into the stack in descending order */ {
				Node node = storage.remove(0);
				nodes.push(node);
				avoidLoops.put(node.encode(), node);
			}
		}

		return result;
	}

	/**
	 * This method calculates and returns the threshold of DFBnB algorithm.
	 */
	private static int thresholdDFBnB() {
		int count = -1;
		for (int r = 0; r < Dimension.N; r += 1) {
			for (int c = 0; c < Dimension.M; c += 1) {
				if (Node.initial.getBoard()[r][c].getColor() == Color.BLACK) {
					continue;
				}

				count += 1;
			}
		}

		if (count > 12) {
			return Integer.MAX_VALUE;
		}

		int threshold = 1;
		for (int i = 2; i <= count; i += 1) {
			threshold = threshold * i;
		}

		return threshold;
	}
}
