import java.util.Scanner;

public class Validate03{
    public static int getInt(String message, String error){
        Scanner inp=new Scanner(System.in);
        while(true){
            try{
                System.out.print(message);
                String input=inp.nextLine().trim();
                int num=Integer.parseInt(input);
                return num;
            } catch(Exception e){
                System.out.println(error);
            }
        }
    }
  
    public static void main(String[] args){
        int num=getInt("Input number: ", "Wrong");
        System.out.println("Number: " + num);
        
        int age=getInt("Input age: ", "Wrong");
        System.out.println("Age: " + age);
    }
}
