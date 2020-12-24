package Utils;

/**
 * This enum represents the direction of the expansion.
 * @author Yahav Karpel
 */

public enum Direction {
	L, U, R, D;

	/**
	 * This method converts the given integer to the corresponding direction.
	 */
	public static Direction convert(int direction) throws RuntimeException {
		if (direction == 0) {
			return L;
		} if (direction == 1) {
			return U;
		} if (direction == 2) {
			return R;
		} if (direction == 3) {
			return D;
		}

		throw new RuntimeException("ERROR");
	}
}
