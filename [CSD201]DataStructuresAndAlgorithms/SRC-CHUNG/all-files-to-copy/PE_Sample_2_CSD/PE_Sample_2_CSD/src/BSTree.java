/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void fvisitBal(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes("(" + p.info.name + "," + p.info.age + "," + p.bal + ") ");
        }
    }

    void fvisitLevel(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes("(" + p.info.name + "," + p.info.age + "," + p.level + ") ");
        }
    }

    void preOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void breadthBal(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisitBal(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void breadthLevel(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisitLevel(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void loadData(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("person.txt", k);
        int[] b = Lib.readLineToIntArray("person.txt", k + 1);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
//===========================================================================
    void insert2(Person x) {
        if (isEmpty()) {
            root = new Node(x);
            return;
        }
        Node father, child; // father la cha cua p
        father = null;
        child = root;

        while (child != null) {
            if (child.info.name.equals(x.name)) {
                System.out.println("The key " + x + "already exists, no insertion");
                return;
            }
            father = child;
            if (x.name.compareTo(child.info.name) < 0) {
                child = child.left;
            } else {
                child = child.right;
            }
        }

        if (x.name.compareTo(father.info.name) < 0) {
            father.left = new Node(x);
        } else {
            father.right = new Node(x);
        }

    }

    void insert(String xName, int xAge) {
        //if xname == B then do nothing
        //else insert
        if (xName.charAt(0) == 'B') {
            return;
        } else {
            insert2(new Person(xName, xAge));
        }
    }

    void f1() throws Exception {/* You do not need to edit this function. Your task is to complete insert  function
        above only.
         */
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        preOrder(root, f123);
        f123.writeBytes("\r\n");
        inOrder(root, f123);
        f123.writeBytes("\r\n");
        f123.close();
    }

//===============================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        preOrder(root, f123);
        f123.writeBytes("\r\n");
        BSTree h = new BSTree();
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        MyQueue queue = new MyQueue();
        queue.enqueue(root);
        Node r;
        while (!queue.isEmpty()) {
            r = queue.dequeue();
            //xu li logic
            if (r.info.age > 4) {
                h.insert2(r.info);
            }

            //xu li logic
            if (r.left != null) {
                queue.enqueue(r.left);
            }
            if (r.right != null) {
                queue.enqueue(r.right);
            }
        }

        //-------------------------------------------------------------------------------------
        h.preOrder(h.root, f123);
        f123.writeBytes("\r\n");
        f123.close();
    }

//===============================================================
    int height(Node pNode) {
        if (pNode == null) {
            return 0;
        }
        int leftHeight, rightHeight, height;
        leftHeight = height(pNode.left);
        rightHeight = height(pNode.right);

        height = leftHeight > rightHeight ? leftHeight : rightHeight;
        return height + 1;
    }

    void f3() throws Exception {
        clear();
        loadData(5);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        int k = 0;
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        k = height(root);

        //-------------------------------------------------------------------------------------
        f123.writeBytes(" k = " + k + "\r\n");
        f123.close();
    }

//===============================================================
    int countNode(Node pNode) {
        if (pNode == null) {
            return 0;
        }
        int k, h, rNode;
        k = countNode(pNode.left);
        h = countNode(pNode.right);
        rNode = k + h + 1;
        return rNode;
    }

    void f4() throws Exception {
        clear();
        loadData(5);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        int k = 0;
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        k = countNode(root);

        //-------------------------------------------------------------------------------------
        f123.writeBytes(" k = " + k + "\r\n");
        f123.close();
    }

//===============================================================
    void deleByCopy(String name) {
        if (isEmpty()) {
            return;
        }

        Node father, child; // father is father of child
        father = null;
        child = root;
        while (child != null) {
            if (child.info.name.compareTo(name) == 0) {
                break;
            }
            father = child;
            if (name.compareTo(child.info.name) < 0) {
                child = child.left;
            } else {
                child = child.right;
            }
        }
        if (child == null) {
            return; // not found
        }

        // in here, child = nut need to remove
        // father is father of child
        // Case 1: child is leaf
        if (child.left == null && child.right == null) {
            if (father == null) { // child is root
                root = null;
            } else {
                if (child == father.left) {
                    father.left = null;
                } else {
                    father.right = null;
                }
            }
            return;
        }
        // Case 2: child have 1 child at left
        if (child.left != null && child.right == null) {
            if (father == null) { // child == root
                root = child.left;
            } else {
                if (child == father.left) {
                    father.left = child.left;
                } else {
                    father.right = child.right;
                }
            }
        }

        //// Case 3: child have 1 child at right
        if (child.left == null && child.right != null) {
            if (father == null) { // child la root
                root = child.right;
            } else {
                if (child == father.left) {
                    father.left = child.right;
                } else {
                    father.right = child.right;
                }

            }
            return;
        }

        // 1:33:00
        // Case 4: child have 2 children
        if (child.left != null && child.right != null) {
            // find most right node of left side of child node
            Node qNode = child.left;
            Node frp, rp; // frp la cha cua rp
            frp = null;
            rp = qNode;
            while (rp.right != null) {
                frp = rp;
                rp = rp.right;
            }
            // rp la node phai nhat

            // copy gia tri cua rp vao node can xoa(p)
            child.info = rp.info;

            // xoa rp di
            if (frp == null) {
                child.left = qNode.left;
            } else {
                frp.right = rp.left;
            }

        }

    }

    void f5() throws Exception {
        clear();
        loadData(5);
        String fname = "f5.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        preOrder(root, f123);
        f123.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        deleByCopy(root.info.name);
        //-------------------------------------------------------------------------------------
        preOrder(root, f123);
        f123.writeBytes("\r\n");
        f123.close();
    }

//===============================================================
    Node rotateRight(Node p) {
        if (p == null || p.left == null) {
            return (p);
        }
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        return (q);
    }

    void f6() throws Exception {
        clear();
        loadData(5);
        String fname = "f6.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        breadth(root, f123);
        f123.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //xoay node root ve ben tay phai
        root = rotateRight(root);

        //-------------------------------------------------------------------------------------
        breadth(root, f123);
        f123.writeBytes("\r\n");
        f123.close();
    }

//===============================================================
    void f7() throws Exception {
        clear();
        loadData(5);
        String fname = "f7.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        breadth(root, f123);
        f123.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //copy 
        boolean isAVL = true;
        MyQueue queue = new MyQueue();
        queue.enqueue(root);
        Node r;
        while (!queue.isEmpty()) {
            r = queue.dequeue();
            //logic....
            r.bal = height(r.right) - height(r.left);
            //kiem tra chi so can bang cua node r co nam trong khoang -1 --> 1
            if (r.bal > 1 || r.bal < -1) {
                isAVL = false;
            }

            //logic
            if (r.left != null) {
                queue.enqueue(r.left);
            }
            if (r.right != null) {
                queue.enqueue(r.right);
            }
        }
        //ghi tat ca cac node vao file
        breadthBal(root, f123);
        //ghi vao file xem la cay can bang hay ko
        if (isAVL == false) {
            f123.writeBytes("\nThe tree is not an AVL tree");
        } else {
            f123.writeBytes("\nThe tree is an AVL tree");

        }

        //-------------------------------------------------------------------------------------
        f123.writeBytes("\r\n");
        f123.close();
    }

//===============================================================
    void f8() throws Exception {
        clear();
        loadData(5);
        String fname = "f8.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        breadth(root, f123);
        f123.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //copy 
        //copy 
        MyQueue queue = new MyQueue();
        root.level = 1;
        queue.enqueue(root);
        Node r;
        while (!queue.isEmpty()) {
            r = queue.dequeue();
            //logic....
            if (r.left != null) {
                r.left.level = r.level + 1;
            }
            if (r.right != null) {
                r.right.level = r.level + 1;
            }
            
            //logic
            if (r.left != null) {
                queue.enqueue(r.left);
            }
            if (r.right != null) {
                queue.enqueue(r.right);
            }
        }
        
        breadthLevel(root, f123);
        
        //-------------------------------------------------------------------------------------
        f123.writeBytes("\r\n");
        f123.close();
    }

//===============================================================
    void inOrder(ArrayList<Person> t, Node p) {
        if (p == null) {
            return;
        }
        inOrder(t, p.left);
        t.add(p.info);
        inOrder(t, p.right);
    }

    void balance(ArrayList<Person> t, int i, int j) {
        if (i > j) {
            return;
        }
        int k = (i + j) / 2;
        insert(t.get(k).name, t.get(k).age);
        balance(t, i, k - 1);
        balance(t, k + 1, j);
    }

    void balance() {
        ArrayList<Person> t = new ArrayList<>();
        inOrder(t, root);
        int n = t.size();
        clear();
        balance(t, 0, n - 1);

    }
    
    void f9() throws Exception {
        clear();
        loadData(5);
        String fname = "f9.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f123 = new RandomAccessFile(fname, "rw");
        breadth(root, f123);
        f123.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        balance();
        breadth(root, f123);
        
        //-------------------------------------------------------------------------------------
        f123.writeBytes("\r\n");
        f123.close();
    }

}
