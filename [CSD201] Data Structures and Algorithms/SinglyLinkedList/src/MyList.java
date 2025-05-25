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
        if(isEmpty()) return;
        // Nếu phần tử đầu chia hết cho 3
        if(head.info % 3 == 0){
            removeFirst();
            return;
        }
        Node prev = head;
        Node curr = head.next;
        while(curr != null){
            if(curr.info % 3 == 0){
                prev.next = curr.next;
                if(curr == tail) tail = prev;
                size--;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }
    
    public void removeFirst(){
        if(isEmpty()) 
            return;
        head=head.next;
        if(head==null) 
            tail=null; // Neu danh sach chi co 1 phan tu
        size--;
    }

    public void removeLast(){
        if(isEmpty()) return;
        if(head == tail) { 
            head = tail = null;
        } else {
            Node p = head;
            while(p.next != tail) {
                p = p.next;
            }
            p.next = null;
            tail = p;
        }
        size--;
    }
    
    public Node search(int x) {
        Node p = head;
        while(p != null) {
            if(p.info == x) return p;
            p = p.next;
        }
        return null;
    }
    
    public boolean contains(int x) {
        return search(x) != null;
    }
    
    public void insertAfter(int target, int x) {
        Node p = search(target);
        if(p == null) return; // Không tìm thấy target

        Node newNode = new Node(x, p.next);
        p.next = newNode;

        if(p == tail) tail = newNode; // Nếu chèn sau tail thì cập nhật lại tail

        size++;
    }

    public void traverse(){
        Node p=head;
        while(p!=null){
            System.out.print(" " + p.info);
            p=p.next;
        }
    }
}