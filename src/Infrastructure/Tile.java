package Infrastructure;
import Utils.Color;

/**
 * This class represents a tile.
 * @author Yahav Karpel
 */

public class Tile {
	private int data;
	private Color color;

	/**
	 * This method constructs a new tile.
	 */
	protected Tile() {
		color = Color.NULL;
	}

	/**
	 * This method constructs a new tile.
	 */
	protected Tile(int data, Color color) {
		this.data = data;
		this.color = color;
	}

	/**
	 * This method returns the data of this tile.
	 */
	public int getData() {
		return data;
	}

	/**
	 * This method returns the color of this tile.
	 */
	public Color getColor() {
		return color;
	}
}
