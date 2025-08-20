

public class TreeNode {
    Car info;     
    TreeNode left; 
    TreeNode right; 
    
   TreeNode(Car info, TreeNode left, TreeNode right){
        this.info = info;
        this.left = left;
        this.right = right;
    }

   TreeNode(Car info){
       this(info, null, null);
   }
}