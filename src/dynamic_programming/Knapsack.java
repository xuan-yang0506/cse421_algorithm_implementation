/**
 * Created by Sean Yang on June 28, 2020
 * This program is an implementation of the Knapsack problem algorithm introduced in CSE421.
 */

package dynamic_programming;

public class Knapsack {

    private static class Item {
        int number;
        int value;
        int weight;

        public Item(int number, int value, int weight) {
            this.number = number;
            this.value = value;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Item[] items = new Item[5];
        items[0] = new Item(1, 1, 1);
        items[1] = new Item(2, 6, 2);
        items[2] = new Item(3, 18, 5);
        items[3] = new Item(4, 22, 6);
        items[4] = new Item(5, 28, 7);

        System.out.println(getMaxKnapsack(items, 11));
    }

    public static int getMaxKnapsack(Item[] items, int capacity) {
        /*
        Let OPT(i, w) = Max value subset of items 1, ..., i of weight 0 <= w <= W

        Case 1: OPT(i, w) selects item i
        • In this case, OPT (i, w) = v_i + OPT(i − 1, w - w_i)
        Case 2: OPT(i, w) does not select item i
        • In this case, OPT(i, w) = OPT(i − 1, w).

        for w = 0 to W
            M[0, w] = 0
        for i = 1 to n
            for w = 1 to W
                if (wi > w)
                    M[i, w] = M[i-1, w]
                else
                    M[i, w] = max {M[i-1, w], vi + M[i-1, w-wi ]}
        return M[n, W]
         */
        int n = items.length;
        int[][] matrix = new int[n + 1][capacity + 1];
        for (int i = 0; i < capacity + 1; i++) {
            matrix[0][i] = 0;
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int w = 1; w < matrix[0].length; w++) {
                if (items[i - 1].weight > w) {
                    matrix[i][w] = matrix[i - 1][w];
                } else {
                    matrix[i][w] = Math.max(matrix[i - 1][w],
                            items[i - 1].value + matrix[i - 1][w - items[i - 1].weight]);
                }
            }
        }

        // printing the whole Knapsack table
        for (int[] arr : matrix) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        return matrix[n][capacity];
    }
}
