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
 * <p />
 * Further work. Compression can be improved.<br />
 * Possible corner positions 8! =     40.320<br />
 * Possible corner rotations 3^7 =     2.187<br />
 * Possible edge positions 12! = 479.001.600<br />
 * Possible edge rotations 2^11 =      2.048<br />
 * Divide by two since two pieces cannot be swapped. It is however possible to swap two corners by
 * also swapping two edges (http://en.wikipedia.org/wiki/Rubik%27s_Cube#Permutations) so they cannot
 * be handled independently.
 * <p />
 * This gives 43,252,003,274,489,856,000 different possible permutations. This number is less than
 * 2^66 (and greater than 2^65) so at least nine bytes (eight bytes and two bits) are required
 * to uniquely represent a cube.
 */
public class CompressedCube implements ICube {

	final Facelet[] corners;
	final Facelet[] edges;

	public CompressedCube(ICube cube) {
		corners = CORNER_FACELETS.fromList(cube.getCorners());
		edges = EDGE_FACELETS.fromList(cube.getEdges());
	}

	/**
	 * @return list of all corners in the uncompressed cube
	 */
	@Override public List<Integer> getCorners() {
		return CORNER_FACELETS.toList(corners);
	}

	/**
	 * @return list of all edges in the uncompressed cube
	 */
	@Override public List<Integer> getEdges() {
		return EDGE_FACELETS.toList(edges);
	}

	/** Creates a copy of the given array with the elements shifted and rotated by n. */
	private static int[] shiftRotate(int n, int ... arr) {
		int[] result = new int[arr.length];
		for (Integer i : RubikUtil.range(arr.length))
			result[i] = arr[(i + n) % arr.length];
		return result;
	}

	/** Represents one facelet, which knows its cubelet, orientation and neighbor facelets. */
	private static class Facelet {
		private final int orientation, cubelet;
		private final int[] twists;
		private Facelet(int orientation, int cubelet, int[] facelets) {
			this.orientation = orientation;
			this.cubelet = cubelet;
			this.twists = shiftRotate(orientation, facelets);
		}
	}


	/** Utility class for mapping between stored {@link Facelet}s and all {@link Facelet}s. */
	private static class Facelets {
		private final int[][] cubelets;
		private final Facelet[] facelets = new Facelet[24];

		/**
		 * Constructor
		 * @param cubelets array containing all cubelets, each represented by an array of facelets.
		 */
		Facelets(int[][] cubelets) {
			this.cubelets = cubelets;
			for (Integer c : RubikUtil.range(cubelets.length))
				for (Integer r : RubikUtil.range(cubelets[c].length))
					facelets[cubelets[c][r]] = new Facelet(r, c, cubelets[c]);
		}

		/** Converts a list of integers to an array containing one {@link Facelet} per cubelet.*/
		Facelet[] fromList(List<Integer> list) {
			Facelet[] result = new Facelet[cubelets.length];
			for (Integer i : RubikUtil.range(cubelets.length))
				result[i] = facelets[list.get(cubelets[i][0])];
			return result;
		}

		/** Opposite of {@link #fromList(List)} .*/
		List<Integer> toList(final Facelet[] storedFacelets) {
			return new AbstractList<Integer>() {
				@Override public Integer get(int i) {
					Facelet face = facelets[i];
					return storedFacelets[face.cubelet].twists[face.orientation];
				}

				@Override public int size() {
					return Cube.SIZE;
				}
			};
		}

		/** In progress */
		@SuppressWarnings("unused")
		long compress(Facelet[] facelets) {
			return 0;
		}

		/** In progress */
		@SuppressWarnings("unused")
		Facelet[] uncompress(long compressed) {
			return null;
		}
	}

	private static final Facelets CORNER_FACELETS =  new Facelets(new int[][] {
			{  0,  8,  4},
			{  1,  7, 13},
			{  2, 12, 18},
			{  3, 17,  9},
			{ 20, 14,  6},
			{ 21,  5, 11},
			{ 22, 10, 16},
			{ 23, 19, 15},
		});


	private static final Facelets EDGE_FACELETS = new Facelets(new int[][] {
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

		/** In progress */
		public void testFull() {
			Cube cube = SOLVED.combine(F2, U2, R2, L2, D2, B2);
			CompressedCube compressed = new CompressedCube(cube);
			int[] cornerPermutation = new int[compressed.corners.length];
			int[] cornerRotation = new int[compressed.corners.length];
			for (int i = 0; i < compressed.corners.length; i++) {
				cornerPermutation[i] = compressed.corners[i].cubelet;
				cornerRotation[i] = compressed.corners[i].orientation;
			}
		}
	}
}
