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
	protected static void initFromFile(State initial) {
		Map<Integer, Color> br = new HashMap<>();
		StringTokenizer st = new StringTokenizer(Input.instance.remove(1).substring(6), " ,");
		while (st.hasMoreTokens()) {
			br.put(Integer.parseInt(st.nextToken()), Color.BLACK);
		}

		st = new StringTokenizer(Input.instance.remove(1).substring(4), " ,");
		while (st.hasMoreTokens()) {
			br.put(Integer.parseInt(st.nextToken()), Color.RED);
		}

		for (int r = 0; r < Dimension.N; r += 1) {
			st = new StringTokenizer(Input.instance.remove(1), " ,");
			for (int c = 0; c < Dimension.M; c += 1) {
				String token = st.nextToken();
				if (token.equals("_")) {
					initial.board[r][c] = new Tile();
					initial.blank = new Point(r, c);
					continue;
				}

				int data = Integer.parseInt(token);

				if (!br.containsKey(data)) {
					initial.board[r][c] = new Tile(data, Color.GREEN);
					continue;
				}

				Color color = br.get(data);
				if (color == Color.BLACK) {
					if (data != r * Dimension.M + c + 1) /* Unsolvable */ {
						initial.board = null;
						return;
					}
				}

				initial.board[r][c] = new Tile(data, color);
			}
		}
	}
}
