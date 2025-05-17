public class Tester{
    public static void main(String[] args){
        MyList list=new MyList();
        list.addFirst(2); 
        list.addLast(3);
        list.addFirst(9);
        list.addLast(17);
        list.addFirst(3);
        list.addLast(18);
        list.traverse();
    }
}