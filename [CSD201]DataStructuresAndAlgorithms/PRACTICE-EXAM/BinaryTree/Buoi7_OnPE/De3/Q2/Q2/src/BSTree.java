/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

public class BSTree {

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

    void visit(Node p) {
        System.out.print("p.info: ");
        if (p != null) {
            System.out.println(p.info + " ");
        }
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
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
        Queue q = new Queue();
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

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    

    void insert(String xCode, int xType, int xPrice) {
        //You should insert here statements to complete this function
        if (xCode.startsWith("B")) {
            return;
        }else {
            insert2(new Corn(xCode, xType, xPrice));
        }
    }
    
    void insert2(Corn x) {
        if (isEmpty()) {
            root = new Node(x);
            return;
        }
        Node father, child; // father la cha cua p
        father = null;
        child = root;

        while (child != null) {
            if (child.info.type == x.type) {
                System.out.println("The key " + x + "already exists, no insertion");
                return;
            }
            father = child;
            if (x.type < (child.info.type)) {
                child = child.left;
            } else {
                child = child.right;
            }
        }

        if (x.type < (father.info.type)) {
            father.left = new Node(x);
        } else {
            father.right = new Node(x);
        }

    }

//Do not edit this function. Your task is to complete insert function above only.
    void f1() throws Exception {
        clear();
        loadData(2);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void inOrder2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder2(p.left, f);
        //logic
        if (p.info.price < 5) {
            fvisit(p, f);
        }
        //logic
        inOrder2(p.right, f);
    }
    
    void f2() throws Exception {
        clear();
        loadData(6);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        inOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        inOrder2(root, f);
        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void preOrder(Node p) {
        // use recursion , condition to stop
        if (p == null) {
            return;
        }
        //logic
        if (p.info.type < 10) {
            deleByCopy(p.info);
            return;
        }
        
        //logic
        preOrder(p.left);
        preOrder(p.right);

    }
    
    void deleByCopy(Corn x) {
        if (isEmpty()) {
            return;
        }

        Node father, child; // father is father of child
        father = null;
        child = root;
        while (child != null) {
            if (child.info.type == x.type) {
                break;
            }
            father = child;
            if (x.type < child.info.type) {
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
    
    void f3() throws Exception {
        clear();
        loadData(10);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        preOrder(root);
        //------------------------------------------------------------------------------------
        preOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    
    
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
        loadData(14);;
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        //copy 
        Queue queue = new Queue();
        queue.enqueue(root);
        Node r = null;
        while (!queue.isEmpty()) {
            r = queue.dequeue();
            //logic....
            if (r.info.type == 5) {
                break;
            }

            //logic
            if (r.left != null) {
                queue.enqueue(r.left);
            }
            if (r.right != null) {
                queue.enqueue(r.right);
            }
        }
        int k = countNode(r);
        r.info.price = k;
        
        //------------------------------------------------------------------------------------
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

}
