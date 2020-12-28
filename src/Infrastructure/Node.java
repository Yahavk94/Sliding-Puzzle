package Infrastructure;
import Utils.Direction;

/**
 * This class represents a node.
 * @author Yahav Karpel
 */

public class Node implements Comparable<Node> {
	private final int key = total++;

	private int weight;
	private int heuristic;
	private boolean mark;

	private static int total;

	private State state;
	private Direction direction;
	private String path;

	public static final Node initial = new Node(Initial.initFromFile(new State()));

	/**
	 * This method constructs the initial node.
	 */
	private Node(State initial) {
		if (initial != null) {
			state = new State(initial);
		}
	}

	/**
	 * This method constructs a new node.
	 */
	public Node(Node current, Direction direction) {
		setWeight(current.weight);
		state = new State(current.state);
		setDirection(direction);
		setPath(current.path);
	}

	/**
	 * This method returns the weight of this node.
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * This method sets the weight of this node.
	 */
	public void setWeight(int weight) {
		this.weight = weight;
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
	 * This method returns the state of this node.
	 */
	public State getState() {
		return state;
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
	 * This method returns the sequence of actions that led to this node.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * This method sets the sequence of actions that led to this node.
	 */
	public void setPath(String path) {
		this.path = path;
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
			if (key > node.key) {
				return 1;
			}
		}

		else if (weight + heuristic > node.weight + node.heuristic) {
			return 1;
		}

		return -1;
	}
}
