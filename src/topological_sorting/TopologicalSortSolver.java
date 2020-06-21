/**
 * Created by Sean Yang on June 21, 2020
 * This program is an implementation of the topological sorting algorithm introduced in CSE421.
 * It also contains a validation method to validate a topological sorting.
 */

package topological_sorting;

import utils.Graph;

import java.util.*;

public class TopologicalSortSolver {
    private Graph graph;

    /**
     * Constructor, takes in a graph
     * @param graph a graph to be sorted
     */
    public TopologicalSortSolver(Graph graph) {
        this.graph = graph;
    }

    /**
     * returns the topologically sorted list of all the vertices
     * @return - a topologically sorted list of vertices
     */
    public List<Integer> returnTopologicalSortedList() {

        /*
            pseudo code:

            Maintain the following:
                count[w] = (remaining) number of incoming edges to node w
                S = set of (remaining) nodes with no incoming edges
            Initialization:
                count[w] = 0 for all w
                count[w]++ for all edges (v,w) O(m + n)
                S = S UNION {w} for all w with count[w]=0
            Main loop:
            while S not empty
                • remove some v from S
                • make v next in topo order O(1) per node
                • for all edges from v to some w O(1) per edge
                –decrement count[w]
                –add w to S if count[w] hits 0
            Correctness: clear, I hope
            Time: O(m + n) (assuming edge-list representation of graph)
         */


        // initialization

        // number of incoming edges to each node
        int[] count = new int[graph.numberOfVertices()];
        for (int i = 0; i < graph.numberOfVertices(); i++) {
            count[i] = graph.getParents(i).size();
        }

        // create a set of nodes with no incoming edges
        // create a set of all nodes first, then remove every possible neighbors
        Set<Integer> all = new HashSet<>();
        for (int i = 0; i < graph.numberOfVertices(); i++) {
            all.add(i);
        }
        for (int i = 0; i < graph.numberOfVertices(); i++) {
            List<Integer> neighbors = graph.getNeighbors(i);
            for (int n : neighbors) {
                if (all.contains(n)) {
                    all.remove(n);
                }
            }
        }
        Queue<Integer> noIncomingEdgeSet = new LinkedList<>(all);


        // main loop:
        List<Integer> output = new LinkedList<>();

        while (!noIncomingEdgeSet.isEmpty()) {
            int v = noIncomingEdgeSet.poll();
            output.add(v);
            List<Integer> neighbors = graph.getNeighbors(v);
            for (int i : neighbors) {
                count[i]--;
                if (count[i] == 0) {
                    noIncomingEdgeSet.add(i);
                }
            }
        }

        return output;
    }

    /**
     * static method to validate the topological sorting of the list
     * @param list - a list in topological sorting to be verified
     * @param g - graph of the vertices
     * @return true if the list is a correct topological sort
     *         false otherwise
     */
    public static boolean isTopologicalSorted(List<Integer> list, Graph g) {
        for (int i = 0; i < list.size(); i++) {
            int current = list.get(i);
            // check every neighbor of this vertices, if any of them appears ahead of it
            // in the list, return false
            for (int neighbor : g.getNeighbors(current)) {
                if (list.indexOf(neighbor) >= i) {
                    return false;
                }
            }
        }
        return true;
    }
}
