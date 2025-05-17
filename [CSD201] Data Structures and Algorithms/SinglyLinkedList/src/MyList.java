public class MyList{
    Node headNode, tailNode;
    int size;

    public MyList(){
        headNode=tailNode=null;
        size=0;
    }
    
    public boolean isEmpty(){
        return headNode==null;
    }
    
    public void clear(){
        headNode=null;
    }
    
    public void addLast(int x){
        if(x%2!=0) return;
        Node p=new Node(x, null);
        if(isEmpty()){
            headNode=tailNode=p;
        } else{
            tailNode.Next=p;
            tailNode=p;
        }
        size++;
    }
    
    public void addFirst(int x){
        if(x%2!=0) return;
        Node p=new Node(x, null);
        if(isEmpty()){
            headNode=tailNode=p;
        } else{
            p.Next=headNode;
            headNode=p;
        }
        size++;
    }
    
    public void remove(Node p){
        
    }
    
    public void removeFirst(){
        if(isEmpty()) return;
        headNode=headNode.Next;
        size--;
    }
    
    public void removeLast(){
        if(isEmpty()) return;
        
    }
    
    public void traverse(){
        Node p=headNode;
        while(p!=null){            
            System.out.println(" " + p.info);
            p=p.Next;
        }
    }
   
}