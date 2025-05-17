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
        if(x%2!=0) return;
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
        if(x%2==0) return;
        Node p=new Node(x, null);
        if(isEmpty())
            head=tail=p;
        else{
            tail.next=p;
            tail=p;
        }
        size++;
    }
    
    public void remove(){
    }
    
    public void traverse(){
        Node p=head;
        while(p!=null){
            System.out.print(" " + p.info);
            p=p.next;
        }
    }
}