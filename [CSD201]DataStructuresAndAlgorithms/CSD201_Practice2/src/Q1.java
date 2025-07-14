

import java.util.Scanner;

public class Q1 {

    public static void main(String[] args) throws Exception {
        CarStore pm = new CarStore();
        int choice;
        Scanner sca = new Scanner(System.in);
        System.out.println();
        System.out.println(" 1. Test f1");
        System.out.println(" 2. Test f2");
        System.out.println(" 3. Test f3");
        System.out.println(" 4. Test f4");
        System.out.print("    Your selection (1 -> 4): ");

        while (!sca.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number between 1 and 4.");
            System.out.print("    Your selection (1 -> 4): ");
            sca.next();
        }
        choice = sca.nextInt();
        sca.nextLine();

        switch(choice) {
            case 1: 
                pm.f1();
                System.out.println("\nOUTPUT (f1.txt):");
                Lib.viewFile("f1.txt");
                break;
            case 2: 
                pm.f2();
                System.out.println("\nOUTPUT (f2.txt):");
                Lib.viewFile("f2.txt");
                break;
            case 3: 
                pm.f3();
                System.out.println("\nOUTPUT (f3.txt):");
                Lib.viewFile("f3.txt");
                break;
            case 4: 
                pm.f4();
                System.out.println("\nOUTPUT (f4.txt):");
                Lib.viewFile("f4.txt");
                break;
            default: 
                System.out.println("Wrong selection. Please choose a number from 1 to 4.");
        }
        System.out.println();
        sca.close();
    }     
}
