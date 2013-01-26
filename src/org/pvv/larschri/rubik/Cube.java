package org.pvv.larschri.rubik;

import java.util.AbstractList;
import java.util.List;

/**
 * Immutable representation for a 3x3x3 rubiks cube with all facelets. Cubes can be combined to get new Cubes.
 */
public class Cube implements ICube {
	public final static int SIZE = 24;
	private final int[] edges = new int[SIZE], corners = new int[SIZE];

	/**
	 * Combine a with b and produce the resulting cube.
	 * <p>
	 * The resulting cube is a copy of cube a modified by the steps that would result in
	 * cube b when applied on the {@link SOLVED} cube.
	 */
	private Cube(Cube a, Cube b) {
		for (int i : RubikUtil.range(SIZE)) {
			edges[i] = a.edges[b.edges[i]];
			corners[i] = a.corners[b.corners[i]];
		}
	}

	/**
	 * Create a new cube with the corners and edges from the given cube.
	 */
	public Cube(ICube cube) {
		int i = 0;
		for (int edge : cube.getEdges()) edges[i++] = edge;
		int j = 0;
		for (int corner : cube.getCorners()) corners[j++] = corner;
	}

	private Cube(Cube parent, boolean inverse) {
		copy(parent.edges, edges, inverse);
		copy(parent.corners, corners, inverse);
	}

	private static void copy(int[] a, int[] b, boolean inverse) {
		int i = 0;
		if (inverse) for (int j : a) b[j]   = i++;
		else         for (int j : a) b[i++] = j;
	}

	/**
	 * Combines a sequence of cubes one by one in the given order.
	 */
	public Cube combine(Cube ... cubes) {
		Cube result = new Cube(this, false);
		for (Cube c : cubes) {
			result = new Cube(result, c);
		}
		return result;
	}

	/**
	 * The cube that must be combined by this to get the solved cube.
	 */
	public Cube inverse() {
		return new Cube(this, true);
	}

	/**
	 * Interface for external access to corners and edges.
	 */
	private static class FaceletList extends AbstractList<Integer> {
		private final int[] array;
		FaceletList(int[] array) { this.array = array; }
		@Override public Integer get(int i) { return array[i]; }
		@Override public int size() { return SIZE; }
	}

	/**
	 * @return list of all 24 edges
	 */
	@Override public List<Integer> getEdges() { return new FaceletList(edges); }

	/**
	 * @return list of all 24 edges
	 */
	@Override public List<Integer> getCorners() { return new FaceletList(corners); }
}
