
import java.util.*;
import java.io.*;

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
        if(size > 0 && price > 0){
            Node p=new Node(new Room(code, status, size, price));
            if(isEmpty()){
                head=tail=p;
            } else{
                tail.next=p;
                tail=p;
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

    void loadDataRequest(int k) //do not edit this function
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
        if(size > 0 && price > 0){
            Node p=new Node(new Room(size, price));
            if(isEmpty()){
                front=rear=p;
            } else{
                rear.next=p;
                rear=p;
            }
        }
        //---------------------------------------------------------
    }

    Room deQueue() {
        Room tmp = new Room();
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        if(isEmpty()) return null;
        tmp=front.info;
        front=front.next;
        if(front == null) rear=null;
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

    void load(int k) throws Exception //do not edit this function
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

    void rent(Room t) throws Exception {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        Node p=dList.head;
        Room bestRoom=null;
        
        while(p != null){
            if(p.info.getStatus() == 0){
                if(p.info.getSize() >= t.getSize()){
                    if(p.info.getPrice() <= t.getPrice()){
                        bestRoom=p.info;
                        break;
                    }
                }
            }
            p=p.next;
        }
        
        while(p != null){
            if(p.info.getStatus() == 0){
                if(p.info.getSize() >= t.getSize()){
                    if(p.info.getPrice() <= t.getPrice()){
                        if(p.info.getPrice() <= bestRoom.getPrice())
                            bestRoom=p.info;
                    }
                }
            }
            p=p.next;
        }
        
        if(bestRoom != null){
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
        Room req=RQueue.deQueue();
        rent(req);
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
        Room req=RQueue.deQueue();
        while(req != null){
            rent(req);
            req=RQueue.deQueue();
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
        Room req=RQueue.deQueue();
        while(req != null){
            rent(req);
            req=RQueue.deQueue();
        }
        
        Node p=dList.head;
        while(p != null){
            if(p.info.getStatus() == 1){
                result+=p.info.getPrice();
            }
            p=p.next;
        }
        //---------------------------------------------------------
        ftraverse(f);
        f.writeBytes("Total Revenue: " + result + " ");
        f.close();
    }

}
