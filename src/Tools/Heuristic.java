package Tools;
import java.util.HashMap;
import java.util.Map;
import Infrastructure.State;
import Utils.Color;
import Utils.Dimension;
import Utils.Point;

/**
 * This class represents the heuristic function.
 * @author Yahav Karpel
 */

public class Heuristic {
	private static final Map<Integer, Point> map = initMap(new HashMap<>());

	/**
	 * This method computes and returns the heuristic evaluation of the given state.
	 */
	public static int manhattanDistance2D(State state) {
		int manhattan = 0;
		for (int r = 0; r < Dimension.N; r += 1) {
			for (int c = 0; c < Dimension.M; c += 1) {
				try {
					int cost = Color.cost(state.getBoard()[r][c].getColor());
					Point dest = map.get(state.getBoard()[r][c].getData());
					manhattan += cost * (Math.abs(dest.getRow() - r) + Math.abs(dest.getCol() - c));
				}

				catch (RuntimeException e) {
					continue;
				}
			}
		}

		return manhattan;
	}

	/**
	 * This method returns a mapping of each tile to its location in the goal state.
	 */
	private static Map<Integer, Point> initMap(Map<Integer, Point> map) {
		for (int r = 0; r < Dimension.N; r += 1) {
			for (int c = 0; c < Dimension.M; c += 1) {
				if (r * Dimension.M + c + 1 != Dimension.NM) {
					map.put(r * Dimension.M + c + 1, new Point(r, c));
				}
			}
		}

		return map;
	}
}
