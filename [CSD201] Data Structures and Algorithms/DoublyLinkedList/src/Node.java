public class Node {
    Car info;
    Node prev, next;
    
    public Node(){
    }
    
    public Node(Car info, Node prev, Node next){
        this.info=info;
        this.prev=prev;
        this.next=next;
    }
}
