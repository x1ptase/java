

public class CarQueue {
    Node front;
    Node rear; 
    int length;

    public CarQueue(){
        this.front = null;
        this.rear = null;
        this.length = 0;
    }

    public int length(){
        return length;
    }

    public boolean isEmpty(){
        return this.length == 0;
    }

    void loadData(int k) {
        String[] bookIDs = Lib.readLineToStrArray("data.txt", k);
        String[] authors = Lib.readLineToStrArray("data.txt", k + 1);
        double[] prices = Lib.readLineToDoubleArray("data.txt", k + 2);
        
        int n = bookIDs.length;
        for (int i = 0; i < n; i++) {
            enqueue(bookIDs[i], authors[i], prices[i]);
        }
    }

    public void enqueue(String bookID, String author, double price){
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        Car car=new Car(bookID, author, price);
        Node p=new Node(car);

        if(isEmpty()){
            front=rear=p;
        } else{
            rear.next=p;
            rear=p;
        }
        //---------------------------------------------------------

        this.length++;
    }

    public Car dequeue(){
        //You should write here appropriate statements to complete this function.
        //YOU CAN ADD NEW FUNCTIONS IN THE FOLLOWING PART IF NEEDED
        //--------------------------------------------------------
        Car tmp=front.info;
        front=front.next;
        if(front == null){
            rear=null;
        }
        this.length--;
        //---------------------------------------------------------
        return null; //if no book 
    }

    public boolean remove(String id){
        //You should write here appropriate statements to complete this function.
        //YOU CAN ADD NEW FUNCTIONS IN THE FOLLOWING PART IF NEEDED
        //--------------------------------------------------------
        if(front.info.getID().equals(id)){
            front=front.next;
            length--;
            if(front == null){
                rear=null;
            }
            return true;
        }
        
        Node current=front;
        while(current.next != null){
            if(current.next.info.getID().equals(id)){
                current.next=current.next.next;
                length--;
                if(current.next == null){
                    rear=current;
                }
                return true;
            }
            current=current.next;
        }
        //---------------------------------------------------------
        return false;
    }
    
    public void displayBooks(){
        if (this.isEmpty()){
            System.out.println("Book Queue is empty. No books to display.");
            return;
        }
        Node current = this.front;
        while(current != null){
            System.out.println(current.info.bookID + " --> ");
            current = current.next;
        }
        System.out.println("END");
    }
}
