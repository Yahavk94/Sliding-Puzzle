package Utils;

/**
 * This class represents a location on the board.
 * @author Yahav Karpel
 */

public class Point {
	private int r;
	private int c;

	/**
	 * This method constructs a new point.
	 */
	public Point() {
		r = c = Integer.MAX_VALUE;
	}

	/**
	 * This method constructs a new point.
	 */
	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}

	/**
	 * This method returns the row of this point.
	 */
	public int getRow() {
		return r;
	}

	/**
	 * This method returns the column of this point.
	 */
	public int getCol() {
		return c;
	}

	/**
	 * This method sets the location of this point.
	 */
	public void setLocation(Point other) {
		r = other.r;
		c = other.c;
	}
}
