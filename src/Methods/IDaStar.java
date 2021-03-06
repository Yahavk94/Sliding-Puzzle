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

public class IDaStar implements Solvable {
	@Override
	public Node solve() {
		Stack<Node> nodes = new Stack<>();

		// All the nodes that are currently in the stack
		Map<String, Node> avoidLoops = new HashMap<>();

		// Initialize the threshold
		int threshold = Heuristic.manhattanDistance2D(Node.INITIAL);

		while (threshold != Integer.MAX_VALUE) {
			Node.INITIAL.setMark(false);
			nodes.push(Node.INITIAL);
			avoidLoops.put(Node.INITIAL.encode(), Node.INITIAL);

			// Should be set to a strict upper bound
			int minF = Integer.MAX_VALUE;

			while (!nodes.isEmpty()) {
				Node current = nodes.pop();

				if (current.isMarked()) /* Marked as out */ {
					avoidLoops.remove(current.encode());
					continue;
				}

				current.setMark(true);
				nodes.push(current);

				for (int i = 0; i < Service.NUM_OF_OPERATORS; i += 1) /* Generate the next possible nodes */ {
					Node generated = Service.expand(current, Direction.convert(i));
					if (generated == null) /* Unsafe expansion */ {
						continue;
					}

					// Set the heuristic value of the generated node
					generated.setHeuristic(Heuristic.manhattanDistance2D(generated));

					int fn = generated.getWeight() + generated.getHeuristic();
					if (fn > threshold) /* Do not exceed the current threshold */ {
						if (fn < minF) /* Update if necessary */ {
							minF = fn;
						}

						continue;
					}

					// Encode the generated node
					String code = generated.encode();

					if (avoidLoops.containsKey(code)) {
						if (avoidLoops.get(code).isMarked()) /* Marked as out */ {
							continue;
						} else /* Not marked as out */ {
							Node similar = avoidLoops.get(code);
							if (similar.getWeight() + similar.getHeuristic() > fn) /* A cheaper path */ {
								similar.setMark(true);
								avoidLoops.remove(code);
							} else /* The older one is cheaper */ {
								continue;
							}
						}
					}

					if (generated.isGoal()) /* The goal was found */ {
						return generated;
					}

					nodes.push(generated);
					avoidLoops.put(code, generated);
				}
			}

			threshold = minF;
		}

		return null;
	}
}
