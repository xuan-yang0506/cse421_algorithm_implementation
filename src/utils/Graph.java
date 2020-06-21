package utils;

import java.util.List;

public interface Graph {
    public int numberOfVertices();
    public void addEdge(int from, int to, double weight);
    public void addEdge(int from, int to);
    public void removeEdge(int from, int to);
    public List<Integer> getNeighbors(int vertex);
    public List<Integer> getParents(int vertex);
}
