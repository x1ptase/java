public class MyStack{
    Node top;
    int size;
    
    public MyStack(){
        top=null;
        size=0;
    }
    
    public void clear(){
        top=null;
        size=0;
    }
    
    public boolean isEmpty(){
        return top==null;
    }
    
    public void push(Student x){  //=addFirst(int x)
        Node p=new Node(x, null);
        if(isEmpty()){
            top=p;
        } else{
            p.next=top;
            top=p;
        } 
        size++;
    }
    
    public void pop(){  //=removeLast()
        if(isEmpty()){
            return;
        }
        top=top.next;
        size--;
    }
    
    public Student peek(){
        if(!isEmpty()){
            return top.info;
        } else{
            return null;
        }
    }

    public void traverse(){
        Node p=top;
        while(p!=null){
            System.out.println(p.info);
            p=p.next;
        }
    }
}