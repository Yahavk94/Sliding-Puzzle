package Utils;

/**
 * This enum represents the colors of the tiles.
 * @author Yahav Karpel
 */

public enum Color {
	GREEN, RED, BLACK, NULL;

	/**
	 * This method converts the specified color to the corresponding cost.
	 */
	public static int cost(Color color) throws RuntimeException {
		if (color == GREEN) {
			return 1;
		} if (color == RED) {
			return 30;
		}

		throw new RuntimeException("ERROR");
	}
}
