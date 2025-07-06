package Controller;

import Model.Circle;
import Model.Rectangle;
import Model.Shape;
import Model.Triagle;
import java.util.ArrayList;

public class Manage{
    ArrayList<Shape> list;

    public Manage(){
        list=new ArrayList<>();
    }

    public void program(){
        while(true){
            displayMenu();
            int choice = Validation.getChoice("Enter an option: ", 1, 5);
            switch (choice) {
                case 1://hinh tron
                    Shape circle = new Circle();
                    list.add(circle);
                    break;
                case 2://hcn
                    Shape rectange = new Rectangle();
                    list.add(rectange);
                    break;
                case 3://tam giac
                    Shape triagle = new Triagle();
                    list.add(triagle);
                    break;
                case 4:
                    for (Shape shape : list) {
                        shape.input();
                    }
                    break;
                case 5:
                    for (Shape shape : list) {
                        shape.printResult();
                    }
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }

    private void displayMenu() {
        System.out.println("");
        System.out.println("1. Circle\n"
                + "2. Rectangle\n"
                + "3. Triagle\n"
                + "4. Input\n"
                + "5. Print Result\n"
                + "6. Exit");
    }

}
