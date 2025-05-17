package B;

import A.IList;
import java.util.ArrayList;
import java.util.Scanner;

public class MyList implements IList{
    private ArrayList<Editor> list;

    public MyList(){
        list=new ArrayList<>();
    }

    @Override
    public void add(){
        Scanner inp=new Scanner(System.in);
        while(true){
            Editor e=new Editor();
            e.input();
            list.add(e);
            System.out.print("Do you want to add another editor? (yes/no): ");
            if(inp.nextLine().equalsIgnoreCase("no")) {
                break;
            }
        }
    }

    @Override
    public boolean search(String id){
        for(Editor e : list){
            if(e.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Editor remove(String id){
        for(Editor e : list){
            if(e.getId().equalsIgnoreCase(id)){
                list.remove(e);
                return e;
            }
        }
        return null;
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