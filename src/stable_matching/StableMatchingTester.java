package stable_matching;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class StableMatchingTester {
    @Test
    public void testSimple() {
        int[][] menPreferences = {{0, 1, 2, 3, 4},
                                  {1, 2, 3, 0, 4},
                                  {2, 3, 0, 1, 4},
                                  {3, 0, 1, 2, 4},
                                  {0, 1, 2, 3, 4}};
        int[][] womenPreferences = {{1, 2, 3, 4, 0},
                                    {2, 3, 4, 0, 1},
                                    {3, 4, 0, 1, 2},
                                    {4, 0, 1, 2, 3},
                                    {0, 1, 2, 3, 4}};
        StableMatchingSolver solver = new StableMatchingSolver(menPreferences, womenPreferences);
        Map<Integer, Integer> output = solver.getMatching();

        System.out.println(output);

        Map<Integer, Integer> reverseMatch = new HashMap<>();
        for (int i : output.keySet()) {
            reverseMatch.put(output.get(i), i);
        }

        assert StableMatchingSolver.isStable(output, reverseMatch, menPreferences, womenPreferences);
    }
}
