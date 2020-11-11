package union_find;

public class DisjointSet {
    public static void join(Node n1, Node n2) {
        if (n1.rank < n2.rank) {
            joinHelper(n2, n1);
        } else {
            joinHelper(n1, n2);
        }
    }

    public static boolean isJoined(Node n1, Node n2) {
        return findParent(n1) == findParent(n2);
    }

    private static void joinHelper(Node parent, Node child) {
        Node pp = findParent(parent);
        Node cp = findParent(child);
        joinParent(pp, cp);
    }

    private static Node findParent(Node node) {
        Node current = node;
        while (current.parent != null) {
            current = current.parent;
        }
        return current;
    }

    private static void joinParent(Node parent, Node child) {
        child.parent = parent;
        parent.rank = Math.max(parent.rank, child.rank + 1);
    }

}
