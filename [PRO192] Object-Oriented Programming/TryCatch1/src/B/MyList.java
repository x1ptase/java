package B;

import A.IList;
import A.Window;
import java.util.ArrayList;

public class MyList implements IList{
    private ArrayList<Window> list;

    public MyList(){
        list=new ArrayList<>();
    }

    @Override
    public boolean add(Window x){
        return list.add(x);
    }

    @Override
    public Window search(String id){
        for(Window w : list){
            if(w instanceof Editor){
                if(((Editor) w).getId().compareTo(id.toUpperCase())==0)
                    return w;
            }
        }
        return null;
    }

    @Override
    public void printAll(){
        for(Window w : list)
            w.output();
    }
}