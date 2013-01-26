package org.pvv.larschri.rubik;

import junit.framework.TestCase;

/**
 * Test for values in interface {@link Cubes}
 */
public class CubesTest extends TestCase implements Cubes {

	/**
	 * Test all facelets in all the cubes.
	 */
	public void testCubes() {
		assertEquals(CLOCKWISE_ROTATIONS.get(0).getEdges().toString(),   "[1, 2, 3, 0, 4, 5, 6, 12, 7, 9, 10, 11, 17, 13, 14, 15, 16, 8, 18, 19, 20, 21, 22, 23]");
		assertEquals(CLOCKWISE_ROTATIONS.get(0).getCorners().toString(), "[1, 2, 3, 0, 13, 5, 6, 12, 7, 4, 10, 11, 17, 18, 14, 15, 16, 8, 9, 19, 20, 21, 22, 23]");
		assertEquals(CLOCKWISE_ROTATIONS.get(1).getEdges().toString(),   "[11, 1, 2, 3, 5, 6, 7, 4, 8, 9, 10, 20, 12, 0, 14, 15, 16, 17, 18, 19, 13, 21, 22, 23]");
		assertEquals(CLOCKWISE_ROTATIONS.get(1).getCorners().toString(), "[11, 8, 2, 3, 5, 6, 7, 4, 21, 9, 10, 20, 12, 0, 1, 15, 16, 17, 18, 19, 13, 14, 22, 23]");
		assertEquals(CLOCKWISE_ROTATIONS.get(2).getEdges().toString(),   "[0, 1, 2, 16, 3, 5, 6, 7, 9, 10, 11, 8, 12, 13, 14, 15, 21, 17, 18, 19, 20, 4, 22, 23]");
		assertEquals(CLOCKWISE_ROTATIONS.get(2).getCorners().toString(), "[17, 1, 2, 16, 3, 0, 6, 7, 9, 10, 11, 8, 12, 13, 14, 15, 21, 22, 18, 19, 20, 4, 5, 23]");
		assertEquals(CLOCKWISE_ROTATIONS.get(3).getEdges().toString(),   "[0, 6, 2, 3, 4, 5, 23, 7, 8, 9, 10, 11, 13, 14, 15, 12, 16, 17, 1, 19, 20, 21, 22, 18]");
		assertEquals(CLOCKWISE_ROTATIONS.get(3).getCorners().toString(), "[0, 6, 7, 3, 4, 5, 23, 20, 8, 9, 10, 11, 13, 14, 15, 12, 16, 17, 1, 2, 19, 21, 22, 18]");
		assertEquals(CLOCKWISE_ROTATIONS.get(4).getEdges().toString(),   "[0, 1, 15, 3, 4, 5, 6, 7, 8, 2, 10, 11, 12, 13, 14, 22, 17, 18, 19, 16, 20, 21, 9, 23]");
		assertEquals(CLOCKWISE_ROTATIONS.get(4).getCorners().toString(), "[0, 1, 15, 12, 4, 5, 6, 7, 8, 2, 3, 11, 23, 13, 14, 22, 17, 18, 19, 16, 20, 21, 9, 10]");
		assertEquals(CLOCKWISE_ROTATIONS.get(5).getEdges().toString(),   "[0, 1, 2, 3, 4, 10, 6, 7, 8, 9, 19, 11, 12, 13, 5, 15, 16, 17, 18, 14, 21, 22, 23, 20]");
		assertEquals(CLOCKWISE_ROTATIONS.get(5).getCorners().toString(), "[0, 1, 2, 3, 4, 10, 11, 7, 8, 9, 19, 16, 12, 13, 5, 6, 15, 17, 18, 14, 21, 22, 23, 20]");

		assertEquals(F1.getEdges().toString(),   "[1, 2, 3, 0, 4, 5, 6, 12, 7, 9, 10, 11, 17, 13, 14, 15, 16, 8, 18, 19, 20, 21, 22, 23]");
		assertEquals(F1.getCorners().toString(), "[1, 2, 3, 0, 13, 5, 6, 12, 7, 4, 10, 11, 17, 18, 14, 15, 16, 8, 9, 19, 20, 21, 22, 23]");
		assertEquals(F2.getEdges().toString(),   "[2, 3, 0, 1, 4, 5, 6, 17, 12, 9, 10, 11, 8, 13, 14, 15, 16, 7, 18, 19, 20, 21, 22, 23]");
		assertEquals(F2.getCorners().toString(), "[2, 3, 0, 1, 18, 5, 6, 17, 12, 13, 10, 11, 8, 9, 14, 15, 16, 7, 4, 19, 20, 21, 22, 23]");
		assertEquals(F3.getEdges().toString(),   "[3, 0, 1, 2, 4, 5, 6, 8, 17, 9, 10, 11, 7, 13, 14, 15, 16, 12, 18, 19, 20, 21, 22, 23]");
		assertEquals(F3.getCorners().toString(), "[3, 0, 1, 2, 9, 5, 6, 8, 17, 18, 10, 11, 7, 4, 14, 15, 16, 12, 13, 19, 20, 21, 22, 23]");

		assertEquals(U1.getEdges().toString(),   "[11, 1, 2, 3, 5, 6, 7, 4, 8, 9, 10, 20, 12, 0, 14, 15, 16, 17, 18, 19, 13, 21, 22, 23]");
		assertEquals(U1.getCorners().toString(), "[11, 8, 2, 3, 5, 6, 7, 4, 21, 9, 10, 20, 12, 0, 1, 15, 16, 17, 18, 19, 13, 14, 22, 23]");
		assertEquals(U2.getEdges().toString(),   "[20, 1, 2, 3, 6, 7, 4, 5, 8, 9, 10, 13, 12, 11, 14, 15, 16, 17, 18, 19, 0, 21, 22, 23]");
		assertEquals(U2.getCorners().toString(), "[20, 21, 2, 3, 6, 7, 4, 5, 14, 9, 10, 13, 12, 11, 8, 15, 16, 17, 18, 19, 0, 1, 22, 23]");
		assertEquals(U3.getEdges().toString(),   "[13, 1, 2, 3, 7, 4, 5, 6, 8, 9, 10, 0, 12, 20, 14, 15, 16, 17, 18, 19, 11, 21, 22, 23]");
		assertEquals(U3.getCorners().toString(), "[13, 14, 2, 3, 7, 4, 5, 6, 1, 9, 10, 0, 12, 20, 21, 15, 16, 17, 18, 19, 11, 8, 22, 23]");

		assertEquals(R1.getEdges().toString(),   "[0, 1, 2, 16, 3, 5, 6, 7, 9, 10, 11, 8, 12, 13, 14, 15, 21, 17, 18, 19, 20, 4, 22, 23]");
		assertEquals(R1.getCorners().toString(), "[17, 1, 2, 16, 3, 0, 6, 7, 9, 10, 11, 8, 12, 13, 14, 15, 21, 22, 18, 19, 20, 4, 5, 23]");
		assertEquals(R2.getEdges().toString(),   "[0, 1, 2, 21, 16, 5, 6, 7, 10, 11, 8, 9, 12, 13, 14, 15, 4, 17, 18, 19, 20, 3, 22, 23]");
		assertEquals(R2.getCorners().toString(), "[22, 1, 2, 21, 16, 17, 6, 7, 10, 11, 8, 9, 12, 13, 14, 15, 4, 5, 18, 19, 20, 3, 0, 23]");
		assertEquals(F3.getEdges().toString(),   "[3, 0, 1, 2, 4, 5, 6, 8, 17, 9, 10, 11, 7, 13, 14, 15, 16, 12, 18, 19, 20, 21, 22, 23]");
		assertEquals(F3.getCorners().toString(), "[3, 0, 1, 2, 9, 5, 6, 8, 17, 18, 10, 11, 7, 4, 14, 15, 16, 12, 13, 19, 20, 21, 22, 23]");

		assertEquals(L1.getEdges().toString(),   "[0, 6, 2, 3, 4, 5, 23, 7, 8, 9, 10, 11, 13, 14, 15, 12, 16, 17, 1, 19, 20, 21, 22, 18]");
		assertEquals(L1.getCorners().toString(), "[0, 6, 7, 3, 4, 5, 23, 20, 8, 9, 10, 11, 13, 14, 15, 12, 16, 17, 1, 2, 19, 21, 22, 18]");
		assertEquals(L2.getEdges().toString(),   "[0, 23, 2, 3, 4, 5, 18, 7, 8, 9, 10, 11, 14, 15, 12, 13, 16, 17, 6, 19, 20, 21, 22, 1]");
		assertEquals(L2.getCorners().toString(), "[0, 23, 20, 3, 4, 5, 18, 19, 8, 9, 10, 11, 14, 15, 12, 13, 16, 17, 6, 7, 2, 21, 22, 1]");
		assertEquals(L3.getEdges().toString(),   "[0, 18, 2, 3, 4, 5, 1, 7, 8, 9, 10, 11, 15, 12, 13, 14, 16, 17, 23, 19, 20, 21, 22, 6]");
		assertEquals(L3.getCorners().toString(), "[0, 18, 19, 3, 4, 5, 1, 2, 8, 9, 10, 11, 15, 12, 13, 14, 16, 17, 23, 20, 7, 21, 22, 6]");

		assertEquals(D1.getEdges().toString(),   "[0, 1, 15, 3, 4, 5, 6, 7, 8, 2, 10, 11, 12, 13, 14, 22, 17, 18, 19, 16, 20, 21, 9, 23]");
		assertEquals(D1.getCorners().toString(), "[0, 1, 15, 12, 4, 5, 6, 7, 8, 2, 3, 11, 23, 13, 14, 22, 17, 18, 19, 16, 20, 21, 9, 10]");
		assertEquals(D2.getEdges().toString(),   "[0, 1, 22, 3, 4, 5, 6, 7, 8, 15, 10, 11, 12, 13, 14, 9, 18, 19, 16, 17, 20, 21, 2, 23]");
		assertEquals(D2.getCorners().toString(), "[0, 1, 22, 23, 4, 5, 6, 7, 8, 15, 12, 11, 10, 13, 14, 9, 18, 19, 16, 17, 20, 21, 2, 3]");
		assertEquals(D3.getEdges().toString(),   "[0, 1, 9, 3, 4, 5, 6, 7, 8, 22, 10, 11, 12, 13, 14, 2, 19, 16, 17, 18, 20, 21, 15, 23]");
		assertEquals(D3.getCorners().toString(), "[0, 1, 9, 10, 4, 5, 6, 7, 8, 22, 23, 11, 3, 13, 14, 2, 19, 16, 17, 18, 20, 21, 15, 12]");

		assertEquals(B1.getEdges().toString(),   "[0, 1, 2, 3, 4, 10, 6, 7, 8, 9, 19, 11, 12, 13, 5, 15, 16, 17, 18, 14, 21, 22, 23, 20]");
		assertEquals(B1.getCorners().toString(), "[0, 1, 2, 3, 4, 10, 11, 7, 8, 9, 19, 16, 12, 13, 5, 6, 15, 17, 18, 14, 21, 22, 23, 20]");
		assertEquals(B2.getEdges().toString(),   "[0, 1, 2, 3, 4, 19, 6, 7, 8, 9, 14, 11, 12, 13, 10, 15, 16, 17, 18, 5, 22, 23, 20, 21]");
		assertEquals(B2.getCorners().toString(), "[0, 1, 2, 3, 4, 19, 16, 7, 8, 9, 14, 15, 12, 13, 10, 11, 6, 17, 18, 5, 22, 23, 20, 21]");
		assertEquals(B3.getEdges().toString(),   "[0, 1, 2, 3, 4, 14, 6, 7, 8, 9, 5, 11, 12, 13, 19, 15, 16, 17, 18, 10, 23, 20, 21, 22]");
		assertEquals(B3.getCorners().toString(), "[0, 1, 2, 3, 4, 14, 15, 7, 8, 9, 5, 6, 12, 13, 19, 16, 11, 17, 18, 10, 23, 20, 21, 22]");
	}
}
