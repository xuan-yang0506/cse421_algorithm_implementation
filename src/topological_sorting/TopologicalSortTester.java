package topological_sorting;

import org.junit.Test;
import utils.DirectedGraph;
import utils.Graph;

import java.util.List;

public class TopologicalSortTester {
    @Test
    public void simpleTest() {
        Graph graph = new DirectedGraph(7);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(0, 6);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);

        TopologicalSortSolver solver = new TopologicalSortSolver(graph);
        List<Integer> topologicalSorted = solver.returnTopologicalSortedList();
        assert TopologicalSortSolver.isTopologicalSorted(topologicalSorted, graph);
    }
}
