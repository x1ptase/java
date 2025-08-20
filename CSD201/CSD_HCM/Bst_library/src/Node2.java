class Node2 {
    Book info;
    Node2 next;

    Node2(Book x, Node2 p) {
        info = x;
        next = p;
    }

    Node2(Book x) {
        this(x, null);
    }
} 