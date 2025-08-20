
public class Main {

    public static void main(String[] args) {
        BSTree t = new BSTree();

        int[] a = {15,10,30,5,20,40,4,7};
        t.insertMany(a);
        System.out.println("1.Test pre-order traversal:");
        t.preOrder(t.root);
        System.out.println();

        System.out.println("2.Test in-order traversal:");
        t.inOrder(t.root);
        System.out.println();

        System.out.println("3.Test post-order traversal:");
        t.postOrder(t.root);
        System.out.println();

        System.out.println("4.Test breadth-first traversal:");
        t.breadth(t.root);
        System.out.println();

        //delete
       
//        System.out.println("5.Test delete by copying:");
//        t.deleByCopy(10);
//        t.breadth(t.root);
//        System.out.println();
        
        System.out.println("5.Test delete by copying:");
        t.deleByMerging(15);
        t.breadth(t.root);
        System.out.println();
    }

}
