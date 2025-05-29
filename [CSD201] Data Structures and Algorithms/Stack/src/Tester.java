public class Tester{
    public static void main(String[] args){
        MyStack ms=new MyStack();
        Student st1=new Student("01", "TuanAnh", 2005, 7.2);
        Student st2=new Student("02", "TuanKiet", 2005, 8.4);
        
        ms.push(st1);
        ms.push(st2);
        System.out.println("Before");
        ms.traverse();
        
        ms.pop();
        System.out.println("After");
        ms.traverse();
    }
    
    // lấy các phần tử chẵn/ lẻ pop()
}