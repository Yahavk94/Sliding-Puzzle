package Infrastructure;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import Utils.Color;
import Utils.Dimension;
import Utils.Input;
import Utils.Point;

/**
 * This class initializes the initial state.
 * @author Yahav Karpel
 */

public class Initial {
	protected static State initFromFile(State initial) {
		for (int r = 0; r < Dimension.N; r += 1) {
			StringTokenizer st = new StringTokenizer(Input.instance.remove(3), " ,");
			for (int c = 0; c < Dimension.M; c += 1) {
				String data = st.nextToken();
				if (data.equals("_")) {
					initial.getBoard()[r][c].setColor(Color.NULL);
					initial.getBlank().setLocation(new Point(r, c));
				} else {
					initial.getBoard()[r][c].setData(Integer.parseInt(data));
				}
			}
		}

		return initializeColors(initial);
	}

	private static State initializeColors(State initial) {
		Map<Integer, Color> br = new HashMap<>();
		StringTokenizer st = new StringTokenizer(Input.instance.remove(1).substring(6), " ,");
		while (st.hasMoreTokens()) {
			br.put(Integer.parseInt(st.nextToken()), Color.BLACK);
		}

		st = new StringTokenizer(Input.instance.remove(1).substring(4), " ,");
		while (st.hasMoreTokens()) {
			br.put(Integer.parseInt(st.nextToken()), Color.RED);
		}

		if (!br.keySet().isEmpty()) {
			for (int r = 0; r < Dimension.N; r += 1) {
				for (int c = 0; c < Dimension.M; c += 1) {
					int data = initial.getBoard()[r][c].getData();
					if (br.containsKey(data)) {
						if (br.get(data) == Color.BLACK) {
							if (data != r * Dimension.M + c + 1) /* Unsolvable */ {
								return null;
							}
						}

						initial.getBoard()[r][c].setColor(br.get(data));

						br.remove(data);
						if (br.keySet().isEmpty()) {
							return initial;
						}
					}
				}
			}
		}

		return initial;
	}

	/**
	 * This method returns true if the initial state can be solved.
	 */
	public static boolean canBeSolved() {
		return Node.getInstance().getState() != null;
	}
}
