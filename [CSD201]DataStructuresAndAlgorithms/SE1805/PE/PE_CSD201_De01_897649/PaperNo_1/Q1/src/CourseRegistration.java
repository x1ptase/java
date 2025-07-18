import java.io.*;

class SllStudent {
    SllNode head, tail;

    SllStudent() { head = tail = null; }

    boolean isEmpty() { return (head == null); }

    void clear() { head = tail = null; }
    
    void loadDataRegistration(int k) { //do not edit this function   
        int[] a = Lib.readLineToIntArray("data.txt", k);
        String[] b = Lib.readLineToStrArray("data.txt", k + 1);
        boolean[] c = Lib.readLineToBooleanArray("data.txt", k + 2);      
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addStudent(a[i], b[i], c[i]);
        }
    }

    void addStudent(int xID, String xName, boolean xFee) {     
        SllNode newNode = new SllNode(new Student(xID,xName,xFee));
        //You should insert here statements to complete this function
        //------------------------------------------------------------------
        SllNode p=new SllNode(new Student(xID, xName, xFee));
        if(isEmpty()){
            head=tail=p;
        } else{
            p.next=head;
            head=p;
        }
        //------------------------------------------------------------------
    }
    
    void printInsertionOrder(RandomAccessFile f) throws Exception {
        //You should insert here statements to complete this function
        //------------------------------------------------------------------
        SllNode p=head;
        int count=0;
        while( p!= null && count < 5){
            if(p.info.isFee()){
                f.writeBytes("(" + p.info.getId() + "," + p.info.getName() + "," + p.info.isFee() + ")");
                count++;
            }
            p=p.next;
        }
        //------------------------------------------------------------------
    }

    void display(RandomAccessFile f) throws Exception {
        SllNode p = head;
        while (p != null) {
            f.writeBytes("(" + p.info.getId() + "," 
                        + p.info.getName() + "," + p.info.isFee() + ") ");
            p = p.next;       
        }
        f.writeBytes("\r\n");
        f.close();
    }
}

class BstStudent {
    BstNode root;
    
    BstStudent() { root = null; }

    boolean isEmpty() { return (root == null); }

    void clear() { root = null; }

    void fvisit(BstNode p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void inOrder(BstNode p, RandomAccessFile f) throws Exception {
        if (p == null) { return; }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }
    
    void printPaidDesc(BstNode p, RandomAccessFile f) throws Exception {
        //You should insert here statements to complete this function
        //------------------------------------------------------------------
        if(p == null) return;
        printPaidDesc(p.right, f);
        if(p.info.isFee()){
            f.writeBytes("(" + p.info.getId() + "," + p.info.getName() + "," + p.info.isFee() + ")");
        }
        printPaidDesc(p.left, f);
        //------------------------------------------------------------------
    }
    
    void loadDataRegistration(int k) //do not edit this function
    {
        int[] a = Lib.readLineToIntArray("data.txt", k);
        String[] b = Lib.readLineToStrArray("data.txt", k + 1);
        boolean[] c = Lib.readLineToBooleanArray("data.txt", k + 2);   
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]);
        }
    }
    
    BstNode insertStudent(BstNode root, Student std) {
        //You should insert here statements to complete this function
        //------------------------------------------------------------------
        if(root == null){
            return new BstNode(std);
        }
        if(std.getId() < root.info.getId()){
            root.left=insertStudent(root.left, std);
        } else if(std.getId() > root.info.getId()){
            root.right=insertStudent(root.right, std);
        } else{
            root.info=std;
        }
        //------------------------------------------------------------------
        return root;
    }
    
    void insert(int xID, String xName, boolean xFee) {
        //You should insert here statements to complete this function
        //---------------------------------------------------------------------
        this.root = insertStudent(this.root, new Student(xID,xName,xFee)); 

	//---------------------------------------------------------------------       
    }
    
    int count(BstNode node) {    
        //You should insert here statements to complete this function
        //------------------------------------------------------------------
        if(node == null){
            return 0;
        }
        //------------------------------------------------------------------
        return 1 + count(node.right);
    }
    
    public BstNode searchById(BstNode node, int id) {
        //You should insert here statements to complete this function
        //------------------------------------------------------------------
        
        //------------------------------------------------------------------
        return null;
    }

    public BstNode search(int id) {
        //You should insert here statements to complete this function
        //------------------------------------------------------------------
        
        //------------------------------------------------------------------
        return null;
    }
}

public class CourseRegistration {
    SllStudent sList = new SllStudent();
    BstStudent bList = new BstStudent();

    CourseRegistration() { }

    void load(int k) throws Exception { //do not edit this function  
        sList.loadDataRegistration(k);
        bList.loadDataRegistration(k);
    }
//=============================================================================
//========YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART=========
//=============================================================================   
    void f1() throws Exception {
        load(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        sList.printInsertionOrder(f);
    }

    void f2() throws Exception {
        load(1);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        //---------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
        Your task is to insert statements here, just after this comment,
        to complete the question in the exam paper.*/        
        int i = 0;
        i = bList.count(bList.root);
        String c = "" + i;
        f.writeBytes(c.trim());   
        //---------------------------------------------------------------------
        f.close();
    }

    void f3(int search_id) throws Exception {
        load(1);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        //---------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
        Your task is to insert statements here, just after this comment,
        to complete the question in the exam paper.*/  
        BstNode p = bList.search(search_id);
        if (p != null) {
            f.writeBytes("(" + p.info.getId() + "," 
                        + p.info.getName() + "," + p.info.isFee() + ") ");
            f.writeBytes("\r\n");
            f.close();
        } else {
            f.writeBytes("Not found");
            f.writeBytes("\r\n");
            f.close();
        }     
        //---------------------------------------------------------------------
    }

    void f4() throws Exception {
        load(1);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        bList.inOrder(bList.root, f);
        f.writeBytes("\r\n");
        //---------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
        Your task is to insert statements here, just after this comment,
        to complete the question in the exam paper.*/        
        bList.printPaidDesc(bList.root, f);       
        //---------------------------------------------------------------------
        f.close();
    }
}