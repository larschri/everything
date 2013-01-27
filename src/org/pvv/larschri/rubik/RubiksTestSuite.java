package org.pvv.larschri.rubik;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Tests for all classes in this package.
 */
public class RubiksTestSuite {

	/**
	 * Method invoked by junit.
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(CompressedCube.CompressedCubeTest.class);
		suite.addTestSuite(CubesTest.class);
		suite.addTestSuite(OrientationsTest.class);
		return suite;
	}
}
