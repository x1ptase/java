import java.util.Scanner;

class NumberPairs{
    private int a;
    private int b;
    
    public NumberPairs(int a, int b){
        this.a=a;
        this.b=b;
    }
    
    public void swap(int a, int b){
        int tmp=this.a;
        this.a=this.b;
        this.b=tmp;
    }
    
    public void printNum(){
        System.out.println("SWAP");
        System.out.println("a=" + a + ", b=" + b);
    }
}

public class PassByValue{
    public static void main(String[] args){
        Scanner inp=new Scanner(System.in);
        System.out.print("a=");
        int a=inp.nextInt();
        System.out.print("b=");
        int b=inp.nextInt();
        
        
        NumberPairs num=new NumberPairs(a, b);
        num.swap(a, b);
        num.printNum();
    }
}
