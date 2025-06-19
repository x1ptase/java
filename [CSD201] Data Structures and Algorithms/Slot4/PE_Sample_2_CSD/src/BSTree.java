/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

class BSTree
  {Node root;
   BSTree() {root=null;}
   boolean isEmpty()
      {return(root==null);
      }
   void clear()
      {root=null;
      }
   void fvisit(Node p, RandomAccessFile f) throws Exception
     {if(p != null) f.writeBytes(p.info + " ");
     }

  void fvisitBal(Node p, RandomAccessFile f) throws Exception
    {if(p != null) 
      f.writeBytes("("+p.info.name+","+p.info.age+","+p.bal+") ");
    }

  void fvisitLevel(Node p, RandomAccessFile f) throws Exception
    {if(p != null) 
      f.writeBytes("("+p.info.name+","+p.info.age+","+p.level+") ");
    }

   void preOrder(Node p, RandomAccessFile f) throws Exception
     {if(p==null) return;
      fvisit(p,f);
      preOrder(p.left,f);
      preOrder(p.right,f);
     }
   void inOrder(Node p, RandomAccessFile f) throws Exception
     {if(p==null) return;
      inOrder(p.left,f);
      fvisit(p,f);
      inOrder(p.right,f);
     }
   void postOrder(Node p, RandomAccessFile f) throws Exception
     {if(p==null) return;
      postOrder(p.left,f);
      postOrder(p.right,f);
      fvisit(p,f);
     }
  void breadth(Node p, RandomAccessFile f) throws Exception
    {if(p==null) return;
     MyQueue q = new MyQueue();
     q.enqueue(p);Node r;
     while(!q.isEmpty())
       {r = q.dequeue();
        fvisit(r,f);
        if(r.left!=null) q.enqueue(r.left);
        if(r.right!=null) q.enqueue(r.right);
       }
    }

  void breadthBal(Node  p, RandomAccessFile f) throws Exception
   {if(p==null) return;
    MyQueue q = new MyQueue();
    q.enqueue(p); Node r;
    while(!q.isEmpty())
     {r = q.dequeue();
      fvisitBal(r,f);
      if(r.left != null) q.enqueue(r.left);
      if(r.right != null) q.enqueue(r.right);
     }
   }

  void breadthLevel(Node  p, RandomAccessFile f) throws Exception
   {if(p==null) return;
    MyQueue q = new MyQueue();
    q.enqueue(p); Node r;
    while(!q.isEmpty())
     {r = q.dequeue();
      fvisitLevel(r,f);
      if(r.left != null) q.enqueue(r.left);
      if(r.right != null) q.enqueue(r.right);
     }
   }

   void loadData(int k)  //do not edit this function
     {String [] a = Lib.readLineToStrArray("person.txt", k);
      int [] b = Lib.readLineToIntArray("person.txt", k+1);
      int n = a.length;
      for(int i=0;i<n;i++) insert(a[i],b[i]);
     }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
//===========================================================================
  void insert(String xName, int xAge)
    {
        if (xName.charAt(0) == 'B') return; // Do nothing if name starts with 'B'
        Node p = new Node(new Person(xName, xAge));
        if (isEmpty()) {
            root = p;
            return;
        }
        Node father = null, current = root;
        while (current != null) {
            if (current.info.name.equals(xName)) return; // No insertion if name exists
            father = current;
            if (xName.compareTo(current.info.name) < 0)
                current = current.left;
            else
                current = current.right;
        }
        if (xName.compareTo(father.info.name) < 0)
            father.left = p;
        else
          father.right = p;
    }

  void f1() throws Exception
    {/* You do not need to edit this function. Your task is to complete insert  function
        above only.
     */
     clear();
     loadData(1);
     String fname = "f1.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f123 = new RandomAccessFile(fname, "rw"); 
     preOrder(root,f123);
     f123.writeBytes("\r\n");
     inOrder(root,f123);
     f123.writeBytes("\r\n");
     f123.close();
    }  
  
//===============================================================
  void f2() throws Exception
    {clear();
     loadData(5);
     String fname = "f2.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f123 = new RandomAccessFile(fname, "rw"); 
     preOrder(root,f123);
     f123.writeBytes("\r\n");
     BSTree h = new BSTree();
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        // Insert nodes with age > 4 using breadth-first traversal
        MyQueue q = new MyQueue();
        if (root != null) q.enqueue(root);
        while (!q.isEmpty()) {
            Node r = q.dequeue();
            if (r.info.age > 4) h.insert(r.info.name, r.info.age);
            if (r.left != null) q.enqueue(r.left);
            if (r.right != null) q.enqueue(r.right);
        }
    //-------------------------------------------------------------------------------------
     h.preOrder(h.root,f123);
     f123.writeBytes("\r\n");
     f123.close();
    }  
    // Calculate height of the tree
    int height(Node p) {
        if (p == null) return 0;
        return 1 + Math.max(height(p.left), height(p.right));
    }
//===============================================================
  void f3() throws Exception
    {clear();
     loadData(5);
     String fname = "f3.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f123 = new RandomAccessFile(fname, "rw"); 
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
  
    // Count number of nodes in the tree
    int countNodes(Node p) {
        if (p == null) return 0;
        return 1 + countNodes(p.left) + countNodes(p.right);
    }

//===============================================================
  void f4() throws Exception
    {clear();
     loadData(5);
     String fname = "f4.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f123 = new RandomAccessFile(fname, "rw"); 
     int k = 0;
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        k = countNodes(root);

    //-------------------------------------------------------------------------------------
     f123.writeBytes(" k = " + k + "\r\n");
     f123.close();
    }  

     // Delete node by copying
    void deleByCopy(String xName) {
        if (isEmpty()) return;
        Node father = null, current = root;
        while (current != null) {
            if (current.info.name.equals(xName)) break;
            father = current;
            if (xName.compareTo(current.info.name) < 0)
                current = current.left;
            else
                current = current.right;
      }
        if (current == null) return; // Node not found

        // Case 1: Leaf node
        if (current.left == null && current.right == null) {
            if (father == null) root = null;
            else if (father.left == current) father.left = null;
            else father.right = null;
            return;
        }

        // Case 2: Node has only left child
        if (current.left != null && current.right == null) {
            if (father == null) root = current.left;
            else if (father.left == current) father.left = current.left;
            else father.right = current.left;
            return;
        }

        // Case 3: Node has only right child
        if (current.left == null && current.right != null) {
            if (father == null) root = current.right;
            else if (father.left == current) father.left = current.right;
            else father.right = current.right;
            return;
        }

        // Case 4: Node has two children
        Node rightMost = current.left, rightMostFather = null;
        while (rightMost.right != null) {
            rightMostFather = rightMost;
            rightMost = rightMost.right;
        }
        current.info = rightMost.info;
        if (rightMostFather == null) current.left = rightMost.left;
        else rightMostFather.right = rightMost.left;
    }
  
//===============================================================
  void f5() throws Exception
    {clear();
     loadData(5);
     String fname = "f5.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f123 = new RandomAccessFile(fname, "rw"); 
     preOrder(root,f123);
     f123.writeBytes("\r\n");
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
     deleByCopy(root.info.name);

    //-------------------------------------------------------------------------------------
     preOrder(root,f123);
     f123.writeBytes("\r\n");
     f123.close();
    }  

    // Rotate right
    Node rotateRight(Node p) {
      if (p == null || p.left == null) return p;
      Node q = p.left;
      p.left = q.right;
      q.right = p;
      return q;
  }
//===============================================================
  void f6() throws Exception
    {clear();
     loadData(5);
     String fname = "f6.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f123 = new RandomAccessFile(fname, "rw"); 
     breadth(root,f123);
     f123.writeBytes("\r\n");
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        if (root != null && root.left != null)
            root = rotateRight(root);

    //-------------------------------------------------------------------------------------
     breadth(root,f123);
     f123.writeBytes("\r\n");
     f123.close();
    }  
  
    // Calculate balance factor for all nodes
    void calculateBalance(Node p) {
        if (p == null) return;
        p.bal = height(p.right) - height(p.left);
        calculateBalance(p.left);
        calculateBalance(p.right);
    }

    // Check if tree is AVL
    boolean isAVL(Node p) {
        if (p == null) return true;
        int bal = Math.abs(height(p.right) - height(p.left));
        if (bal > 1) return false;
        return isAVL(p.left) && isAVL(p.right);
    }

//===============================================================
  void f7() throws Exception
    {clear();
     loadData(5);
     String fname = "f7.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f123 = new RandomAccessFile(fname, "rw"); 
     breadth(root,f123);
     f123.writeBytes("\r\n");
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        calculateBalance(root);
        breadthBal(root, f123);
        f123.writeBytes("\r\n");
        f123.writeBytes(isAVL(root) ? "The tree is an AVL tree" : "The tree is not an AVL tree");

    //-------------------------------------------------------------------------------------
     f123.writeBytes("\r\n");
     f123.close();
    }  
  
    // Calculate level for all nodes
    void calculateLevel(Node p, int level) {
        if (p == null) return;
        p.level = level;
        calculateLevel(p.left, level + 1);
        calculateLevel(p.right, level + 1);
    }

//===============================================================
  void f8() throws Exception
    {clear();
     loadData(5);
     String fname = "f8.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f123 = new RandomAccessFile(fname, "rw"); 
     breadth(root,f123);
     f123.writeBytes("\r\n");
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        calculateLevel(root, 1);
        breadthLevel(root, f123);

    //-------------------------------------------------------------------------------------
     f123.writeBytes("\r\n");
     f123.close();
    }  
  
    // Collect nodes in-order to array
    void inOrderToArray(Node p, ArrayList<Person> list) {
        if (p == null) return;
        inOrderToArray(p.left, list);
        list.add(p.info);
        inOrderToArray(p.right, list);
    }

    // Build balanced tree from sorted array
    Node buildBalancedTree(ArrayList<Person> list, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(list.get(mid));
        node.left = buildBalancedTree(list, start, mid - 1);
        node.right = buildBalancedTree(list, mid + 1, end);
        return node;
    }
  
//===============================================================
  void f9() throws Exception
    {clear();
     loadData(5);
     String fname = "f9.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f123 = new RandomAccessFile(fname, "rw"); 
     breadth(root,f123);
     f123.writeBytes("\r\n");
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        ArrayList<Person> list = new ArrayList<>();
        inOrderToArray(root, list);
        root = buildBalancedTree(list, 0, list.size() - 1);
        breadth(root, f123);
     
    //-------------------------------------------------------------------------------------
     f123.writeBytes("\r\n");
     f123.close();
    }  

 }
