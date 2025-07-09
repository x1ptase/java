class Node {

    Room info;
    Node next;

    Node(Room x, Node p) {
        info = x;
        next = p;
    }

    Node(Room x) {
        this(x, null);
    }
}
