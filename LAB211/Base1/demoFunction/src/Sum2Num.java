import java.util.Scanner;

public class Sum2Num{
    public static int sum(int a, int b){
        return a+b;
    }
    
    public static void main(String[] args){
        Scanner inp=new Scanner(System.in);
        int a=inp.nextInt();
        int b=inp.nextInt();
        
        System.out.println(sum(a, b));
    }
}
