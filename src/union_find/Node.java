package union_find;

public class Node {
    public int val;
    public Node parent;
    public int rank;

    public Node(int val) {
        this.val = val;
        this.parent = null;
        this.rank = 0;
    }
}
