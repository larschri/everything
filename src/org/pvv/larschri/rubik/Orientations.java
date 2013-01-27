package org.pvv.larschri.rubik;

import java.util.ArrayList;
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
 * Interface that holds all the orientations of a cube. Note that the center pieces are not
 * a part of the {@link Cube} state, so the orientations should not be used to rotate the cube since
 * the center piece will not be rotated.
 */
public interface Orientations {

	/**
	 * List all 24 orientations of the cube. The i'th cube has the i'th number oriented to position 0.
	 */
	List<Cube> ORIENTATIONS = Collections.unmodifiableList(new Object() {
		private final Cube ROT_Z = rot( 1, 2, 3, 0,13,14,15,12, 7, 4, 5, 6,17,18,19,16,11, 8, 9,10,23,20,21,22);
		private final Cube ROT_Y = rot(11, 8, 9,10, 5, 6, 7, 4,21,22,23,20, 3, 0, 1, 2,19,16,17,18,13,14,15,12);
		private final Cube ROT_X = ROT_Z.combine(ROT_Y, ROT_Z.inverse());
	
		private Cube rot(final Integer ... arr) {
			return new Cube(new ICube() {
				@Override public List<Integer> getCorners() { return Arrays.asList(arr); }
				@Override public List<Integer> getEdges() { return Arrays.asList(arr); }
			});
		}
	
		private List<Cube> orientations() {
			List<Cube> result = new ArrayList<Cube>();
			for (Cube c : Arrays.asList(
					new Cube(ICube.IDENTITY),                 //  0
					ROT_X.combine(ROT_Z.inverse()),           //  4
					ROT_Y.combine(ROT_Z),                     //  8
					ROT_Y.inverse().combine(ROT_Z.inverse()), // 12
					ROT_X.inverse().combine(ROT_Z.inverse()), // 16
					ROT_Y.combine(ROT_Y)))                    // 20
			{
				result.add(c);
				result.add(c = c.combine(ROT_Z));
				result.add(c = c.combine(ROT_Z));
				result.add(c = c.combine(ROT_Z));
			}
			return result;
		}
	}.orientations());

}
