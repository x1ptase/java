
import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Set;

class dataList {

    Node head, tail;

    dataList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void loadDataRoom(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int[] d = Lib.readLineToIntArray("data.txt", k + 3);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i], d[i]);
        }
    }

    void addLast(String code, int status, int size, int price) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        if (size > 0 && price > 0) {
            Node nodeMoi = new Node(new Room(code, status, size, price));
            if (isEmpty()) {
                head = tail = nodeMoi;
            } else {
                tail.next = nodeMoi;
                tail = nodeMoi;
            }
        }

        //---------------------------------------------------------
    }

}

class requestQueue {

    Node front, rear;

    requestQueue() {
        front = rear = null;
    }

    boolean isEmpty() {
        return (front == null);
    }

    void clear() {
        front = rear = null;
    }

    void loadDataRequest(int k) //d o not edit this function
    {
        int[] a = Lib.readLineToIntArray("data.txt", k + 4);
        int[] b = Lib.readLineToIntArray("data.txt", k + 5);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            enQueue(a[i], b[i]);
        }
    }

    void enQueue(int size, int price) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        if (size > 0 && price > 0) {
            Node nodeMoi = new Node(new Room(size, price));
            if (isEmpty()) {
                front = rear = nodeMoi;
            } else {
                rear.next = nodeMoi;
                rear = nodeMoi;
            }
        }
        //---------------------------------------------------------
    }

    Room deQueue() {
        Room tmp = new Room(); // tmp nay la mot doi tuong ROOM chua co cai thong tin gi ca ?
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        if (isEmpty()) {
            return null;
        }
        tmp = front.info; // tmp ROOM == front la node -> front.info
        front = front.next;
        if (front == null) {
            rear = null;
        }
        //---------------------------------------------------------
        return tmp;
    }

}

class Hotel {

    dataList dList = new dataList();
    requestQueue RQueue = new requestQueue();

    Hotel() {
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = dList.head;
        f.writeBytes("Data List: ");
        if (p == null) {
            f.writeBytes("Empty");
        }
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
        f.writeBytes("Request  : ");
        p = RQueue.front;
        if (p == null) {
            f.writeBytes("Empty");
        }
        while (p != null) {
            f.writeBytes("(" + p.info.getSize() + "," + p.info.getPrice() + ") ");
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void load(int k) throws Exception //d o not edit this function
    {
        dList.loadDataRoom(k);
        RQueue.loadDataRequest(k);
    }

    void f1() throws Exception {
        load(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

    void rent(Room t) throws Exception { // t la room request
        //You should write here appropriate statements to complete this function.
        //- -------------------------------------------------------
        Node current = dList.head; // node hien tai, bat dau tu head
        Room bestRoom = null; // best Room
        // Trong truong hop, co phong phu hop thi de dang tim duoc phong BestRoom
        while (current != null) {
            if (current.info.getStatus() == 0) {
                if (current.info.getSize() >= t.getSize()) {
                    if (current.info.getPrice() <= t.getPrice()) {
                        bestRoom = current.info;
                        break;
                    }
                }
            }
            current = current.next;
        }

        while (current != null) {
            if (current.info.getStatus() == 0) {
                if (current.info.getSize() >= t.getSize()) {
                    if (current.info.getPrice() <= t.getPrice()) {
                        if(current.info.getPrice() <= bestRoom.getPrice() ){
                             bestRoom = current.info;
                        }
                    }
                }
            }
            current = current.next;
        }

        if (bestRoom != null) {
            bestRoom.setStatus(1);
        }
        //---------------------------------------------------------
    }

    void f2() throws Exception {
        load(1);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        Room request = RQueue.deQueue(); // Room request (1,100)
        rent(request);
        //---------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    void f3() throws Exception {
        load(1);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        Room request = RQueue.deQueue(); // Khach dau tien
        while(request != null){
            rent(request);
            request = RQueue.deQueue();
        }

        //---------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    void f4() throws Exception {
        load(1);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        int result = 0;
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        Room request = RQueue.deQueue(); // Khach dau tien
        while(request != null){
            rent(request);
            request = RQueue.deQueue();
        }
        
         Node current = dList.head; // Chay lai dList
         
         while(current !=null){
             if(current.info.getStatus() == 1){
                 result += current.info.getPrice();
             }
             current = current.next;
         }
        

        //---------------------------------------------------------
        ftraverse(f);
        f.writeBytes("Total Revenue: " + result + " ");
        f.close();
    }

}
