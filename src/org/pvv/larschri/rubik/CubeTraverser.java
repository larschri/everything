package org.pvv.larschri.rubik;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CubeTraverser {
    static String cubeToString(Cube c) {
        return c.getCorners().toString() + c.getEdges().toString();
    }

    private static List<Cube> visitLevel(Collection<String> visited, Collection<Cube> toVisit) {
        List<Cube> nextLevel = new ArrayList<>();
        for (Cube cube : toVisit) {
            for (Cube move : Cubes.SINGLE_MOVES) {
                Cube next = move.combine(cube);
                if (visited.add(cubeToString(next))) {
                    nextLevel.add(next);
                }
            }
        }
        return nextLevel;
    }

    /**
     * Returns the cubes by level. Each level is one move.
     */
    static List<List<Cube>> traverse(Cube cube, int levels) {
        List<List<Cube>> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        visited.add(cubeToString(cube));
        result.add(Collections.singletonList(cube));
        for (int i = 0; i < levels; i++) {
            result.add(visitLevel(visited, result.get(i)));
        }
        return result;
    }
}
