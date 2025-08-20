import java.util.Scanner;

public class Validate01{
    public static void main(String[] args){
        Scanner inp=new Scanner(System.in);
        try{
            System.out.print("Please input number: ");
            int num=inp.nextInt();
            
            System.out.println("Your input is: " + num);
        } catch(Exception e){
            System.out.println("Number must be demical numbers!");
        }
    }
}
