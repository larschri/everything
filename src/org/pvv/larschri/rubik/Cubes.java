package org.pvv.larschri.rubik;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 *             6  5  5
 *             6  4  4
 *             7  7  4
 * 14 13 13    1  0  0    8 11 11   21 20 20
 * 14 12 12    1  0  3    8  8 10   21 20 23
 * 15 15 12    2  2  3    9  9 10   22 22 23
 *            18 17 17
 *            18 16 16
 *            19 19 16
 * </pre>
 *
 * Interface containing the solved cube and all cubes that are one twist 
 * (clockwise, counterclockwise or 180 degrees) away from the solved state.
 * 
 * <ul>
 * <li/>F1, F2, F3 - front layer turned clockwise (F1), 180 degrees (F2) and counterclockwise (F3)
 * <li/>U1, U2, U3 - upper layer turned clockwise (U1), 180 degrees (U2) and counterclockwise (U3)
 * <li/>R1, R2, R3 - right layer turned clockwise (R1), 180 degrees (R2) and counterclockwise (R3)
 * <li/>L1, L2, L3 - left layer turned clockwise (L1), 180 degrees (L2) and counterclockwise (L3)
 * <li/>D1, D2, D3 - down layer turned clockwise (D1), 180 degrees (D2) and counterclockwise (D3)
 * <li/>B1, B2, B3 - back layer turned clockwise (B1), 180 degrees (B2) and counterclockwise (B3)
 * </ul>
 * 
 * Classes that implements this interface can create new cubes by combining these cubes. Example:
 * <code>
 * Cube cube = SOLVED.combine(F2, B2, R2, L2, U2, D2);
 * </code>
 */
public interface Cubes {
	/**
	 * The solved cube.
	 */
	Cube SOLVED = new Cube(ICube.IDENTITY);

	/**
	 * List of six Cubes with one side rotated clockwise. The cubes are ordered by increasing facelet
	 * numbers of the rotated side.
	 */
	List<Cube> CLOCKWISE_ROTATIONS = Collections.unmodifiableList(new Object() {
		Cube r = new Cube(new ICube() {
			@Override public List<Integer> getCorners() {
				return Arrays.asList( 1, 2, 3, 0,13, 5, 6,12, 7, 4,10,11,17,18,14,15,16, 8, 9,19,20,21,22,23); }
			@Override public List<Integer> getEdges() {
				return Arrays.asList( 1, 2, 3, 0, 4, 5, 6,12, 7, 9,10,11,17,13,14,15,16, 8,18,19,20,21,22,23); }
		});
		
		private Cube turn(int i) {
			Cube o = Orientations.ORIENTATIONS.get(i);
			return o.combine(r, o.inverse());			
		}

		List<Cube> turns() {
			return Arrays.asList(turn(0), turn(4), turn(8), turn(12), turn(16), turn(20));
		}
	}.turns());

	

	/**
	 * Cube with the front layer turned clockwise.
	 */
	Cube F1 = CLOCKWISE_ROTATIONS.get(0);

	/**
	 * Cube with the up layer turned clockwise.
	 */
	Cube U1 = CLOCKWISE_ROTATIONS.get(1);

	/**
	 * Cube with the right layer turned clockwise.
	 */
	Cube R1 = CLOCKWISE_ROTATIONS.get(2);

	/**
	 * Cube with the left layer turned clockwise.
	 */
	Cube L1 = CLOCKWISE_ROTATIONS.get(3);

	/**
	 * Cube with the down layer turned clockwise.
	 */
	Cube D1 = CLOCKWISE_ROTATIONS.get(4);

	/**
	 * Cube with the back layer turned clockwise.
	 */
	Cube B1 = CLOCKWISE_ROTATIONS.get(5);

	/**
	 * Cube with the front layer turned 180 degrees.
	 */
	Cube F2 = F1.combine(F1);

	/**
	 * Cube with the upper layer turned 180 degrees.
	 */
	Cube U2 = U1.combine(U1);

	/**
	 * Cube with the right layer turned 180 degrees.
	 */
	Cube R2 = R1.combine(R1);

	/**
	 * Cube with the left layer turned 180 degrees.
	 */
	Cube L2 = L1.combine(L1);

	/**
	 * Cube with the down layer turned 180 degrees.
	 */
	Cube D2 = D1.combine(D1);

	/**
	 * Cube with the back layer turned 180 degrees.
	 */
	Cube B2 = B1.combine(B1);

	/**
	 * Cube with the front layer turned counterclockwise.
	 */
	Cube F3 = F1.inverse();

	/**
	 * Cube with the upper layer turned counterclockwise.
	 */
	Cube U3 = U1.inverse();

	/**
	 * Cube with the right layer turned counterclockwise.
	 */
	Cube R3 = R1.inverse();

	/**
	 * Cube with the left layer turned counterclockwise.
	 */
	Cube L3 = L1.inverse();

	/**
	 * Cube with the down layer turned counterclockwise.
	 */
	Cube D3 = D1.inverse();

	/**
	 * Cube with the back layer turned counterclockwise.
	 */
	Cube B3 = B1.inverse();
}
