package Utils;
import java.util.StringTokenizer;

/**
 * This class represents the dimension of the puzzle.
 * @author Yahav Karpel
 */

public class Dimension {
	private static StringTokenizer dim = new StringTokenizer(Input.instance.remove(1), "x");
	public static final int N = Integer.parseInt(dim.nextToken());
	public static final int M = Integer.parseInt(dim.nextToken());
	public static final int NM = N * M;
}
