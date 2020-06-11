/**
 * Created by Sean Yang on June 11, 2020
 * This program is an implementation of the Gale-Shapley algorithm introduced in CSE-421
 * It also contains a static method to check if a matching is stable
 */

package stable_matching;

import java.util.*;

public class StableMatchingSolver {
    private int[][] menPreferences;
    private int[][] womenPreferences;
    private int numberOfPeople;

    /**
     * Constructor accepting two list of preferences from men and women
     * @param menPreferences - men preference of all women, ranking from highest to lowest.
     * @param womenPreferences - women preference of all men, ranking from highest to lowest.
     */

    public StableMatchingSolver(int[][] menPreferences, int[][] womenPreferences) {
        assert menPreferences.length == womenPreferences.length;
        this.menPreferences = menPreferences;
        this.womenPreferences = womenPreferences;
        numberOfPeople = menPreferences.length;
    }

    /**
     * Return the matching from the preference lists
     */
    public Map<Integer, Integer> getMatching() {
        // put all men into a queue
        Queue<Integer> men = new LinkedList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            men.add(i);
        }

        int[] wife = new int[numberOfPeople];
        int[] husband = new int[numberOfPeople];

        boolean[][] menProposed = new boolean[numberOfPeople][numberOfPeople];

        // set all people to free
        Arrays.fill(wife, -1);
        Arrays.fill(husband, -1);

        // create an inverse preference list to enable O(1) lookup for
        // preference comparison
        int[][] womenPreferenceInverse = new int[numberOfPeople][numberOfPeople];
        for (int i = 0; i < numberOfPeople; i++) {
            for (int j = 0; j < numberOfPeople; j++) {
                womenPreferenceInverse[i][womenPreferences[i][j]] = j;
            }
        }

        /*
          pseudo code:

          Initialize each person to be free.
          while (some man is free and hasn't proposed to every woman) {
             Choose such a man m
             w = 1st woman on m's list to whom m has not yet proposed
             if (w is free)
                assign m and w to be engaged
             else if (w prefers m to her fiancé m')
                assign m and w to be engaged, and m' to be free
             else
                w rejects m
             }

         */

        while (!men.isEmpty()) {
            int current = men.poll();
            for (int i = 0; i < menPreferences[current].length; i++) {
                if (menProposed[current][i]) {
                    // the man has proposed to this women
                    continue;
                }
                // try to propose to this woman
                menProposed[current][i] = true;
                if (husband[i] == -1) {
                    // if this woman is free, we assign them to be engaged
                    husband[i] = current;
                    wife[current] = i;
                    break;
                } else if (womenPreferenceInverse[i][husband[i]] > womenPreferenceInverse[i][current]) {
                    // if this woman prefers this man to her current fiancé, we assign them to be engaged
                    int previousHusband = husband[i];
                    husband[i] = current;
                    wife[current] = i;
                    wife[previousHusband] = -1;
                    men.add(previousHusband);
                    break;
                } else {
                    // otherwise, this women rejects this man
                    continue;
                }
            }
        }

        Map<Integer, Integer> output = new HashMap<>();
        for (int i = 0; i < wife.length; i++) {
            output.put(i, wife[i]);
        }
        return output;
    }

    /**
     * This method checks if a matching is stable
     * @param matching - man to women matching
     * @param womenToManMatching - women to man matching
     * @param menPreferences - man preference list
     * @param womenPreferences - women preference list
     * @return true if the matching is stable
     *         false otherwise
     */
    public static boolean isStable(Map<Integer, Integer> matching,
                                   Map<Integer, Integer> womenToManMatching,
                                   int[][] menPreferences,
                                   int[][] womenPreferences) {
        for (int husband : matching.keySet()) {
            int wife = matching.get(husband);
            for (int i = 0; i < menPreferences[husband].length; i++) {
                if (menPreferences[husband][i] == wife) {
                    break;
                }
                // this man prefers this women to his wife
                // then we need to check if this women prefers this man to her husband
                int[] herPreferenceList = womenPreferences[i];
                for (int j = 0; j < herPreferenceList.length; j++) {
                    // if we see her husband first, it is ok
                    // if we see this man before her husband, it is not ok
                    if (herPreferenceList[j] == womenToManMatching.get(i)) {
                        break;
                    }
                    if (herPreferenceList[j] == husband) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
