package org.pvv.larschri.rubik;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The interface for a rubiks cube. Can return lists of all corner- and edge-facelets.
 * The implementation is responsible for returning a valid configuration.
 *  * <pre>
 *             6  5  5
 *             6  U  4
 *             7  7  4
 * 14 13 13    1  0  0    8 11 11   21 20 20
 * 14  L 12    1  F  3    8  R 10   21  B 23
 * 15 15 12    2  2  3    9  9 10   22 22 23
 *            18 17 17
 *            18  D 16
 *            19 19 16
 * </pre>
 */
public interface ICube {
	int SIZE = 24;
	
	/**
	 * @return list of all 24 edge facelets
	 */
	List<Integer> getEdges();

	/**
	 * @return list of all 24 edge facelets
	 */
	List<Integer> getCorners();

	/**
	 * List of numbers from 0 to 23.
	 */
	List<Integer> IDENTITY_LIST = Collections.unmodifiableList(Arrays.asList(
			  0, 1, 2, 3, 4, 5, 6, 7,
			  8, 9,10,11,12,13,14,15,
			 16,17,18,19,20,21,22,23));

	
	ICube IDENTITY = new ICube() {
		@Override public List<Integer> getCorners() { return IDENTITY_LIST; }
		@Override public List<Integer> getEdges()   { return IDENTITY_LIST; }
	};
}
