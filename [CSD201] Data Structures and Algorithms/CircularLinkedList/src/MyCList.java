public class MyCList {
    Node head, tail;
    int size=0;
    
    public MyCList(){
        head=tail=null;
    }
    
    public boolean isEmpty(){
        return head==null;
    }
    
    public void clear(){
       head=tail=null;
    }
  
    public void addFirst(int x){
        Node p=new Node(x, null);
        if(isEmpty()){
            head=tail=p;
            tail.next=head; // create
            return;
        }
        p.next=head;
        head=p;
        tail.next=head; // update
        size++;
    }
    
    public void addLast(int x){
        Node p=new Node(x, null);
        if(isEmpty()){
            head=tail=p;
            tail.next=head;
            return;
        }
        tail.next=p;
        tail=p;
        tail.next=head;
        size++;
    }
    
    public void traverse(){
        Node p=head;
        if(isEmpty()) return;
        do{
            System.out.print(" "+p.info);
            p=p.next;
        } while(p!=head);    // stop when p == head
    }
}
