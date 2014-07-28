package org.pvv.larschri.rubik;


import java.util.List;

import junit.framework.TestCase;

/**
 * Test for {@link CubeTraverser}
 */
public class CubeTraverserTest extends TestCase implements Cubes {
    public void testCubeTraverser () {
        /*
         * From https://oeis.org/A080601
         */
        long[] expecteds = new long[] {
                1, 18, 243, 3240, 43239, 574908, 7618438, 100803036, 1332343288,
                17596479795L, 232248063316L, 3063288809012L, 40374425656248L,
                531653418284628L, 6989320578825358L, 91365146187124313L};
        int numLevelsToTest = 4;
        List<List<Cube>> result = CubeTraverser.traverse(SOLVED, numLevelsToTest);
        assertEquals(result.size(), numLevelsToTest);
        for (int i = 0; i < numLevelsToTest; i++) {
            assertEquals(result.get(i).size(), expecteds[i]);
        };

        // Same, but start from another cube.
        result = CubeTraverser.traverse(SOLVED.combine(U3, F1, B2), numLevelsToTest);
        assertEquals(result.size(), numLevelsToTest);
        for (int i = 0; i < numLevelsToTest; i++) {
            assertEquals(result.get(i).size(), expecteds[i]);
        };
    }
}