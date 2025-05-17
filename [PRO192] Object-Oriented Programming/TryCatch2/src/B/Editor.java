package B;

import java.util.Scanner;

public class Editor{
    private String id, title, version;
    private int width, length;

    public Editor(){
    }

    public Editor(String id, String title, int width, int length, String version) {
        this.id = id;
        this.title = title;
        this.width = width;
        this.length = length;
        this.version = version;
    }

    public String getId(){
        return id;
    }

    public void setId(String id) throws Exception{
        if (!id.matches("(?i)^EDT\\d+$")) 
            throw new Exception("ID format (EDTx, x is digit)");
        this.id = id.toUpperCase();
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) throws Exception {
        if (title==null || title.trim().isEmpty()) 
            throw new Exception("Title cannot be empty");
        this.title=title;
    }

    public int getWidth(){
        return width;
    }

    public void setWidth(int width) throws Exception {
        if(width<10 || width>100) 
            throw new Exception("Width between 10 && 100");
        this.width=width;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int length) throws Exception {
        if (length<10 || length>100) 
            throw new Exception("Length between 10 && 100");
        this.length=length;
    }

    public String getVersion(){
        return version;
    }

    public void setVersion(String version) throws Exception{
        if(!version.matches("\\d+\\.\\d+\\.\\d+")) 
            throw new Exception("Invalid version format (must be x.x.x)");
        this.version=version;
    }

    public void input() throws Exception{
        Scanner inp=new Scanner(System.in);

        System.out.print("Enter ID: ");
        setId(inp.nextLine());

        System.out.print("Enter Title: ");
        setTitle(inp.nextLine());

        System.out.print("Enter Width: ");
        setWidth(inp.nextInt());

        System.out.print("Enter Length: ");
        setLength(inp.nextInt());
        inp.nextLine(); 

        System.out.print("Enter Version: ");
        setVersion(inp.nextLine());
    }

    public void output(){
        System.out.println("[" + id + " | " + title + " | " + width + "-" + length + " | " + version + "]");
    }
}
