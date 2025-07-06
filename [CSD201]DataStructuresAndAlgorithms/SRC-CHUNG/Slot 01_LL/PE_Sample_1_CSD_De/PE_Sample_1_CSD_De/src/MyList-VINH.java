/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

class MyList {

    Node head, tail;

    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void loadDataToLast(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("person.txt", k);
        int[] b = Lib.readLineToIntArray("person.txt", k + 1);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i]);
        }
    }

    void loadDataToFirst(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("person.txt", k);
        int[] b = Lib.readLineToIntArray("person.txt", k + 1);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addFirst(a[i], b[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void addLast(Person x) {
        Node qNode = new Node(x);
        if (isEmpty()) {
            head = tail = qNode;
            return;
        }
        tail.next = qNode;
        tail = qNode;

    }

    void addFirst(Person x) {
        Node currentNode = new Node(x);

//    	currentNode.next = head;
//    	head = currentNode;
        head = new Node(x, head);
        if (tail == null) {
            tail = currentNode;
        }

    }

    void addLast(String xName, int xAge) {//You should write here appropriate statements to complete this function.
        if (xName.charAt(0) == 'B') {
            return;
        } else {
            addLast(new Person(xName, xAge));
        }
    }

    void addFirst(String xName, int xAge) {//You should write here appropriate statements to complete this function.
        if (xName.charAt(0) == 'B') {
            return;
        } else {
            addFirst(new Person(xName, xAge));
        }
    }

//=================================================================
    void f1() throws Exception {/* You do not need to edit this function. Your task is to complete the addLast  function
        above only.
         */
        clear();
        loadDataToLast(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        ftraverse(f123);
        f123.close();
    }

//=================================================================
    void f2() throws Exception {/* You do not need to edit this function. Your task is to complete the addFirst  function
        above only.
         */
        clear();
        loadDataToFirst(1);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        ftraverse(f123);
        f123.close();
    }

//=================================================================
    void f3() throws Exception {
        clear();
        loadDataToLast(4);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        ftraverse(f123);

        MyList h = new MyList();
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.info.age > 4) {
                h.addLast(new Person(currentNode.info.name, currentNode.info.age));
            }
            currentNode = currentNode.next;
        }

        //-------------------------------------------------------------------------------------
        h.ftraverse(f123);
        f123.close();
    }

//=================================================================
    void remove(Node q) {
        if (q == null) {
            return;
        }
        if (q == head) { // xoa not dau tien
            removeFirst();
            return;

        }
        // tim node F truoc node q
        Node fNode = head;
        while (fNode != null && fNode.next != q) {
            fNode = fNode.next;
        }
        if (fNode == null) { // q khong co trong list
            return;

        }
        // xoa q khoi list
        fNode.next = q.next;
        if (fNode.next == null) {
            tail = null;
        }

    }

    void removeFirst() {
        if (isEmpty()) {
            return;
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }

    }

    void f4() throws Exception {
        clear();
        loadDataToLast(4);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        ftraverse(f123);
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.info.age < 6) {
                remove(currentNode);
                return;
            }
            currentNode = currentNode.next;
        }

        //-------------------------------------------------------------------------------------
        ftraverse(f123);
        f123.close();
    }

//=================================================================
    void sortByName() {
        Node piNode, pjNode;
        Person temp;
        piNode = head;
        while (piNode != null) {
            pjNode = piNode.next;
            while (pjNode != null) {
                if (piNode.info.name.compareTo(pjNode.info.name) > 0) {
                    temp = piNode.info;
                    piNode.info = pjNode.info;
                    pjNode.info = temp;
                }
                pjNode = pjNode.next;
            }
            piNode = piNode.next;
        }

    }
    
    void f5() throws Exception // sort by name
    {
        clear();
        loadDataToLast(4);
        String fname = "f5.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        ftraverse(f123);
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        
        sortByName();
        
        //-------------------------------------------------------------------------------------
        ftraverse(f123);
        f123.close();
    }

//=================================================================
    
    void reverse() {
        MyList tList = new MyList();
        Node pNode = head;
        while (pNode != null) {
            tList.addFirst(pNode.info);
            pNode = pNode.next;
        }
        head = tList.head;
        tail = tList.tail;
    }
    void f6() throws Exception // sort by name
    {
        clear();
        loadDataToLast(4);
        String fname = "f6.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        ftraverse(f123);
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        reverse();
        //--------------------------------------------------------
        ftraverse(f123);
        f123.close();
    }

//=================================================================
    void f7() throws Exception // sort by name
    {
        clear();
        loadDataToLast(4);
        String fname = "f7.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        ftraverse(f123);
        MyList h = new MyList();
        h.loadDataToLast(7);
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        Node currentNode = h.head;
        while (currentNode != null) {            
            addLast(new Person(currentNode.info.name, currentNode.info.age));
            
            currentNode = currentNode.next;
        }
        
        //-------------------------------------------------------------------------------------
        ftraverse(f123);
        f123.close();
    }

//=================================================================
    Node pos(int k) {
        int count = 0;
        Node pNode = head;
        while (pNode != null) {
            if (count == k) {
                return pNode;

            }
            count++;
            pNode = pNode.next;
        }

        return (null);
    }
    
    void f8() throws Exception // sort by name
    {
        clear();
        loadDataToLast(4);
        String fname = "f8.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        ftraverse(f123);
        int k = 3;
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        remove(pos(3));
        //-------------------------------------------------------------------------------------
        ftraverse(f123);
        f123.close();
    }

//=================================================================
    
    
    void f9() throws Exception // sort by name
    {
        clear();
        loadDataToLast(4);
        String fname = "f9.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        ftraverse(f123);
        String xName = "C6";
        String yName = "CX";
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        
        Node currenNode = head;
        while (currenNode != null) {            
            if (currenNode.info.name.equals(xName)) {
                currenNode.info.name = yName;
                break;
            }
            
            currenNode = currenNode.next;
        }
        
        
        //-------------------------------------------------------------------------------------
        ftraverse(f123);
        f123.close();
    }

}
