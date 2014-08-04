package org.pvv.larschri.rubik;

import java.util.Random;

public class RandomCubeGenerator {
    private Random random;

    public RandomCubeGenerator() {
        this(new Random());
    }

    public RandomCubeGenerator(Random random) {
        this.random = random;
    }

    Cube generate(int steps) {
        Cube cube = Cubes.SOLVED;
        for (int i = 0; i < steps; i++) {
            cube = cube.combine(Cubes.SINGLE_MOVES.get(random.nextInt(Cubes.SINGLE_MOVES.size())));
        }
        return cube;
    }
}
