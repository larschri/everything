package org.pvv.larschri.rubik;

import java.util.AbstractList;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;


/**
 * Cube that does only store one facelet per cubelet. All facelets can be retrieved
 * from {@link #getCorners()} and {@link #getEdges()}.
 * 
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
 * Corner cubelets:
 * 0, 1, 2, 3, 0, 5, 4, 1, 0, 3, 6, 5,
 * 2, 1, 4, 7, 6, 3, 2, 7, 4, 5, 6, 7 
 * Corner rotations:
 * 0, 0, 0, 0, 1, 2, 1, 2, 2, 1, 2, 1,
 * 2, 1, 2, 1, 1, 2, 1, 2, 0, 0, 0, 0
 * 
 * Further work. Compression can be improved.
 * Possible corner positions 40320 8!
 * Possible corner rotations 2187 3^7
 * Possible edge positions 479001600 12!
 * Possible edge rotations 2048 2^11
 * Divide by two since two pieces cannot be swapped. It is however possible to swap two corners by
 * also swapping two edges (http://en.wikipedia.org/wiki/Rubik%27s_Cube#Permutations) so they cannot
 * be handled independently.
 * 
 * This gives 43,252,003,274,489,856,000 different possible permutations. This number is less than
 * 2^66 (and greater than 2^65) so at least nine bytes (eight bytes and two bits) are required
 * to uniquely represent a cube. 
 */
public class CompressedCube implements ICube {

	final int[] corners = new int[CORNER_CUBELETS.length];
	final int[] edges = new int[EDGE_CUBELETS.length];

	private static void initArray(List<Integer> src, int[] dest, Cubelet[] cubelets) {
		for (int i = 0; i < dest.length; i++)
			dest[i] = src.get(cubelets[i].getFirstFace());
	}

	public CompressedCube(ICube cube) {
		initArray(cube.getCorners(), corners, CORNER_CUBELETS);
		initArray(cube.getEdges(), edges, EDGE_CUBELETS);
	}

	/**
	 * @return list of all corners in the uncompressed cube
	 */
	@Override public List<Integer> getCorners() {
		return new CornerFaceletList(corners);
	}

	/**
	 * @return list of all edges in the uncompressed cube
	 */
	@Override public List<Integer> getEdges() {
		return new EdgeFaceletList(edges);
	}

	/**
	 * New approach. More straight forward than CORNER_NEIGHBORS since we would
	 * like to handle cubelet and orientation separately when storing the
	 * compressed cube.
	 * <p>
	 * We create a {@link Cubelet} object for each orientation of the ehrm ...
	 * cubelets. So even though there are 8 corner cubelets in a rubiks cube, we
	 * will create 24 {@link Cubelet} objects; one for each orientation (or
	 * facelet if you like). The three {@link Cubelet} objects that represents
	 * the same cubelet will share the same id, but will have different
	 * orientations.
	 * <p>
	 * There will also be 24 {@link Cubelet} instances for the 12 edge cubelets
	 * in a rubiks cube, that works correspondingly.
	 */
	static class Cubelet {
		private final Face[] faces;
		@SuppressWarnings("unused")
		private final int id;

		private Cubelet(int id, int[] faces) {
			this.faces = new Face[faces.length];
			for (Integer i : RubikUtil.range(faces.length))
				this.faces[i] = new Face(i, faces[i]);
			this.id = id;
		}

		/**
		 * @return the first face on this cubelet.
		 */
		public int getFirstFace() {
			return faces[0].getFace();
		}

		public int getId() { return getId(); }

		class Face {
			private final int orientation, face;
			private Face(int orientation, int face) {
				this.orientation = orientation;
				this.face = face;
			}
			public Face getFace(int offset) {
				return faces[(this.orientation + offset) % faces.length];
			}
			public int getOrientation() { return orientation; }
			public int getFace() { return face; }
			public Cubelet getCubelet() { return Cubelet.this; }
		}

		private static Cubelet[] createCubelets(int[][] array) {
			Cubelet[] result = new Cubelet[array.length];
			for (Integer i : RubikUtil.range(array.length))
				result[i] = new Cubelet(i, array[i]);
			return result;
		}

		private static Face[] getFacelets(Cubelet[] cubelets) {
			Face[] result = new Face[24];
			for (Cubelet cubelet : cubelets)
				for (Face face : cubelet.faces)
					result[face.getFace()] = face;
			return result;
		}
	}

	private final static Cubelet[] CORNER_CUBELETS = Cubelet.createCubelets(new int[][] {
			{  0,  8,  4},
			{  1,  7, 13},
			{  2, 12, 18},
			{  3, 17,  9},
			{ 20, 14,  6},
			{ 21,  5, 11},
			{ 22, 10, 16},
			{ 23, 19, 15},
		});

	private final static Cubelet.Face[] CORNER_FACELETS = Cubelet.getFacelets(CORNER_CUBELETS);

	private final static Cubelet[] EDGE_CUBELETS = Cubelet.createCubelets(new int[][] {
			{  0,  7},
			{  1, 12},
			{  2, 17},
			{  3,  8},
			{  4, 11},
			{  6, 13},
			{ 16,  9},
			{ 18, 15},
			{ 20,  5},
			{ 21, 10},
			{ 22, 19},
			{ 23, 14},
		});

	private final static Cubelet.Face[] EDGE_FACELETS = Cubelet.getFacelets(EDGE_CUBELETS);

	/**
	 * Provides access to all corners. Takes care of "uncompression".
	 */
	private static class CornerFaceletList extends AbstractList<Integer> {
		/**
		 * Three facelets sharing a corner cubelet are considered neighbors. This array lists
		 * the first neighbor for each corner facelet. This can be used to derive the second neighbor.
		 *     6  5  5
 *             6  4  4
 *             7  7  4
 * 14 13 13    1  0  0    8 11 11   21 20 20
 * 14 12 12    1  0  3    8  8 10   21 20 23
 * 15 15 12    2  2  3    9  9 10   22 22 23
 *            18 17 17
 *            18 16 16
 *            19 19 16

		 */

		private final int[] corners;
		CornerFaceletList(int[] corners) { this.corners = corners; }

		/**
		 * Find and return the value of the i'th corner facelet in the uncompressed cube.
		 */
		@Override public Integer get(int i) {
			switch(i) {
			case  4: return CORNER_FACELETS[corners[0]].getFace(2).getFace();
			case  5: return CORNER_FACELETS[corners[5]].getFace(1).getFace();
			case  6: return CORNER_FACELETS[corners[4]].getFace(2).getFace();
			case  7: return CORNER_FACELETS[corners[1]].getFace(1).getFace();
			case  8: return CORNER_FACELETS[corners[0]].getFace(1).getFace();
			case  9: return CORNER_FACELETS[corners[3]].getFace(2).getFace();
			case 10: return CORNER_FACELETS[corners[6]].getFace(1).getFace();
			case 11: return CORNER_FACELETS[corners[5]].getFace(2).getFace();
			case 12: return CORNER_FACELETS[corners[2]].getFace(1).getFace();
			case 13: return CORNER_FACELETS[corners[1]].getFace(2).getFace();
			case 14: return CORNER_FACELETS[corners[4]].getFace(1).getFace();
			case 15: return CORNER_FACELETS[corners[7]].getFace(2).getFace();
			case 16: return CORNER_FACELETS[corners[6]].getFace(2).getFace();
			case 17: return CORNER_FACELETS[corners[3]].getFace(1).getFace();
			case 18: return CORNER_FACELETS[corners[2]].getFace(2).getFace();
			case 19: return CORNER_FACELETS[corners[7]].getFace(1).getFace();
			case 20: return CORNER_FACELETS[corners[4]].getFace(0).getFace();
			case 21: return CORNER_FACELETS[corners[5]].getFace(0).getFace();
			case 22: return CORNER_FACELETS[corners[6]].getFace(0).getFace();
			case 23: return CORNER_FACELETS[corners[7]].getFace(0).getFace();
			default: return CORNER_FACELETS[corners[i]].getFace(0).getFace();
			}
		}
		@Override public int size() { return Cube.SIZE; }
	}

	/**
	 * Provides access to all edges. Takes care of "uncompression".
	 */
	private static class EdgeFaceletList extends AbstractList<Integer> {

		private final int[] edges;
		EdgeFaceletList(int[] edges) { this.edges = edges; }

		/**
		 * Find and return the value of the i'th edge facelet in the uncompressed cube.
		 */
		@Override public Integer get(int i) {
			switch(i) {
			case  5: return EDGE_FACELETS[edges[ 8]].getFace(1).getFace();
			case  6: return EDGE_FACELETS[edges[ 5]].getFace(0).getFace();
			case  7: return EDGE_FACELETS[edges[ 0]].getFace(1).getFace();
			case  8: return EDGE_FACELETS[edges[ 3]].getFace(1).getFace();
			case  9: return EDGE_FACELETS[edges[ 6]].getFace(1).getFace();
			case 10: return EDGE_FACELETS[edges[ 9]].getFace(1).getFace();
			case 11: return EDGE_FACELETS[edges[ 4]].getFace(1).getFace();
			case 12: return EDGE_FACELETS[edges[ 1]].getFace(1).getFace();
			case 13: return EDGE_FACELETS[edges[ 5]].getFace(1).getFace();
			case 14: return EDGE_FACELETS[edges[11]].getFace(1).getFace();
			case 15: return EDGE_FACELETS[edges[ 7]].getFace(1).getFace();
			case 16: return EDGE_FACELETS[edges[ 6]].getFace(0).getFace();
			case 17: return EDGE_FACELETS[edges[ 2]].getFace(1).getFace();
			case 18: return EDGE_FACELETS[edges[ 7]].getFace(0).getFace();
			case 19: return EDGE_FACELETS[edges[10]].getFace(1).getFace();
			case 20: return EDGE_FACELETS[edges[ 8]].getFace(0).getFace();
			case 21: return EDGE_FACELETS[edges[ 9]].getFace(0).getFace();
			case 22: return EDGE_FACELETS[edges[10]].getFace(0).getFace();
			case 23: return EDGE_FACELETS[edges[11]].getFace(0).getFace();
			default: return EDGE_FACELETS[edges[ i]].getFace(0).getFace();
			}
		}

		@Override public int size() { return Cube.SIZE; }

	}

	/**
	 * N integers have N! possible permutations. This method converts an array
	 * of size N containing a permutation of the numbers in [0,N) to a unique
	 * integer in range [0,N!). This integer can be converted back to the
	 * permutation using {@link #unpack(int, int)}
	 */
	public static int pack(int ... numbers) {
		// The algorithm shrinks the array by one on each iteration by
		// recording the position of the largest unrecorded
		// element, and then let the last unrecorded element take over the
		// recorded position.

		// numbers[positions[i]] == i is always true for every i not yet recorded.
		int positions[] = new int[numbers.length];
		int p = 0;
		for (int n : numbers)
			positions[n] = p++;

		int result = 0;

		for (int i = positions.length - 1; i > 0; i--) {
			int pos = positions[i];
			result = result * (i+1) + pos;

			// Swap the last number into the free position.
			int lastNumber = numbers[i];
			numbers[pos] = lastNumber;
			positions[lastNumber] = pos;
		}
		return result;
	}

	/**
	 * See {@link #pack(int...)}
	 */
	public static int[] unpack(int number, int size) {
		int[] result = new int[size];
		for (int i = 1; i < size; i++) {
			int pos = number % (i+1);
			result[i] = result[pos];
			result[pos] = i;
			number /= (i+1);
		}
		return result;
	}

	/**
	 * Tests for class {@link CompressedCube}
	 */
	public static class CompressedCubeTest extends TestCase implements Cubes {

		/**
		 * Test that the given number can be unpacked to a permutation of size n
		 * and packed back to the same number.
		 */
		private void testPacking(int number, int size) {
			int[] permutation = unpack(number, size);
			assertEquals(size, permutation.length);
			boolean b[] = new boolean[permutation.length];
			for (int n : permutation) {
				assertFalse("Value occurs exactly once in permutation: "+n, b[n]);
				b[n] = true;
			}
			assertEquals(number, pack(permutation));
		}

		/**
		 * Test {@link CompressedCube#pack(int...)}/{@link CompressedCube#unpack(int, int)}
		 */
		public void testPermutationPacking() {
			int factorial12 = 479001600;
			int factorial8 = 40320;
			Random rand = new Random(42);
			for (int i = 0; i < 100; i++) {
				testPacking(rand.nextInt(factorial12), 12);
				testPacking(rand.nextInt(factorial8), 8);
			}
			testPacking(factorial12 - 1, 12);
			testPacking(factorial8 - 1, 8);
			testPacking(0, 12);
			testPacking(0, 8);
		}

		private void testCube(String name, Cube cube) {
			CompressedCube compressed = new CompressedCube(cube);
			assertEquals("corners should be equal in "+name,
					String.valueOf(cube.getCorners()),
					String.valueOf(compressed.getCorners()));
			assertEquals("edges should be equal in "+name,
					String.valueOf(cube.getEdges()),
					String.valueOf(compressed.getEdges()));
		}

		private final static Cube[] CUBES = new Cube[] {
			F1, U1, R1, L1, D1, B1,
			F2, U2, R2, L2, D2, B2,
			F3, U3, R3, L3, D3, B3,
		};

		/**
		 * Test that compressed cubes are correctly uncompressed.
		 */
		public void testCubes() {
			testCube("SOLVED", SOLVED);
			for (Cube c : CUBES) {
				testCube("unit cube", c);
			}
			testCube("checker", SOLVED.combine(F2, U2, R2, L2, D2, B2));
		}

		private void testRandom(int seed, int length) {
			Random rand = new Random(seed);
			Cube cube = SOLVED;
			for (int i = 0; i < length; i++) {
				cube = cube.combine(CUBES[rand.nextInt(CUBES.length)]);
			}
			testCube("random "+seed+"/"+length, cube);
		}

		/**
		 * Test some randomly scrambled cubes.
		 */
		public void testRandomCubes() {
			for (int i = 0; i < 100; i++) {
				testRandom(i, 35);
			}
		}
	}
}
