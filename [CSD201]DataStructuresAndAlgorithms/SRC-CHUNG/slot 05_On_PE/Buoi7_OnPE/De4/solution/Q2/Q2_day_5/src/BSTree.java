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

    void preOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void preOrder2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        if (p.info.price >= 3 && p.info.price <= 5) {
            fvisit(p, f);
        }
        preOrder2(p.left, f);
        preOrder2(p.right, f);
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

    void loadData(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void insert2(Car x) {
        if (isEmpty()) {
            root = new Node(x);
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.price == x.price) {
                System.out.println("The key already exists, no insertion");
                return;
            }
            f = p;
            if (x.price < p.info.price) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x.price < f.info.price) {
            f.left = new Node(x);
        } else {
            f.right = new Node(x);
        }
    }

    void insert(String xOwner, int xPrice) {//You should insert here statements to complete this function
        if (xOwner.charAt(0) == 'B' || xPrice > 100) {
            return;
        }
        insert2(new Car(xOwner, xPrice));
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
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f2() throws Exception {
        clear();
        loadData(4);
        String fname = "f2.txt";
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
        preOrder2(root, f);
        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

// f.writeBytes(" k = " + k + "\r\n");
//=============================================================
    void deleByCopy(int price) {
        if (isEmpty()) {
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.price == price) {
                break;
            }
            f = p;
            if (price < p.info.price) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return; // not found
        }      // p is a leaf-node
        if (p.left == null && p.right == null) {
            if (f == null) { // p is the root
                root = null;
            } else {
                if (p == f.left) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
            return;
        }

        // p has left son
        if (p.left != null && p.right == null) {
            if (f == null) { // p is the root
                root = p.left;
            } else {
                if (p == f.left) {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
            return;
        }
        // p has right son only
        if (p.left == null && p.right != null) {
            if (f == null) { // p is the root
                root = p.right;
            } else {
                if (p == f.left) {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
            return;
        }
        // p has both 2 sons
        if (p.left != null && p.right != null) {
            Node q = p.left;
            // Start finding the right-most node on the left subtree
            Node frp, rp;
            frp = null;
            rp = q;
            while (rp.right != null) {
                frp = rp;
                rp = rp.right;
            }
            // rp now is the right-most node
            p.info = rp.info;
            // Delete rp;
            if (frp == null) {
                p.left = q.left;
            } else {
                frp.right = rp.left;
            }
        }

    }

    void f3() throws Exception {
        clear();
        loadData(7);
        String fname = "f3.txt";
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

        Queue q = new Queue();
        Node r;
        q.enqueue(root);
        while (!q.isEmpty()) {
            r = q.dequeue();

            if (r.left != null && r.right != null && r.info.price < 7) {
                deleByCopy(r.info.price);
                break;
            }

            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }

        //------------------------------------------------------------------------------------
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }
//=============================================================

    Node rotateToRight(Node p) {
        if (p == null || p.left == null) {
            return (p);
        }
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        return (q);
    }

    void f4() throws Exception {
        clear();
        loadData(10);
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
        Node x = root;
        Node fatherX = null;
        Queue q = new Queue();
        q.enqueue(root);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            if (r.left != null && r.info.price < 7) {
                x = r;
                break;
            }
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
            fatherX = r;
        }

        boolean isLeft = false;
        if (fatherX.left == x) {
            isLeft = true;
        }   
        
        x = rotateToRight(x);
        
        if(isLeft){
            fatherX.left = x;
        }else{
            fatherX.right = x;
        }

        //------------------------------------------------------------------------------------
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

}
