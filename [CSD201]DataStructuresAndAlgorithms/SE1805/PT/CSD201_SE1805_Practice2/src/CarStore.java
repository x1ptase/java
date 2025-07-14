

import java.util.*;
import java.io.*;


class CarStore {

    CarQueue carQueue = new CarQueue(); 
    CarBST carTree = new CarBST();   
    Scanner sc = new Scanner(System.in);

    CarStore() {
    }

    void load(int k) throws Exception
    {
        carQueue.loadData(k);
        carTree.loadData(k);
    }
    
    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        f.writeBytes("Car Queue: ");
        if (carQueue.isEmpty()) {
            f.writeBytes("Empty");
        } else {
            Node currentQueue = carQueue.front;
            while (currentQueue != null) {
                f.writeBytes("(" +  currentQueue.info.getID() + "," + currentQueue.info.getColor()+ "," + currentQueue.info.getPrice()+ ") ");
                currentQueue = currentQueue.next;
            }
        }
        f.writeBytes("\r\n");
        f.writeBytes("Car BST: ");
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentTree = carTree.root;
        while(currentTree != null || !stack.isEmpty()){
            while(currentTree != null){
                stack.push(currentTree);
                currentTree = currentTree.left;
            }
            currentTree = stack.pop();
            f.writeBytes("(" +  currentTree.info.getID() + "," + currentTree.info.getColor()+ "," + currentTree.info.getPrice()+ ") ");
            currentTree = currentTree.right;
        }
        f.writeBytes("\r\n");
    }
    
    String loadInput(int k)
    {
        String a = Lib.readLineToStr("data.txt", k);
        return a;
    }

//===========================================================================
//=======YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================

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

        String searchTaskID = loadInput(5); 
        System.out.println("Searching for Car ID in BST: " + searchTaskID);
        Car found = carTree.search(searchTaskID);
        
        if (found != null){
            f.writeBytes("Found: ");
            f.writeBytes("(" +  found.getID() + "," + found.getColor()+ "," + found.getPrice()+ ") ");
            f.writeBytes("\r\n");
        }else{
            f.writeBytes("Car not found in BST");
            f.writeBytes("\r\n");
        }
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
        
        Car max = carTree.findMax();
        if (max != null){
            f.writeBytes("Highest Alphabetical Car ID in BST: ");
            f.writeBytes("(" +  max.getID() + "," + max.getColor()+ "," + max.getPrice()+ ") ");
            f.writeBytes("\r\n");
        }else{
            f.writeBytes("Car BST is empty (no highest ID found)");
            f.writeBytes("\r\n");
        }
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

        Car dequeueCar = carQueue.dequeue();
        if (dequeueCar != null) {
            System.out.println("Dequeued from Car Queue: " + dequeueCar.getID() + " (" + dequeueCar.getColor()+ ")");
        } else {
            System.out.println("Car Queue is empty, nothing to dequeue.");
        }
        
        String deleteCarID = loadInput(7); 
        System.out.println("Delete "+ deleteCarID +" from both Queue and Tree: ");

        carQueue.remove(deleteCarID);
        carTree.remove(deleteCarID);
        
        ftraverse(f); 
        f.close();
    }
}
