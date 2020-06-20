package topological_sorting;

import utils.Graph;

import java.util.*;

public class TopologicalSortSolver {
    private Graph graph;

    public TopologicalSortSolver(Graph graph) {
        this.graph = graph;
    }

    public List<Integer> returnTopologicalSortedList() {
        // initialization

        // number of incoming edges to each node
        int[] count = new int[graph.numberOfVertices()];
        for (int i = 0; i < graph.numberOfVertices(); i++) {
            count[i] = graph.getNeighbors(i).size();
        }

        // set of nodes with no incoming edges
        Queue<Integer> noIncomingEdgeSet = new LinkedList<>();
        for (int i = 0; i < graph.numberOfVertices(); i++) {
            if (graph.getNeighbors(i).size() == 0) {
                noIncomingEdgeSet.add(i);
            }
        }

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

    public static boolean isTopologicalSorted(List<Integer> list, Graph g) {
        for (int i = 0; i < list.size(); i++) {
            int current = list.get(i);
            for (int neighbor : g.getNeighbors(current)) {
                if (list.indexOf(neighbor) >= i) {
                    return false;
                }
            }
        }
        return true;
    }
}
