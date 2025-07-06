import java.util.Scanner;

public class Tester{
    public static void main(String[] args){
        Tree tree=new Tree();
        Scanner inp=new Scanner(System.in);
        int choice;

        do{
            System.out.println("\n=========== MENU ===========");
            System.out.println("1. Add student");
            System.out.println("2. Find student by ID");
            System.out.println("3. Update mark");
            System.out.println("4. Delete student");
            System.out.println("5. Display to stduent");
            System.out.println("6. Quit");
            System.out.print("Choose option (1-6): ");
            choice=Integer.parseInt(inp.nextLine());

            switch(choice){
                case 1:
                    System.out.print("Enter studentID: ");
                    String code=inp.nextLine();
                    System.out.print("Enter studentName: ");
                    String name=inp.nextLine();
                    System.out.print("Enter mark: ");
                    double mark=Double.parseDouble(inp.nextLine());

                    tree.insert(new Student(code, name, mark));
                    System.out.println("Add student successfull.");
                    break;

                case 2:
                    System.out.print("Enter studentID: ");
                    String searchCode=inp.nextLine();
                    Student found=tree.search(searchCode);
                    if(found != null)
                        System.out.println("Found: " + found);
                    else
                        System.out.println("Find not found.");
                    break;

                case 3:
                    System.out.print("Enter studentID: ");
                    String updateCode = inp.nextLine();
                    System.out.print("Enter newMark: ");
                    double newMark = Double.parseDouble(inp.nextLine());
                    tree.updateMark(updateCode, newMark);
                    break;

                case 4:
                    System.out.print("Enter student: ");
                    String deleteCode = inp.nextLine();
                    tree.delete(deleteCode);
                    System.out.println("Deleted.");
                    break;

                case 5:
                    System.out.println("Student lists (In-Order):");
                    tree.inOrder();
                    break;

                case 6:
                    System.out.println("Exit...");
                    break;

                default:
                    System.out.println("Choose again..");
            }
        } while(choice != 0);
    }
}