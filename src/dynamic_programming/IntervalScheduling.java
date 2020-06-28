/**
 * Created by Sean Yang on June 28, 2020
 * This program is an implementation of the weighted interval scheduling algorithm introduced in CSE421.
 */

package dynamic_programming;

import java.util.Arrays;
import java.util.Comparator;

public class IntervalScheduling {

    // weighted job class
    private static class Job {
        int startTime;
        int endTime;
        int weight;

        public Job(int startTime, int endTime, int weight) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Job[] jobs = new Job[8];
        jobs[0] = new Job(1, 4, 3);
        jobs[1] = new Job(3, 5, 4);
        jobs[2] = new Job(0, 6, 1);
        jobs[3] = new Job(4, 7, 3);
        jobs[4] = new Job(3, 8, 4);
        jobs[5] = new Job(5, 9, 3);
        jobs[6] = new Job(6, 10, 2);
        jobs[7] = new Job(8, 11, 4);

        System.out.println(getMaxScheduling(jobs));
    }

    public static int getMaxScheduling(Job[] jobs) {
        /*
        Sorting Idea: Label jobs by finishing time

        Case 1: Suppose OPT has job n.
        • So, all jobs i that are not compatible with n are not OPT
        • Let p(n) = largest index i < n such that job i is compatible with n.
        • Then, we just need to find OPT of 1, … , p(n)
        Case 2: OPT does not select job n.
        • Then, OPT is just the optimum 1, … , n − 1
         */

        Arrays.sort(jobs, Comparator.comparingInt(a -> a.endTime));
        int[] dp = new int[jobs.length + 1];
        int[] p = new int[jobs.length + 1];
        p[0] = 0;
        for (int i = 1; i < p.length; i++) {
            Job current = jobs[i - 1];
            p[i] = 0;
            for (int j = 1; j < i; j++) {
                if (jobs[j - 1].endTime <= current.startTime) {
                    p[i] = j;
                }
            }
        }

        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            // case 1: OPT has job n
            int has = jobs[i - 1].weight + dp[p[i]];
            // case 2: OPT does not have job n
            int doNotHave = dp[i - 1];
            dp[i] = Math.max(has, doNotHave);
        }
        System.out.println(Arrays.toString(dp));
        return dp[dp.length - 1];
    }
}
