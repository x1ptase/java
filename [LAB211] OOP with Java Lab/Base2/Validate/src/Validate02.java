import java.util.Scanner;
/*
* Phương thức Integer.parseInt(String s) 
có nhiệm vụ chuyển một chuỗi (string) thành số nguyên (kiểu int)
*/

public class Validate02 {
    public static void main(String[] args) {
        Scanner inp=new Scanner(System.in);
        try {
            System.out.print("Please input number: ");
            String s=inp.nextLine();
            int num=Integer.parseInt(s);
            
            System.out.println("Your input is: " + num);
            
        } catch(NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid decimal number.");
        }
    }
}
