public class MyQueue {
    Node front, rear; //head, tail
    int size;
    
    public MyQueue(){
        front=rear=null;
        size=0;
    }
    
    public void clear(){
        front=rear=null;
        size=0;
    }
    
    public boolean isEmpty(){
        return size==0;
    }
    
    public void enqueue(int x){  // addLast(int x)
        Node p=new Node(x, null);
        if(isEmpty()){
            front=rear=p;
        } else{
            rear.next=p;
            rear=p;
        }      
        size++;
    }
    
    public void dequeue(){ //removeFirst
        if(isEmpty()){
            System.out.println("Queue is empty");
        } else{
            front=front.next;
        }
        size--;
    }
    
    public int peek(){
        if(isEmpty()){
            System.out.println("Queue is empty");
        }
        return front.info;
    }
    
    public void traverse(){
        Node p=front;
        while(p!=null){
            System.out.print(p.info + " ");
            p=p.next;
        }
    }
}
