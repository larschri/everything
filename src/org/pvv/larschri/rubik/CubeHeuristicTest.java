package org.pvv.larschri.rubik;


import java.util.Random;

import junit.framework.TestCase;

/**
 * Test for {@link CubeTraverser}
 */
public class CubeHeuristicTest extends TestCase implements Cubes {
    static Cube randomCube(int steps, Random random) {
        Cube cube = Cubes.SOLVED;
        for (int i = 0; i < steps; i++) {
            cube = cube.combine(Cubes.SINGLE_MOVES.get(random.nextInt(Cubes.SINGLE_MOVES.size())));
        }
        return cube;
    }


    public void testIncreasingHeuristic() {
        int prev = CubeHeuristic.calcHeuristic(SOLVED);
        Random random = new Random(42);
        assertEquals(0, prev);
        for (int i = 1; i < 20; i++) {
            int sum = 0;
            for (int j = 0; j < 500; j++) {
                sum += CubeHeuristic.calcHeuristic(randomCube(i, random));
            }
            assertTrue(sum > prev);
            prev = sum;
        }
    }
}