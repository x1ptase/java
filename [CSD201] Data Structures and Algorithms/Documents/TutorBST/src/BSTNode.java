/*
* Lop cho mot nut TONG QUAT tron cay BST
* Vi cay co thu tu nen du lieu tren cay phai co kha nang so sanh
* => nut thuc te phai hien thuc interface: java.lang.Comparable
                        <T extends Comparable>
 va co lop chan duoi la lop T nay <T extends Comparable<? super T>>
*/

public class BSTNode<T extends Comparable<? super T>>{
    protected T el;
    protected BSTNode<T> left, right;
    
    public BSTNode(){
        left=right=null;
    }
    
    public BSTNode(T el){
        this(el, null, null);
    }
    
    public BSTNode(T el, BSTNode<T> lt, BSTNode<T> rt){
        this.el=el;
        left=lt;
        right=rt;
    }
}