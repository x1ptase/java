
import java.util.*;
import java.io.*;

public class MyList {

    Node head, tail;
    int size;

    MyList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    void ftraverseFW(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            f.writeBytes(p.info.ID + "-" + p.info.name + "-" + p.info.price + "     "); 
            p = p.next;
        }

        f.writeBytes("\r\n");
    }

    void ftraverseBW(RandomAccessFile f) throws Exception {
        Node p = tail;
        while (p != null) {
            f.writeBytes(p.info.ID + "-" + p.info.name + "-" + p.info.price + "     "); 
            p = p.pre;
        }

        f.writeBytes("\r\n");
    }

    void loadData(int k) {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        String[] b = Lib.readLineToStrArray("data.txt", k + 1);
        String[] c = Lib.readLineToStrArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(a[i]);
            int y = Integer.parseInt(c[i]);
            addLast(x, b[i], y);
        }
    }
    
    void addLast(Phone x){
        Node p=new Node(x, null, null);
        if(isEmpty()){
            head=tail=p;
        } else{
            tail.next=p;
            p.pre=tail;
            tail=p;
        } size++;
    }
    
    void addLast(int id, String name, int price) {
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        if(price<0){
            return;
        } else{
            addLast(new Phone(id, name, price));
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
    }
    
    void insertNode(Node w, Node v){
        if(isEmpty()){
           head=w;
           tail=v;
        } else{
            v.next=head.next;
            head.next.pre=v;
            head.next=w;
            w.pre=head;
            w.next=v;
            v.pre=w;
        }   
    }
    
    void remove(Node q){
        if(q==null){
            return;
        }
        if(q==head){
            removeFirst();
            return;
        }
     
        Node fNode=head;
        while (fNode!=null && fNode.next != q){
            fNode=fNode.next;
        }
        if(fNode==null){ 
            return;
        }
        fNode.next=q.next;
        if (fNode.next==null){
            tail=null;
        }
    }

    void removeFirst(){
        if(isEmpty()){
            return;
        }
        head=head.next;
        if(head==null){
            tail=null;
        }
    }


    void f1() throws Exception {
        clear();
        loadData(0);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverseFW(f);
        ftraverseBW(f);
        f.close();
    }


    void f2() throws Exception {
        clear();
        loadData(0);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverseFW(f);
        ftraverseBW(f);
        Phone v = new Phone(7, "V", 8);
        Phone w = new Phone(9, "W", 10);
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        Node headNode=new Node(v, null, null);
        Node tailNode=new Node(w, null, null);
        insertNode(headNode, tailNode);
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverseFW(f);
        ftraverseBW(f);
        f.close();
    }

    void f3() throws Exception {
        clear();
        loadData(0);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverseFW(f);
        ftraverseBW(f);
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        int maxPrice=Integer.MIN_VALUE;
        Node p=head;
        Node toDelete=null;

        while(p!=null){
            if(p.info.price>=maxPrice){
                maxPrice=p.info.price;
                toDelete=p;
            }
            p=p.next;
        }
        remove(toDelete);
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverseFW(f);
        ftraverseBW(f);
        f.close();
    }

    void f4() throws Exception {
        clear();
        loadData(0);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverseFW(f);
        ftraverseBW(f);
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        int cnt=0;
        Node p=head;
        while(p!=null){
            if(p.info.name.equalsIgnoreCase("S")) cnt++;
            p=p.next;
        }
        System.out.println(cnt);
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        f.close();
    }


    void f5() throws Exception {
        clear();
        loadData(0);
        String fname = "f5.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverseFW(f);
        ftraverseBW(f);
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        removeFirst();
        if(head!=null && tail!=null && head!=tail){
            Phone temp=head.info;
            head.info=tail.info;
            tail.info=temp;
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverseFW(f);
        ftraverseBW(f);
        f.close();
    }

}
