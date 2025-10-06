import java.util.Scanner;

public class Test03{
    public static void main(String[] args){
        Scanner inp=new Scanner(System.in);
        System.out.print("Enter a day: ");
        int days=inp.nextInt();
        
        switch(days){
            case 2:
                System.out.println("Monday");
                break;
            case 3:
                System.out.println("Tuesday");
                break;
            case 4:
                System.out.println("Wednesday");
                break;
            case 5:
                System.out.println("Thursday");
                break;
            case 6:
                System.out.println("Friday");
                break;
            case 7:
                System.out.println("Saturday");
                break;
            case 8:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Error...");
        }
    }
}
