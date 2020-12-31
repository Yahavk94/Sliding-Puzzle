package Tools;
import Infrastructure.Node;
import Infrastructure.Tile;
import Utils.Color;
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

		Point s;
		if (node.getDirection() == Direction.L) {
			s = new Point(b.getRow(), b.getCol() + 1);
		} else if (node.getDirection() == Direction.U) {
			s = new Point(b.getRow() + 1, b.getCol());
		} else if (node.getDirection() == Direction.R) {
			s = new Point(b.getRow(), b.getCol() - 1);
		} else {
			s = new Point(b.getRow() - 1, b.getCol());
		}

		Tile temp = node.getBoard()[s.getRow()][s.getCol()];
		node.setWeight(node.getWeight() + Color.cost(temp.getColor()));
		if (node.getPath() == null) {
			node.setPath(temp.getData() + "" + node.getDirection());
		} else {
			node.setPath(node.getPath() + "-" + temp.getData() + node.getDirection());
		}

		node.getBoard()[s.getRow()][s.getCol()] = node.getBoard()[b.getRow()][b.getCol()];
		node.getBoard()[b.getRow()][b.getCol()] = temp;
		b.setLocation(s);

		return node;
	}
}
