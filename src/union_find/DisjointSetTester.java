package union_find;


import org.junit.Test;

public class DisjointSetTester {
    @Test
    public void simpleTest() {
        Node a = new Node(1);
        Node b = new Node(2);
        assert !(DisjointSet.isJoined(a, b));
        DisjointSet.join(a, b);
        assert DisjointSet.isJoined(a, b);

        Node c = new Node(3);
        assert !(DisjointSet.isJoined(a, c));
        assert !(DisjointSet.isJoined(b, c));
        DisjointSet.join(b, c);
        assert DisjointSet.isJoined(a, c);
        assert DisjointSet.isJoined(b, c);
    }
}
