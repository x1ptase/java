
class Node {

    Item info;
    Node next;

    Node(Item x, Node p) {
        info = x;
        next = p;
    }

    Node(Item x) {
        this(x, null);
    }
}
