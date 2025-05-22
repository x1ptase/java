import java.util.Scanner;

public class Tester{
    public static void main(String[] args){
        MyDList lst=new MyDList();
        Scanner inp=new Scanner(System.in);
        
        System.out.println("Enter number of Cars: ");
        int n=Integer.parseInt(inp.nextLine());
        
        for(int i=0; i<=n; i++){
            System.out.println("C" + (i+1) + ":");
            System.out.print("     ID: ");
            String id=inp.nextLine();
            
            System.out.print("     Brand: ");
            String brand=inp.nextLine();
            
            System.out.print("     Color: ");
            String color=inp.nextLine();
            
            inp.nextLine();
            System.out.print("     Exp: ");
            int exp=inp.nextInt();
            
            while(true){
                color=inp.nextLine().trim();
                if(color.equalsIgnoreCase("Red") || color.equalsIgnoreCase("Blue") || color.equalsIgnoreCase("White")){
                    break;
                } else{
                    System.out.println("Invalid color!!!!");
                }
            }
            while(true){
                try{
                    exp=Integer.parseInt(inp.nextLine());
                    if (exp>=2020 && exp<=2030) {
                        break;
                    } else {
                        System.out.println("Invalid exp!!1");
                    }
                } catch(NumberFormatException e){
                    System.out.println("Invalid number!!!1");
                }
            }

            Car car=new Car(id, brand, color, exp);
            lst.addLast(car);
        }
        
        System.out.println("\n====== Before remove ======");
        lst.traverse();

        lst.removeFirstRed();
        lst.removeLastToyota();

        System.out.println("\n====== After remove ======");
        lst.traverse();

        lst.countByColor("Red");
    }

}