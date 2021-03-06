package Tools;
import java.util.Iterator;
import Infrastructure.Node;
import Utils.Color;
import Utils.Dimension;
import Utils.Direction;
import Utils.Input;
import Utils.Point;

/**
 * This class represents the methods which are used by the search algorithms.
 * @author Yahav Karpel
 */

public class Service {
	public static final int NUM_OF_OPERATORS = 4;
	public static final boolean WITH_OPEN = Input.instance.remove(0).equals("with open");

	/**
	 * This method expands the specified node in the specified direction.
	 */
	public static Node expand(Node node, Direction direction) {
		Point blank = node.getBlank();

		if (direction == Direction.L) {
			if (node.getDirection() == Direction.R) /* The inverted action is invalid */ {
				return null;
			} if (blank.getCol() == Dimension.M - 1) /* Unable to slide left */ {
				return null;
			} if (node.getBoard()[blank.getRow()][blank.getCol() + 1].getColor() == Color.BLACK) {
				return null;
			}
		}

		else if (direction == Direction.U) {
			if (node.getDirection() == Direction.D) /* The inverted action is invalid */ {
				return null;
			} if (blank.getRow() == Dimension.N - 1) /* Unable to slide up */ {
				return null;
			} if (node.getBoard()[blank.getRow() + 1][blank.getCol()].getColor() == Color.BLACK) {
				return null;
			}
		}

		else if (direction == Direction.R) {
			if (node.getDirection() == Direction.L) /* The inverted action is invalid */ {
				return null;
			} if (blank.getCol() == 0) /* Unable to slide right */ {
				return null;
			} if (node.getBoard()[blank.getRow()][blank.getCol() - 1].getColor() == Color.BLACK) {
				return null;
			}
		}

		else if (direction == Direction.D) {
			if (node.getDirection() == Direction.U) /* The inverted action is invalid */ {
				return null;
			} if (blank.getRow() == 0) /* Unable to slide down */ {
				return null;
			} if (node.getBoard()[blank.getRow() - 1][blank.getCol()].getColor() == Color.BLACK) {
				return null;
			}
		}

		return Support.update(new Node(node, direction));
	}

	/**
	 * This method displays the open list nodes.
	 */
	public static void iteration(Iterator<Node> iterator) {
		System.out.println("The following states are available for expansion\n");
		while (iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
	}
}
