package C;

import B.MyList;
import B.Editor;
import java.util.Scanner;

public class Test{
    public static void main(String[] args) {
        MyList list=new MyList();
        Scanner inp=new Scanner(System.in);
        int choice;
        
        do{
            System.out.println("\nMenu:");
            System.out.println("1. Add");
            System.out.println("2. Search Editor by ID");
            System.out.println("3. Remove an Editor");
            System.out.println("4. Print All Editors");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice=inp.nextInt();
            inp.nextLine();
            
            switch(choice){
                case 1:
                    list.add();
                    break;
                case 2:
                    System.out.print("Enter ID to search: ");
                    String idSearch=inp.nextLine();
                    if(list.search(idSearch)) {
                        System.out.println("Editor found");
                    } else{
                        System.out.println("Editor not found");
                    }
                    break;
                case 3:
                    System.out.print("Enter ID to remove: ");
                    String idRemove=inp.nextLine();
                    Editor removed=list.remove(idRemove);
                    if(removed!=null){
                        System.out.println("Removed Editor:");
                        removed.output();
                    } else{
                        System.out.println("Editor not found");
                    }
                    break;
                case 4:
                    list.printAll();
                    break;
                case 5:
                    System.out.println("Exiting program");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while(choice!=5);
    }
}
