public class MyList{
    Node head, tail;
    int size;
    
    public MyList(){
        head=tail=null;
        size=0;
    }
    
    public boolean isEmpty(){
        return head==null;
    }
    
    public void clear(){
        head=null;
    }
    
    public void addFirst(int x){
        Node p=new Node(x, null);
        if(isEmpty())
            head=tail=p;
        else{
            p.next=head;
            head=p;
        }
        size++;
    }
    
    public void addLast(int x){
        Node p=new Node(x, null);
        if(isEmpty())
            head=tail=p;
        else{
            tail.next=p;
            tail=p;
        }
        size++;
    }
    
    public void traverse(){
        Node p=head;
        while(p!=null){
            System.out.println(" " + p.info);
            p=p.next;
        }
    }
}