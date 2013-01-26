package org.pvv.larschri.rubik;

import java.util.List;


public class Main implements Cubes {
	/*
	 *             3  2  2
	 *             3  0  1
	 *             0  0  1
	 *  9  8  8 |  4  7  7 | 13 12 12 | 18 17 17
	 *  9  8 11 |  4  4  6 | 13 12 15 | 18 16 16
	 * 10 10 11 |  5  5  6 | 14 14 15 | 19 19 16
	 *            21 20 20
	 *            21 20 23
	 *            22 22 23
	 * 
	 *            23 22 22
	 *            23 20 21
	 *            20 20 21
	 * 15 14 14 |  6  5  5 | 11 10 10 | 16 19 19
	 * 15 12 13 |  6  4  4 | 11  8  9 | 16 16 18
	 * 12 12 13 |  7  7  4 |  8  8  9 | 17 17 18
	 *             1  0  0 
	 *             1  0  3
	 *             2  2  3
	 */
	@SuppressWarnings("unused")
	private static int[][] CORNERS = new int[][] {
		{ 0, 8, 4},
		{ 1, 7,13},
		{ 2,12,23},
		{ 3,22, 9},
		{20,14, 6},
		{21, 5,11},
		{22,10, 3},
		{23, 2,15},
	};

	@SuppressWarnings("unused")
	private static int[][] EDGES = new int[][] {
		{ 0, 7},
		{ 1,12},
		{ 2,17},
		{ 3, 8},
		{20, 5},
		{21,10},
		{22,19},
		{23,14},
		{ 4,11},
		{ 6,13},
		{16, 9},
		{18,15},
	};

	private static int[][] getFaces(Cube c) {
		int[][] m = new int[][] {
				               { 3, 2, 0, 1},
				{ 9, 8,10,11}, { 4, 7, 5, 6}, {13,12,14,15}, {18,17,19,16},
				               {21,20,22,23}};
		int[][] faces = new int[6][];
		List<Integer> corners = c.getCorners();
		List<Integer> edges = c.getEdges();
		for (int i = 0; i < m.length; i++) {
			int n = m[i][0];
			faces[i] = new int[] {
					corners.get(m[i][0]), edges.get(m[i][1]), corners.get(m[i][1]),
					edges.get  (m[i][0]), n - n % 4,          edges.get  (m[i][3]),
					corners.get(m[i][2]), edges.get(m[i][2]), corners.get(m[i][3])};
		}
		return faces;
	}
	
	

	private static void copyFace(int[] face, char[][] screen, int column, int row) {
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				screen[row+r][column+c] = (char) ('A'+face[c+3*r]);
			}
		}
	}
	
	public static String toString(Cube cube) {
		char[][] screen = new char[9][15];
		int[][] faces = getFaces(cube);
		copyFace(faces[0], screen, 4, 0);
		copyFace(faces[1], screen, 0, 3);
		copyFace(faces[2], screen, 4, 3);
		copyFace(faces[3], screen, 8, 3);
		copyFace(faces[4], screen,12, 3);
		copyFace(faces[5], screen, 4, 6);
		StringBuffer sb = new StringBuffer();
		for (char[] line : screen) {
			sb.append(new String(line)+"\n");
		}
		return sb.toString().replace('\0', ' ');
	}

	public static String toStringNice(Cube cube) {
		return replace(toString(cube), "#", "-", "+", "|", "O", "X" );
	}

	public static String replace(String s, String ... colors) {
		assert colors.length == 6;
		return s
			.replaceAll("[ABCD]", colors[0])
			.replaceAll("[EFGH]", colors[1])
			.replaceAll("[IJKL]", colors[2])
			.replaceAll("[MNOP]", colors[3])
			.replaceAll("[QRST]", colors[4])
			.replaceAll("[UVWX]", colors[5]);
	}
	
	public static Cube createCube() {
		Cube c = F2;
		//return Cube.SOLVED.multiply(Cube.F, Cube.F);
		//return Cube.F.multiply(Cube.F);
		return c.combine(B2, U2, D2, R2, L2);
	}

	public static void main(String[] args) {
		Cube cube = createCube();
		System.out.println(toStringNice(cube));
		System.out.println(toStringNice(SOLVED.combine(F1, F1)));
		System.out.println(toStringNice(F1.combine(F1)));
	}
}
