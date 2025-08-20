package B;

import A.Window;
import java.util.Scanner;

public class Editor extends Window{
    private String id, title;

    public Editor(){
        super();
        this.id="";
        this.title="";
    }

    public Editor(int width, int length, String id, String title){
        super(width, length);
        this.id=id;
        this.title=title;
    }

    public String getId(){
        return id.toUpperCase();
    }
    
    /*
    * (?i) khong phan biet hoa/thuong
    * ^ start
    * \\d{3} lay 3 chu so
    */
    public void setId(String id) throws Exception{
        if(!id.matches("(?i)^E\\d{3}")) throw new Exception("Sai format em oi (Exxx)");
        this.id=id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) throws Exception{
        if(title==null || title.trim().isEmpty()) throw new Exception("Nhap lai deee");
        this.title=title;
    }

    @Override
    public void input(){
        super.input();
        Scanner inp=new Scanner(System.in);
        
        try {
            System.out.print("Enter ID (Exxx): ");
            setId(inp.next());
            inp.nextLine(); 
            System.out.print("Enter title: ");
            setTitle(inp.nextLine());
        } catch(Exception e){
            System.out.println("Sai input be oii");
        }
    }

    @Override
    public void output(){
        System.out.println("Editor [" + id.toUpperCase() + "-" + title + "-" +getWidth()+"-"+getLength()+"]");
    }
}
