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
    void addLast(String xName, int xAge) {//You should write here appropriate statements to complete this function.
        //check if the first letter of xName is 'B' (i.e. xName.charAt(0) == 'B') 
        if (xName.charAt(0) == 'B') {
            //then do nothing
            return;
        }
        //otherwise add new person with name=xName, age=xAge to the end of the list. 
        else {
            Person person = new Person(xName, xAge);
            addLast(person);
        }
    }
    
    void addLast(Person x) {
        Node qNode = new Node(x);
        if (isEmpty()) {
            head = tail = qNode;
            return;
        }
        tail.next = qNode;
        tail = qNode;

    }

    void addFirst(String xName, int xAge) {//You should write here appropriate statements to complete this function.
        if (xName.startsWith("B")) {
            return;
        }else {
            addFirst(new Person(xName, xAge));
        }
    }
    
    void addFirst(Person x) {
        Node currentNode = new Node(x);
        head = new Node(x, head);
        if (tail == null) {
            tail = currentNode;
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
        //lap qua tat ca phan tu trong MyList hien tai
        for (Node currentNode = head; currentNode != null; currentNode = currentNode.next) {
            //logic
            //kiem tra xem phan tu nao co age > 4 => addLast h
            if (currentNode.info.age > 4) {
                h.addLast(currentNode.info);
            }
        }
        
        
        //-------------------------------------------------------------------------------------
        h.ftraverse(f123);
        f123.close();
    }

//=================================================================
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
        //tim ra node co age < 6 (node dau tien)
        Node nodeRemove = searchByAge(6);
        
        //xoa
        remove(nodeRemove);
        
        //-------------------------------------------------------------------------------------
        ftraverse(f123);
        f123.close();
    }
    
    Node searchByAge(int xAge) {
        Node pNode = head;
        while (pNode != null) {
            if (pNode.info.age < xAge) {
                return pNode;
            }
            pNode = pNode.next;
        }

        return null;
    }
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

//=================================================================
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
    
    void sortByName() {
        Node piNode, pjNode;
        Person temp;
        piNode = head;
        while (piNode != null) {
            pjNode = piNode.next;
            while (pjNode != null) {
                if (piNode.info.name.compareTo(pjNode.info.name) < 0) {
                    temp = piNode.info;
                    piNode.info = pjNode.info;
                    pjNode.info = temp;
                }
                pjNode = pjNode.next;
            }
            piNode = piNode.next;
        }

    }

//=================================================================
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
        reverse(1,4);
        //--------------------------------------------------------
        ftraverse(f123);
        f123.close();
    }
    void reverse(int k, int h) // reverse from k to h
    {
        if (k >= h) {
            return;
        }
        if (k < 0) {
            return;
        }
        int n = size();
        if (h > n - 1) {
            return;
        }
        Person[] a = toArray();
        int i, j;
        Person x;
        i = k;
        j = h;
        while (i < j) {
            x = a[i];
            a[i] = a[j];
            a[j] = x;
            i++;
            j--;
        }
        clear();
        for (i = 0; i < n; i++) {
            addLast(a[i]);
        }
    }
    Person[] toArray() {
        int n, i;
        n = size();
        Person[] persons = new Person[n];
        Node pNode = head;
        i = 0;
        while (pNode != null) {
            persons[i] = new Person(pNode.info.name, pNode.info.age);
            pNode = pNode.next;
            i++;
        }

        return (persons);
    }
    int size() {
        int count = 0;
        Node pNode = head;
        while (pNode != null) {
            count++;
            pNode = pNode.next;
        }
        return count;
    }
    
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

        //-------------------------------------------------------------------------------------
        ftraverse(f123);
        f123.close();
    }

//=================================================================
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
        //delete the position i = 3 (The first position is 0).
        //tim ra node i = 3
//        Node node = pos(3);
        //delete node
        remove(pos(3));
        //-------------------------------------------------------------------------------------
        ftraverse(f123);
        f123.close();
    }
    
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
        
        //tim ra node co name = C6
        Node node = searchByName("C6");
        //change name
        node.info.name = "CX";
        //-------------------------------------------------------------------------------------
        ftraverse(f123);
        f123.close();
    }
    
    Node searchByName(String xName) {
        Node pNode = head;
        while (pNode != null) {
            if (pNode.info.name.equals(xName)) {
                return pNode;
            }
            pNode = pNode.next;
        }
        return null;
    }

}
