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
    void insert(String xMaker, int xType, int xRadius) {
        //You should insert here statements to complete this function
        if (xMaker.charAt(0) == 'B') {
            return;
        }else {
            insert2(new Ball(xMaker, xType, xRadius));
        }
        
    }
    void insert2(Ball x) {
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
            if (x.type<(child.info.type)) {
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
        loadData(1);
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
    void postOrder2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder2(p.left, f);
        postOrder2(p.right, f);
        if (p.info.radius < 5) {
            fvisit(p, f);
        }
    }
    
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        postOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        postOrder2(root, f);
        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    Node nodeF3 = null;
    void inOrder(Node p) {
        // condition to stop
        if (p == null) {
            return;
        }
        inOrder(p.left);
        if (p.left != null && p.right != null && nodeF3 == null) {
            nodeF3 = p;
            deleByCopy(nodeF3.info);
        }
        
        inOrder(p.right);
    }
    
    void deleByCopy(Ball type) {
        if (isEmpty()) {
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.type == type.type) {
                break;
            }
            f = p;
            if (type.type < p.info.type) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return;
        }
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

        if (p.left != null && p.right == null) {
            if (f == null) {
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
        if (p.left == null && p.right != null) {
            if (f == null) {
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
        if (p.left != null && p.right != null) {
            Node q = p.left;
            Node frp, rp;
            frp = null;
            rp = q;
            while (rp.right != null) {
                frp = rp;
                rp = rp.right;
            }
            p.info = rp.info;
            if (frp == null) {
                p.left = q.left;
            } else {
                frp.right = rp.left;
            }
        }

    }
    
    void f3() throws Exception {
        clear();
        loadData(9);
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
        inOrder(root);
        //------------------------------------------------------------------------------------
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    Node nodeF4 = null;
    void inOrderF4(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrderF4(p.left, f);
        if (p.left != null && p.right != null && nodeF4 == null) {
            nodeF4 = p;
        }
        
        inOrderF4(p.right, f);
    }
    
    private Node findFather(Node x) {
        Queue q = new Queue();
        q.enqueue(root);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            if (r.left == x || r.right == x) {
                return r;
            }
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
        return null;
    }
    Node rotateLeft(Node p) {
        if (p == null || p.right == null) {
            return (p);
        }
        Node q = p.right;
        p.right = q.left;
        q.left = p;
        return (q);
    }
    
    void f4() throws Exception {
        clear();
        loadData(13);;
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
        inOrderF4(root, f);
        Node father = findFather(nodeF4);
            boolean isLeft = (father.left == nodeF4);
            nodeF4 = rotateLeft(nodeF4);
            if (isLeft) {
                father.left = nodeF4;
            } else {
                father.right = nodeF4;
            }
        
        //------------------------------------------------------------------------------------
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

}
