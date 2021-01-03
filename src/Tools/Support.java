package Tools;
import Infrastructure.Node;
import Infrastructure.Tile;
import Utils.Direction;
import Utils.Point;

/**
 * This class represents the methods which are used by the service.
 * @author Yahav Karpel
 */

public class Support {
	/**
	 * This method updates the generated node.
	 */
	protected static Node update(Node node) {
		Point b = node.getBlank();

		Point s = null;
		if (node.getDirection() == Direction.L) {
			s = new Point(b.getRow(), b.getCol() + 1);
		} else if (node.getDirection() == Direction.U) {
			s = new Point(b.getRow() + 1, b.getCol());
		} else if (node.getDirection() == Direction.R) {
			s = new Point(b.getRow(), b.getCol() - 1);
		} else if (node.getDirection() == Direction.D) {
			s = new Point(b.getRow() - 1, b.getCol());
		}

		Tile temp = node.getBoard()[s.getRow()][s.getCol()];

		// Update weight
		node.updateWeight(temp.getColor());

		// Update path
		if (node.getPath().isEmpty()) {
			node.updatePath(temp.getData() + "" + node.getDirection());
		} else {
			node.updatePath("-" + temp.getData() + node.getDirection());
		}

		// Perform swap
		node.getBoard()[s.getRow()][s.getCol()] = node.getBoard()[b.getRow()][b.getCol()];
		node.getBoard()[b.getRow()][b.getCol()] = temp;

		// Update blank
		b.setLocation(s);

		return node;
	}
}
