package Infrastructure;
import Utils.Color;
import Utils.Direction;

/**
 * This class represents a node.
 * @author Yahav Karpel
 */

public class Node extends State implements Comparable<Node> {
	private final int ID = total++;

	private int weight;
	private int heuristic;
	private boolean mark;

	private static int total;

	private Direction direction;
	private String path;

	public static final Node INITIAL = new Node();

	/**
	 * This method constructs the initial node.
	 */
	private Node() {
		super();
		initPath();
	}

	/**
	 * This method constructs a new node.
	 */
	public Node(Node current, Direction direction) {
		super(current);
		weight = current.weight;
		setDirection(direction);
		path = current.path;
	}

	/**
	 * This method returns the weight of this node.
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * This method updates the weight of this node by adding the cost of the specified color.
	 */
	public void updateWeight(Color color) {
		weight += Color.cost(color);
	}

	/**
	 * This method returns the heuristic evaluation of this node.
	 */
	public int getHeuristic() {
		return heuristic;
	}

	/**
	 * This method sets the heuristic evaluation of this node.
	 */
	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	/**
	 * This method returns a boolean temporal data which can be used by the algorithms.
	 */
	public boolean isMarked() {
		return mark;
	}

	/**
	 * This method sets the boolean temporal data which can be used by the algorithms.
	 */
	public void setMark(boolean mark) {
		this.mark = mark;
	}

	/**
	 * This method returns the direction of the expansion of this node.
	 */
	public Direction getDirection() {
		return direction;
	}

	/** 
	 * This method sets the direction of the expansion of this node.
	 */
	private void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * This method initializes the path to this node.
	 */
	private void initPath() {
		path = "";
	}

	/**
	 * This method returns the path to this node.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * This method updates the path to this node by adding the specified action.
	 */
	public void updatePath(String action) {
		path += action;
	}

	/**
	 * This method returns the total number of nodes.
	 */
	public static int totalNodes() {
		return total;
	}

	/**
	 * This method imposes a total ordering on the nodes.
	 */
	@Override
	public int compareTo(Node node) {
		if (weight + heuristic == node.weight + node.heuristic) {
			if (ID > node.ID) {
				return 1;
			}
		}

		else if (weight + heuristic > node.weight + node.heuristic) {
			return 1;
		}

		return -1;
	}
}
