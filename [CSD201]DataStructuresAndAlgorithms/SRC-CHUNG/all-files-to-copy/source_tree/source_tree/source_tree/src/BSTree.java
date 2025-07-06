
import java.util.ArrayList;

public class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    void clear() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    // (1)
    Node search(Node p, int x) {
        // if tree are null
        if (p == null) {
            return null;
        }
        // condition to stop
        if (p.info == x) {
            return p;
        }
        if (x < p.info) {
            return search(p.left, x);
        } else {
            return search(p.right, x);
        }
    }

    // (2) insert use recursion
    Node insert(Node p, int x) {
        // if tree are null
        if (p == null) {
            p = new Node(x);
            return p;
        }
        // insert a node exist
        if (p.info == x) {
            System.out.println("The key " + x + "already exist, no insertion");
            return p;
        }
        if (x < p.info) {
            p.left = insert(p.left, x);

        } else {
            p.right = insert(p.right, x);
        }
        return (p);
    }

    // (3)
    void insert(int x) {
        root = insert(root, x);

    }

    // (4) insert not use recursion
    void insert2(int x) {
        if (isEmpty()) {
            root = new Node(x);
            return;
        }
        Node father, child; // father la cha cua p
        father = null;
        child = root;

        while (child != null) {
            if (child.info == x) {
                System.out.println("The key " + x + "already exists, no insertion");
                return;
            }
            father = child;
            if (x < child.info) {
                child = child.left;
            } else {
                child = child.right;
            }
        }

        if (x < father.info) {
            father.left = new Node(x);
        } else {
            father.right = new Node(x);
        }

    }

    // (5)
    void insertMany(int[] a) {
        for (int i = 0; i < a.length; i++) {
            insert(a[i]);
        }

    }

    // (6)
    void display(Node p) {
        // if p!= null
        if (p != null) {
            System.out.print(p.info + " ");
        }

    }

    // (7) traverse ROOT - LEFT - RIGHT
    void preOrder(Node p) {
        // use recursion , condition to stop
        if (p == null) {
            return;
        }
        //logic
        
        
        //logic
        preOrder(p.left);
        preOrder(p.right);

    }

    // (8) left root right
    void inOrder(Node p) {
        // condition to stop
        if (p == null) {
            return;
        }
        inOrder(p.left);
        display(p);
        inOrder(p.right);
    }

    // (9) left - right - root
    void postOrder(Node p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        display(p);
    }

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

    Node rotateRight(Node p) {
        if (p == null || p.left == null) {
            return (p);
        }
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        return (q);
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

    private Node findFather(Node x) {
        MyQueue q = new MyQueue();
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

    // (10) traverse from top -> down, left -> right
    void breadth(Node p) {
        if (p == null) {
            return;
        }
        //copy 
        MyQueue queue = new MyQueue();
        queue.enqueue(p);
        Node r;
        while (!queue.isEmpty()) {
            r = queue.dequeue();
            //logic....
            display(r);

            //logic
            if (r.left != null) {
                queue.enqueue(r.left);
            }
            if (r.right != null) {
                queue.enqueue(r.right);
            }
        }
    }

    // (11)
    void deleByCopy(int x) {
        if (isEmpty()) {
            return;
        }

        Node father, child; // father is father of child
        father = null;
        child = root;
        while (child != null) {
            if (child.info == x) {
                break;
            }
            father = child;
            if (x < child.info) {
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

    void deleByMerging(int x) {
        if (isEmpty()) {
            return;
        }

        Node father, child; // father is father of child
        father = null;
        child = root;
        while (child != null) {
            if (child.info == x) {
                break;
            }
            father = child;
            if (x < child.info) {
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

            rp.right = child.right;

            if (father == null) {
                root = child.left;
            } else if (child == father.left) {
                father.left = child.left;
            } else {
                father.right = child.left;
            }

        }
    }

    void breathRotate() {
        Node x = root;
        Node fatherX = null;
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            //logic
            if (r.left != null && r.info < 15) {
                x = r;
                break;
            }
            //logic
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

        x = rotateRight(x);

        if (isLeft) {
            fatherX.left = x;
        } else {
            fatherX.right = x;
        }
    }

    int countPreOrder3 = 0;
    Node fourthNode = null;

    void preOrderRotate(Node p) {
        // use recursion , condition to stop
        if (p == null) {
            return;
        }

        countPreOrder3++;
        if (countPreOrder3 == 4 && p.right != null) {
            fourthNode = p;
            Node father = findFather(fourthNode);
            boolean isLeft = (father.left == fourthNode);
            fourthNode = rotateLeft(fourthNode);
            if (isLeft) {
                father.left = fourthNode;
            } else {
                father.right = fourthNode;
            }
            return;
        }

        preOrderRotate(p.left);
        preOrderRotate(p.right);

    }
}
