// ListNode.java
class ListNode {
    Student info;
    ListNode next;

    ListNode(Student x, ListNode p) {
        info = x;
        next = p;
    }

    ListNode(Student x) {
        this(x, null);
    }
}