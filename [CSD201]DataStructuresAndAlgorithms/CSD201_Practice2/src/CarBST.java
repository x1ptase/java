

public class CarBST {
    TreeNode root;

    void loadData(int k) {
        String[] carIDs = Lib.readLineToStrArray("data.txt", k);
        String[] colors = Lib.readLineToStrArray("data.txt", k + 1);
        double[] prices = Lib.readLineToDoubleArray("data.txt", k + 2);
        int n = carIDs.length;
        for (int i = 0; i < n; i++) {
            Car newBooks = new Car(carIDs[i], colors[i], prices[i]);
            insert(newBooks);
        }
    }

    public void insert(Car c) {
        //You should write here appropriate statements to complete this function.
        //YOU CAN ADD NEW FUNCTIONS IN THE FOLLOWING PART IF NEEDED
        //--------------------------------------------------------
        if(root == null){
            root=new TreeNode(c);
            return;
        }
        
        TreeNode current=root;
        TreeNode parent=null;
        String id=c.getID();
        
        while(current != null){
            parent=current;
            int cmp=id.compareTo(current.info.getID());
            if(cmp == 0){
                current.info=c;
                return;
            } else if(cmp < 0){
                current=current.left;
            } else{
                current=current.right;
            }
        }

        if(id.compareTo(parent.info.getID()) < 0){
            parent.left=new TreeNode(c);
        } else{
            parent.right=new TreeNode(c);
        }
        //---------------------------------------------------------
    }

    

    public Car search(String id) {
       //You should write here appropriate statements to complete this function.
        //YOU CAN ADD NEW FUNCTIONS IN THE FOLLOWING PART IF NEEDED
        //--------------------------------------------------------
        TreeNode current=root;
        while(current != null){
            int cmp=id.compareTo(current.info.getID());
            if(cmp == 0){
                return current.info;
            } else if(cmp < 0){
                current=current.left;
            } else{
                current=current.right;
            }
        }
        //---------------------------------------------------------
        return null; //if not found
    }
    
    private Car findMax(TreeNode node){
       if(node == null){
           return null;
       }
       while(node.right != null){
           node=node.right;
       }
       return node.info;
   }

    public Car findMax() {
        //You should write here appropriate statements to complete this function.
        //YOU CAN ADD NEW FUNCTIONS IN THE FOLLOWING PART IF NEEDED
        //--------------------------------------------------------
        return findMax(root);
        //---------------------------------------------------------
        
    }
    
    public void remove(String id) {
        //You should write here appropriate statements to complete this function.
        //YOU CAN ADD NEW FUNCTIONS IN THE FOLLOWING PART IF NEEDED
        //--------------------------------------------------------

        //---------------------------------------------------------
    }
    
}
