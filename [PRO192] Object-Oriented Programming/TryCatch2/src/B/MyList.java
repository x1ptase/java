package B;

import A.IList;
import java.util.ArrayList;

public class MyList implements IList{
    private ArrayList<Editor> list;

    public MyList(){
        list=new ArrayList<>();
    }

    @Override
    public boolean add(Editor x){
        return list.add(x);
    }

    @Override
    public int search(String id){
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getId().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }

    @Override
    public boolean update(String id){
        int index=search(id);
        if(index!=-1){
            System.out.println("Current details:");
            list.get(index).output();
            System.out.println("Enter new details:");
            
            try {
                list.get(index).input();
                return true;
            } catch (Exception e) {
                System.out.println("Error updating editor: " + e.getMessage());
            }
        }else
            System.out.println("Khong tim thay ID");
        return false;
    }

    @Override
    public void printAll(){
        if(list.isEmpty()){
            System.out.println("No available");
        } else{
            for(Editor e : list){
                e.output();
            }
        }
    }
}
