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
	protected static Node update(Node generated) {
		Point b = generated.getBlank();

		Point s = null;
		Direction direction = generated.getDirection();

		if (direction == Direction.L) {
			s = new Point(b.getRow(), b.getCol() + 1);
		} else if (direction == Direction.U) {
			s = new Point(b.getRow() + 1, b.getCol());
		} else if (direction == Direction.R) {
			s = new Point(b.getRow(), b.getCol() - 1);
		} else if (direction == Direction.D) {
			s = new Point(b.getRow() - 1, b.getCol());
		}

		Tile temp = generated.getBoard()[s.getRow()][s.getCol()];

		// Update weight
		generated.updateWeight(temp.getColor());

		// Update path
		if (generated.getPath().isEmpty()) {
			generated.updatePath(temp.getData() + "" + direction);
		} else {
			generated.updatePath("-" + temp.getData() + direction);
		}

		// Perform swap
		generated.getBoard()[s.getRow()][s.getCol()] = generated.getBoard()[b.getRow()][b.getCol()];
		generated.getBoard()[b.getRow()][b.getCol()] = temp;

		// Update blank
		b.setLocation(s);

		return generated;
	}
}
