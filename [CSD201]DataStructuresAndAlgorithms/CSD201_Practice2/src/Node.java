

class Node {

    Car info;
    Node next;

    Node(Car info, Node next) {
        this.info = info;
        this.next = next;
    }

    Node(Car info) {
        this(info, null);
    }
}