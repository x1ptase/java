public class Tester{
    public static void main(String[] args){
        MyQueue mq=new MyQueue();
        
        System.out.println("Before");
        mq.enqueue(10);
        mq.enqueue(20);
        mq.traverse(); 
        
        System.out.println();
        System.out.println("After");
        mq.dequeue();
        mq.traverse(); 
    }
}