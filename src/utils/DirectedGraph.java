package utils;

import java.util.LinkedList;
import java.util.List;

public class DirectedGraph implements Graph {
    private double[][] matrix;

    public DirectedGraph(int numberOfVertices) {
        this.matrix = new double[numberOfVertices][numberOfVertices];
    }

    @Override
    public int numberOfVertices() {
        return matrix.length;
    }

    @Override
    public void addEdge(int from, int to, double weight) {
        if (from == to) {
            throw new IllegalArgumentException("Does not support self edge.");
        }
        matrix[from][to] = weight;
    }

    public void addEdge(int from, int to) {
        addEdge(from, to, 0);
    }

    @Override
    public void removeEdge(int from, int to) {
        if (from == to) {
            throw new IllegalArgumentException("From and to are the same");
        }
        matrix[from][to] = 0;
    }

    @Override
    public List<Integer> getNeighbors(int vertex) {
        List<Integer> output = new LinkedList<>();
        for (int i = 0; i < matrix[vertex].length; i++) {
            if (matrix[vertex][i] != 0) {
                output.add(i);
            }
        }
        return output;
    }
}
