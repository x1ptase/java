
/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.File;
import java.io.RandomAccessFile;

class FruitList {

    Node head, tail;

    FruitList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void loadDataFruit(int k) // do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);
        }
    }

    void addLast(String type, int amount, int price) {
        Node newNode = new Node(new Fruit(type, amount, price));
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

}

class RequestQueue {

    Node front, rear;

    RequestQueue() {
        front = rear = null;
    }

    boolean isEmpty() {
        return (front == null);
    }

    void clear() {
        front = rear = null;
    }

    void loadDataRequest(int k) // do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k + 3);
        int[] b = Lib.readLineToIntArray("data.txt", k + 4);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            enQueue(a[i], b[i]);
        }
    }

    void enQueue(String type, int amount) {
        Node newNode = new Node(new Fruit(type, amount, 0));
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    Fruit deQueue() {
        if (isEmpty()) {
            return null;
        }
        Fruit tmp = front.info;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return tmp;
    }

}

class MyStore {

    FruitList FList = new FruitList();
    RequestQueue RQueue = new RequestQueue();

    MyStore() {
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = FList.head;
        f.writeBytes("Data Fruit: ");
        if (p == null) {
            f.writeBytes("Empty");
        }
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
        f.writeBytes("Request   : ");
        p = RQueue.front;
        if (p == null) {
            f.writeBytes("Empty");
        }
        while (p != null) {
            f.writeBytes("(" + p.info.getType() + "," + p.info.getAmount() + ") ");
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void load(int k) throws Exception {
        FList.loadDataFruit(k);
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

    void f2() throws Exception {
        load(1);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);

        // Sử dụng hàm purchase cho một phần tử đầu tiên của queue
        Fruit t = RQueue.deQueue();
        if (t != null) {
            purchase(t);
        }

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

        // Theo cấu trúc gợi ý trong hình
        Fruit t = RQueue.deQueue();
        while (t != null) {
            purchase(t);
            t = RQueue.deQueue();
        }

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

        // Theo cấu trúc gợi ý trong hình
        int S = 0;
        Fruit t = RQueue.deQueue();
        while (t != null) {
            S += purchase(t);
            t = RQueue.deQueue();
        }

        f.writeBytes("Money     : " + S + "\r\n");
        f.close();
    }

    // Thêm hàm purchase để tính toán và trả về số tiền

    private int purchase(Fruit request) {
        Node p = FList.head;
        while (p != null) {
            if (p.info.getType().equals(request.getType())) {
                // Only purchase if the fruit exists and can be bought
                if (!request.getType().equals("M") && !request.getType().equals("F")) {
                    int purchaseAmount = Math.min(request.getAmount(), p.info.getAmount());
                    if (purchaseAmount > 0) {
                        // Update the remaining amount in the fruit list
                        p.info.setAmount(p.info.getAmount() - purchaseAmount);
                        // Calculate and return the money
                        return purchaseAmount * p.info.getPrice();
                    }
                }
                break;
            }
            p = p.next;
        }
        return 0;
    }
    

}
