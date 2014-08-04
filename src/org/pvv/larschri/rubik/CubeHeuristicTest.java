package org.pvv.larschri.rubik;


import java.util.Random;

import junit.framework.TestCase;

/**
 * Test for {@link CubeTraverser}
 */
public class CubeHeuristicTest extends TestCase implements Cubes {

    public void testIncreasingHeuristic() {
        int prev = CubeHeuristic.calcHeuristic(SOLVED);
        assertEquals(0, prev);
        RandomCubeGenerator random = new RandomCubeGenerator(new Random(42));
        for (int i = 1; i < 20; i++) {
            int sum = 0;
            for (int j = 0; j < 500; j++) {
                sum += CubeHeuristic.calcHeuristic(random.generate(i));
            }
            assertTrue(sum > prev);
            prev = sum;
        }
    }
}