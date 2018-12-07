package org.pvv.larschri.mastermind;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

public class EvilMind {

    private static int black(String code, String guess) {
        return (int) IntStream.range(0, code.length())
                .filter(i -> code.charAt(i) == guess.charAt(i))
                .count();
    }

    private static int blackAndWhite(String code, String guess) {
        List<Integer> unmatchedChars = guess.chars().boxed().collect(Collectors.toList());
        return (int) code.chars()
                .filter(c -> unmatchedChars.remove((Integer)c))
                .count();
    }

    private static List<String> allSolutions(int length, int digits) {
        return IntStream.range(0, (int) Math.pow(digits, length))
                .mapToObj(i -> Integer.toString(i, digits))
                .map(s -> Strings.padStart(s, length, '0'))
                .collect(Collectors.toList());
    }

    /**
     * Find the least helpful master mind score
     */
    private static Entry<List<Integer>, List<String>> evil(List<String> allSolutions, String guess) {
        Map<List<Integer>, List<String>> groupedByScore = allSolutions.stream()
                .filter(s -> !s.equals(guess))
                .collect(Collectors.groupingBy(solution -> {
                    int black = black(solution, guess);
                    return ImmutableList.of(black, blackAndWhite(solution, guess) - black);
                }));

        return groupedByScore.entrySet().stream()
                .reduce((x, y) -> x.getValue().size() > y.getValue().size() ? x : y)
                .get();
    }

    public static void main(String[] arg) {
        Random random = new Random(42);
        for (int i = 0; i < 100; i++) {
            System.err.println("--------------- Game " + (i + 1) + " ------------");
            List<String> solutions = allSolutions(4, 6);
            while (solutions.size() > 1) {
                String guess = solutions.get(random.nextInt(solutions.size()));
                Entry<List<Integer>, List<String>> evil = evil(solutions, guess);
                System.err.println("Guess: " + guess + ", Score: " + evil.getKey().toString() + ", Solutions: " + evil.getValue().size());
                solutions = evil.getValue();
            }
            System.err.println("Solution: " + solutions.get(0));
        }
    }
}
