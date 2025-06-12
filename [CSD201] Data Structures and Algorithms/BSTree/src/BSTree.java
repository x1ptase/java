public class BSTree{
    Node root;
    
    public BSTree(){
        root=null;
    }
    
    public void clear(){
        root=null;
    }
    
    public boolean isEmpty(){
        return (root == null);
    }
    
    public void insert(int x){
        if(isEmpty()){
            root=new Node(x);
            return;
        }
        
        Node current=root;
        while(true){
            if(x == current.info){
                System.out.println("The key " + x + " already exists");
                return;
            }
            if(x < current.info){
                if(current.left == null){
                    current.left=new Node(x);
                    return;
                }
                current=current.left;
            } else{
                if(current.right == null){
                    current.right=new Node(x);
                    return;
                }
                current=current.right;
            }
        }
    }
    
    public void traverse(Node p) {
        if(p == null){
            return;
        }
        traverse(p.left); 
        System.out.print(p.info + " "); 
        traverse(p.right); 
    }
    
    public void traverse(){
        traverse(root);
        System.out.println(); 
    }
}