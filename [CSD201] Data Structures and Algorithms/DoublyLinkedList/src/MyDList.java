public class MyDList {
    Node header, trailer;
    int size;

    public MyDList() {
        header=trailer = null;
        size=0;
    }

    public boolean isEmpty() {
        return header==null;
    }

    public void clear(){
        header=trailer=null;
        size=0;
    }

    public void addFirst(Car x){
        Node p=new Node(x, null, null);
        if(isEmpty()) {
            header=trailer=p;
        } else{
            p.next=header;
            header.prev=p;
            header=p;
        } size++;
    }

    public void addLast(Car x){
        Node p=new Node(x, null, null);
        if(isEmpty()){
            header=trailer=p;
        } else{
            trailer.next=p;
            p.prev=trailer;
            trailer=p;
        } size++;
    }
    
    public void removeFirst(){
        if (isEmpty()) return;
        if(header==trailer){
            header=trailer=null;
        } else{
            header=header.next;
            header.prev=null;
        } size--;
    }

    public void removeLast(){
        if(isEmpty()) return;
        if(header==trailer){
            header=trailer=null;
        } else{
            trailer=trailer.prev;
            trailer.next=null;
        } size--;
    }
    

    public void removeFirstRed(){
        Node p=header;
        while(p!=null) {
            if(p.info.getColor().equalsIgnoreCase("Red")){
                if(p==header){
                    removeFirst();
                } else if(p==trailer){
                    removeLast();
                } else{
                    p.prev.next=p.next;
                    p.next.prev=p.prev;
                    size--;
                }
                return; // delete red at first
            }
            p=p.next;
        }
    }

    public void removeLastToyota(){
        Node p=trailer;
        while(p!=null){
            if(p.info.getBrand().equalsIgnoreCase("Toyota")){
                if(p==trailer){
                    removeLast();
                } else if(p==header){
                    removeFirst();
                } else{
                    p.prev.next=p.next;
                    p.next.prev=p.prev;
                    size--;
                }
                return; // delete toyota at last
            }
            p=p.prev;
        }
    }

    public void countByColor(String color){
        int count=0;
        Node p=header;
        while(p!=null){
            if(p.info.getColor().equalsIgnoreCase(color)){
                count++;
            }
            p=p.next;
        }
        System.out.println("Car with colors" + color + ": "+ count);
    }

    public void traverse(){
        Node p=header;
        while(p!=null){
            System.out.println(p.info);
            p=p.next;
        }
    }
}