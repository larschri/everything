package org.pvv.larschri.rubik;

import junit.framework.TestCase;

/**
 * Test the {@link Cube}s in class [@link Orientations}
 */
public class OrientationsTest extends TestCase {
	private void assertEquals(Cube c, String s) {
		assertEquals(c.getCorners().toString(), s);
		assertEquals(c.getEdges().toString(), s);
	}

	/**
	 * Test the facelets in all orientations.
	 */
	public void testOrientations() {
		assertEquals(Orientations.ORIENTATIONS.get(0), "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23]");
		assertEquals(Orientations.ORIENTATIONS.get(1), "[1, 2, 3, 0, 13, 14, 15, 12, 7, 4, 5, 6, 17, 18, 19, 16, 11, 8, 9, 10, 23, 20, 21, 22]");
		assertEquals(Orientations.ORIENTATIONS.get(2), "[2, 3, 0, 1, 18, 19, 16, 17, 12, 13, 14, 15, 8, 9, 10, 11, 6, 7, 4, 5, 22, 23, 20, 21]");
		assertEquals(Orientations.ORIENTATIONS.get(3), "[3, 0, 1, 2, 9, 10, 11, 8, 17, 18, 19, 16, 7, 4, 5, 6, 15, 12, 13, 14, 21, 22, 23, 20]");
		assertEquals(Orientations.ORIENTATIONS.get(4), "[4, 5, 6, 7, 8, 9, 10, 11, 0, 1, 2, 3, 20, 21, 22, 23, 12, 13, 14, 15, 16, 17, 18, 19]");
		assertEquals(Orientations.ORIENTATIONS.get(5), "[5, 6, 7, 4, 21, 22, 23, 20, 11, 8, 9, 10, 13, 14, 15, 12, 3, 0, 1, 2, 19, 16, 17, 18]");
		assertEquals(Orientations.ORIENTATIONS.get(6), "[6, 7, 4, 5, 14, 15, 12, 13, 20, 21, 22, 23, 0, 1, 2, 3, 10, 11, 8, 9, 18, 19, 16, 17]");
		assertEquals(Orientations.ORIENTATIONS.get(7), "[7, 4, 5, 6, 1, 2, 3, 0, 13, 14, 15, 12, 11, 8, 9, 10, 23, 20, 21, 22, 17, 18, 19, 16]");
		assertEquals(Orientations.ORIENTATIONS.get(8), "[8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 16, 17, 18, 19, 20, 21, 22, 23, 12, 13, 14, 15]");
		assertEquals(Orientations.ORIENTATIONS.get(9), "[9, 10, 11, 8, 17, 18, 19, 16, 3, 0, 1, 2, 21, 22, 23, 20, 7, 4, 5, 6, 15, 12, 13, 14]");
		assertEquals(Orientations.ORIENTATIONS.get(10), "[10, 11, 8, 9, 22, 23, 20, 21, 16, 17, 18, 19, 4, 5, 6, 7, 2, 3, 0, 1, 14, 15, 12, 13]");
		assertEquals(Orientations.ORIENTATIONS.get(11), "[11, 8, 9, 10, 5, 6, 7, 4, 21, 22, 23, 20, 3, 0, 1, 2, 19, 16, 17, 18, 13, 14, 15, 12]");
		assertEquals(Orientations.ORIENTATIONS.get(12), "[12, 13, 14, 15, 2, 3, 0, 1, 18, 19, 16, 17, 6, 7, 4, 5, 22, 23, 20, 21, 8, 9, 10, 11]");
		assertEquals(Orientations.ORIENTATIONS.get(13), "[13, 14, 15, 12, 7, 4, 5, 6, 1, 2, 3, 0, 23, 20, 21, 22, 17, 18, 19, 16, 11, 8, 9, 10]");
		assertEquals(Orientations.ORIENTATIONS.get(14), "[14, 15, 12, 13, 20, 21, 22, 23, 6, 7, 4, 5, 18, 19, 16, 17, 0, 1, 2, 3, 10, 11, 8, 9]");
		assertEquals(Orientations.ORIENTATIONS.get(15), "[15, 12, 13, 14, 19, 16, 17, 18, 23, 20, 21, 22, 1, 2, 3, 0, 5, 6, 7, 4, 9, 10, 11, 8]");
		assertEquals(Orientations.ORIENTATIONS.get(16), "[16, 17, 18, 19, 10, 11, 8, 9, 22, 23, 20, 21, 2, 3, 0, 1, 14, 15, 12, 13, 4, 5, 6, 7]");
		assertEquals(Orientations.ORIENTATIONS.get(17), "[17, 18, 19, 16, 3, 0, 1, 2, 9, 10, 11, 8, 15, 12, 13, 14, 21, 22, 23, 20, 7, 4, 5, 6]");
		assertEquals(Orientations.ORIENTATIONS.get(18), "[18, 19, 16, 17, 12, 13, 14, 15, 2, 3, 0, 1, 22, 23, 20, 21, 8, 9, 10, 11, 6, 7, 4, 5]");
		assertEquals(Orientations.ORIENTATIONS.get(19), "[19, 16, 17, 18, 23, 20, 21, 22, 15, 12, 13, 14, 9, 10, 11, 8, 1, 2, 3, 0, 5, 6, 7, 4]");
		assertEquals(Orientations.ORIENTATIONS.get(20), "[20, 21, 22, 23, 6, 7, 4, 5, 14, 15, 12, 13, 10, 11, 8, 9, 18, 19, 16, 17, 0, 1, 2, 3]");
		assertEquals(Orientations.ORIENTATIONS.get(21), "[21, 22, 23, 20, 11, 8, 9, 10, 5, 6, 7, 4, 19, 16, 17, 18, 13, 14, 15, 12, 3, 0, 1, 2]");
		assertEquals(Orientations.ORIENTATIONS.get(22), "[22, 23, 20, 21, 16, 17, 18, 19, 10, 11, 8, 9, 14, 15, 12, 13, 4, 5, 6, 7, 2, 3, 0, 1]");
		assertEquals(Orientations.ORIENTATIONS.get(23), "[23, 20, 21, 22, 15, 12, 13, 14, 19, 16, 17, 18, 5, 6, 7, 4, 9, 10, 11, 8, 1, 2, 3, 0]");
	}
}
