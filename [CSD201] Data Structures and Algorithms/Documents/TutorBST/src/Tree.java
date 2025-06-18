public class Tree{
    BSTNode<Student> root;

    public Tree(){
        root=null;
    }

    boolean isEmpty(){
        return (root == null);
    }

    public void insert(Student student){
        root=insertRec(root, student);
    }

    private BSTNode<Student> insertRec(BSTNode<Student> node, Student student){
        if(node == null){
            return new BSTNode<>(student);
        }
        if(student.compareTo(node.el) < 0){
            node.left=insertRec(node.left, student);
        } else if(student.compareTo(node.el) > 0){
            node.right=insertRec(node.right, student);
        }
        return node;
    }

    public Student search(String code){
        BSTNode<Student> node=searchRec(root, code);
        if(node != null){
            return node.el;
        }
        return null;
    }

    private BSTNode<Student> searchRec(BSTNode<Student> node, String code){
        if(node == null){
            return null;
        }
        if(node.el.getCode().equals(code)){
            return node;
        }
        if(code.compareTo(node.el.getCode()) < 0){
            return searchRec(node.left, code);
        } else{
            return searchRec(node.right, code);
        }
    }

    private BSTNode<Student> findMax(BSTNode<Student> node){
        while(node.right != null){
            node=node.right;
        }
        return node;
    }

    public void delete(String code){
        root=deleteRec(root, code);
    }

    private BSTNode<Student> deleteRec(BSTNode<Student> node, String code){
        if(node == null){
            return null;
        }

        if(code.compareTo(node.el.getCode()) < 0){
            node.left=deleteRec(node.left, code);
        } else if(code.compareTo(node.el.getCode()) > 0) {
            node.right=deleteRec(node.right, code);
        } else{ // Found the node to delete
            if(node.left == null){
                return node.right;
            }
            if(node.right == null){
                return node.left;
            }
            
            // Node has two children
            BSTNode<Student> maxLeft=findMax(node.left);
            node.el=maxLeft.el;
            node.left=deleteRec(node.left, maxLeft.el.getCode());
        }
        return node;
    }

    public void inOrder(){
        inOrderRec(root);
    }

    private void inOrderRec(BSTNode<Student> node){
        if(node != null){
            inOrderRec(node.left);
            System.out.println(node.el);
            inOrderRec(node.right);
        }
    }

    public void updateMark(String code, double newMark){
        Student student=search(code);
        if(student != null){
            student.setMark(newMark); 
            System.out.println("Updated for: " + student);
        } else{
            System.out.println("Find not found.");
        }
    }
}
