package C;

import B.Editor;
import B.MyList;
import java.util.Scanner;

public class Test{
    public static void main(String[] args){
        MyList list=new MyList();
        Scanner inp=new Scanner(System.in);
        int choice=0;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add an Editor");
            System.out.println("2. Search Editor by ID");
            System.out.println("3. Update Editor by ID");
            System.out.println("4. Print All Editors");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try{
                choice=Integer.parseInt(inp.nextLine());
            } catch(NumberFormatException e){
                System.out.println("Invalid choice! Please enter a number.");
                continue;
            }

            switch(choice){
                case 1:
                    try{
                        Editor e=new Editor();
                        e.input();
                        list.add(e);
                        System.out.println("Editor added successfully!");
                    } catch(Exception e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter ID to search: ");
                    String idSearch=inp.nextLine();
                    int foundIndex=list.search(idSearch);
                    if(foundIndex!=-1){
                        list.printAll();
                    } else{
                        System.out.println("Khong tim thay ID");
                    }
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    String idUpdate=inp.nextLine();
                    list.update(idUpdate);
                    break;

                case 4:
                    list.printAll();
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice! Please enter a number between 1 && 5");
            }
        } while(choice!=5);
    }
}
