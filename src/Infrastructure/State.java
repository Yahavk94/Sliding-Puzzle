package Infrastructure;
import Utils.Color;
import Utils.Dimension;
import Utils.Point;

/**
 * This class represents a state of the puzzle.
 * @author Yahav Karpel
 */

public class State {
	protected Tile board[][] = new Tile[Dimension.N][Dimension.M];
	protected Point blank;

	/**
	 * This method constructs the initial state.
	 */
	protected State() {
		Initial.initFromFile(this);
	}

	/**
	 * This method constructs a new state.
	 */
	protected State(State state) {
		for (int r = 0; r < Dimension.N; r += 1) {
			for (int c = 0; c < Dimension.M; c += 1) {
				board[r][c] = new Tile(state.board[r][c].getData(), state.board[r][c].getColor());
			}
		}

		blank = new Point(state.blank.getRow(), state.blank.getCol());
	}

	/**
	 * This method returns the board of this state.
	 */
	public Tile[][] getBoard() {
		return board;
	}

	/**
	 * This method returns the location of the blank tile of this state.
	 */
	public Point getBlank() {
		return blank;
	}

	/**
	 * This method generates an injective hash function.
	 */
	public String encode() {
		String code = "";
		for (int r = 0; r < Dimension.N; r += 1) {
			for (int c = 0; c < Dimension.M; c += 1) {
				code += board[r][c].getData() + " ";
			}
		}

		return code;
	}

	/**
	 * This method returns true if this state is the goal state.
	 */
	public boolean isGoal() {
		for (int r = 0; r < Dimension.N; r += 1) {
			for (int c = 0; c < Dimension.M; c += 1) {
				if (r * Dimension.M + c + 1 != Dimension.NM) {
					if (board[r][c].getData() != r * Dimension.M + c + 1) {
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * This method returns the string representation of this state.
	 */
	@Override
	public String toString() {
		String string = "";
		for (int r = 0; r < Dimension.N; r += 1) {
			for (int c = 0; c < Dimension.M; c += 1) {
				if (board[r][c].getColor() == Color.NULL) {
					string += "[(NULL)]";
				} else {
					string += "[(" + board[r][c].getData() + ", " + board[r][c].getColor() + ")]";
				}
			}

			string += "\n";
		}

		return string;
	}
}
