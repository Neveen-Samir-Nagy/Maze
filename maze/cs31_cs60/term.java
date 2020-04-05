package eg.edu.alexu.csd.datastructure.maze.cs31_cs60;

import java.awt.Point;

/**
 * . term
 *
 * @author EL-hamd
 *
 */
public class term {
	/**.
	 * . int
	 */
	int x;
	/**
	 * . int
	 */
	int y;
	/**
	 * . point
	 */
	Point parent;

	/**
	 * . get
	 *
	 * @param newx
	 *            first
	 * @param newy
	 *            second
	 * @param newpx
	 *            third
	 * @param newpy
	 *            fourth
	 */
	public term(final int newx,
			final int newy,
			final int newpx,
			final int newpy) {
		this.x = newx;
		this.y = newy;
		parent = new Point(newpx, newpy);
	}
}