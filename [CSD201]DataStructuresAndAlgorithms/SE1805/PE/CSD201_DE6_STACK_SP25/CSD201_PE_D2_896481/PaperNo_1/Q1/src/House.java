
import java.util.*;
import java.io.*;

class ItemList {

    Node head, tail;

    ItemList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    Node deleteFirstFragile() {
        Node result = null;
        //You should write here appropriate statements to complete this function.
        //----------------------------------------------------------------------
        Node p=head;
        Node prev=null;
        
        while(p != null){
            if(p.info.getFragile() == 1){
                result=p;
                if(p == head){
                    head=head.next;
                } else if(p == tail){
                    tail=prev;
                    tail.next=null;
                } else{
                    prev.next=p.next;
                }
                break;
            }
            prev=p;
            p=p.next;
        }
        //----------------------------------------------------------------------
        return result;
    }

    void loadItemList(int k) //do not edit this function
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

    void addLast(String name, int weight, int length, int fragile) {
        //You should write here appropriate statements to complete this function.
        //----------------------------------------------------------------------
        if(weight > 0 && length > 0){
            Node p=new Node(new Item(name, weight, length, fragile));
            if(isEmpty()){
                head=tail=p;
            } else{
                tail.next=p;
                tail=p;
            }
        }
        //----------------------------------------------------------------------
    }

}

class Truck {

    Node head;

    Truck() {
        head = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = null;
    }

    void loadTruck(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k + 4);
        int[] b = Lib.readLineToIntArray("data.txt", k + 5);
        int[] c = Lib.readLineToIntArray("data.txt", k + 6);
        int[] d = Lib.readLineToIntArray("data.txt", k + 7);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            push(a[i], b[i], c[i], d[i]);
        }
    }

    void push(String name, int weight, int length, int fragile) {
        //You should write here appropriate statements to complete this function.
        //----------------------------------------------------------------------
        if(weight > 0 && length > 0){
            Node p=new Node(new Item(name, weight, length, fragile));
            if(isEmpty()){
                head=p;
            } else{
                p.next=head;
                head=p;
            }
        }
        //----------------------------------------------------------------------
    }

    void push(Node q) {
        if (this.isEmpty()) {
            head = q;
        } else {
            q.next = head;
            head = q;
        }
    }

    Node pop() {
        if (isEmpty()) {
            return null;
        }
        Node tmp = head;
        head = head.next;
        return tmp;
    }
}

class House {

    ItemList list = new ItemList();
    Truck truck = new Truck();

    House() {
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = list.head;
        f.writeBytes("ItemList Inventory: ");
        if (p == null) {
            f.writeBytes("Empty");
        }
        while (p != null) {
            fvisit(p, f);
            p = p.next;
        }
        f.writeBytes("\r\n");

        f.writeBytes("Truck Contents: ");
        p = truck.head;
        if (p == null) {
            f.writeBytes("Empty");
        }
        while (p != null) {
            fvisit(p, f);
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void load(int k) throws Exception //do not edit this function
    {
        list.loadItemList(k);
        truck.loadTruck(k);
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
        //You should write here appropriate statements to complete this function.
        //----------------------------------------------------------------------
        truck.push(list.deleteFirstFragile());
        //----------------------------------------------------------------------
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
        //----------------------------------------------------------------------
        Node p=list.head;
        while(p != null){
            if(p.info.getFragile() == 1){
                truck.push(list.deleteFirstFragile());
            }
            p=p.next;
        }
        
        //----------------------------------------------------------------------
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
        int totalWeight = 0;
        //You should write here appropriate statements to complete this function.
        //----------------------------------------------------------------------
        Node p=list.head;
        while(p != null){
            if(p.info.getFragile() == 1){
                truck.push(list.deleteFirstFragile());
            }
            p=p.next;
        }
        
        Node tmp=truck.head;
        while(tmp != null){
            totalWeight+=tmp.info.getWeight();
            tmp=tmp.next;
        }
        //----------------------------------------------------------------------
        ftraverse(f);
        f.writeBytes("Total weight: " + totalWeight + " ");
        f.close();
    }

}
