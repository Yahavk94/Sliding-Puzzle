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
	 * This method constructs a new green tile.
	 */
	protected Tile() {
		setColor(Color.GREEN);
	}

	/**
	 * This method constructs a new tile.
	 */
	protected Tile(int data, Color color) {
		setData(data);
		setColor(color);
	}

	/**
	 * This method returns the data of this tile.
	 */
	public int getData() {
		return data;
	}

	/**
	 * This method sets the data of this tile.
	 */
	protected void setData(int data) {
		this.data = data;
	}

	/**
	 * This method returns the color of this tile.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * This method sets the color of this tile.
	 */
	protected void setColor(Color color) {
		this.color = color;
	}
}
