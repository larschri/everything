package org.pvv.larschri.rubik;

import java.util.List;

/**
 * Utility methods.
 */
public class RubikUtil {

	/**
	 * @param size the size of the list, must be less than or equal to 24
	 * @return list of the given size populated with numbers from zero to size-1
	 */
	public static List<Integer> range(int size) {
		return ICube.IDENTITY_LIST.subList(0, size);
	}
}
