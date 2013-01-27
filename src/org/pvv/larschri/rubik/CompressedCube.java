package org.pvv.larschri.rubik;

import java.util.AbstractList;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import org.pvv.larschri.rubik.CompressedCube.Cubelet.Face;


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
 * <p />
 * Further work. Compression can be improved.<br />
 * Possible corner positions 40320 8!<br />
 * Possible corner rotations 2187 3^7<br />
 * Possible edge positions 479001600 12!<br />
 * Possible edge rotations 2048 2^11<br />
 * Divide by two since two pieces cannot be swapped. It is however possible to swap two corners by 
 * also swapping two edges (http://en.wikipedia.org/wiki/Rubik%27s_Cube#Permutations) so they cannot
 * be handled independently.
 * <p />
 * This gives 43,252,003,274,489,856,000 different possible permutations. This number is less than
 * 2^66 (and greater than 2^65) so at least nine bytes (eight bytes and two bits) are required
 * to uniquely represent a cube. 
 */
public class CompressedCube implements ICube {

	final Face[] corners = new Face[CORNER_CUBELETS.length];
	final Face[] edges = new Face[EDGE_CUBELETS.length];

	private static void initArray(List<Integer> src, Face[] destFacelets, Cubelet[] cubelets, Face[] faces) {
		for (int i = 0; i < destFacelets.length; i++) {
			destFacelets[i] = faces[src.get(cubelets[i].getFirstFace())];
		}
	}

	public CompressedCube(ICube cube) {
		initArray(cube.getCorners(), corners, CORNER_CUBELETS, CORNER_FACELETS);
		initArray(cube.getEdges(), edges, EDGE_CUBELETS, EDGE_FACELETS);
	}


	/**
	 * @return list of all corners in the uncompressed cube
	 */
	@Override public List<Integer> getCorners() {
		return CORNER_LOOKUP.createList(corners);
	}

	/**
	 * @return list of all edges in the uncompressed cube
	 */
	@Override public List<Integer> getEdges() {
		return EDGE_LOOKUP.createList(edges);
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

		public Face getFace(int i) {
			return faces[i];
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

	/** Class for looking up all facelets based on the stored facelets. */
	private static class CubeletLookup {
		private final int[] cubeletLookup = new int[24];
		private final int[] rotationLookup = new int[24];

		private CubeletLookup(Cubelet[] cubelets) {
			for (int c = 0; c < cubelets.length; c++) {
				for (int r = 0; r < cubelets[c].faces.length; r++) {
					cubeletLookup[cubelets[c].faces[r].face] = c;
					rotationLookup[cubelets[c].faces[r].face] = r;
				}
			}
		}

		List<Integer> createList(final Face[] facelets) {
			return new AbstractList<Integer>() {
				@Override public Integer get(int i) {
					return facelets[cubeletLookup[i]].getFace(rotationLookup[i]).face;
				}

				@Override public int size() {
					return Cube.SIZE;
				}
			};
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
	private static final CubeletLookup CORNER_LOOKUP =  new CubeletLookup(CORNER_CUBELETS);

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

	private static final CubeletLookup EDGE_LOOKUP = new CubeletLookup(EDGE_CUBELETS);

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

		/**
		 * Test that compressed cubes are correctly uncompressed.
		 */
		public void testCubes() {
			testCube("SOLVED", SOLVED);
			for (Cube c : SINGLE_MOVES) {
				testCube("unit cube", c);
			}
			testCube("checker", SOLVED.combine(F2, U2, R2, L2, D2, B2));
		}

		private void testRandom(int seed, int length) {
			Random rand = new Random(seed);
			Cube cube = SOLVED;
			for (int i = 0; i < length; i++) {
				cube = cube.combine(SINGLE_MOVES.get(rand.nextInt(SINGLE_MOVES.size())));
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
