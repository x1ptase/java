/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHAM KHAC VINH
 */
public class Main {

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int option = GetInput.getInt("Option: ", "Wrong", 1, 10);
            switch (option) {

                case 1:
                    ProductList.readFromFile("products.txt");
                    break;
                case 2:
                    ProductList.inputAndAdd("products.txt");
                    break;
                case 3:
                    ProductList.traverse();
                    break;
                case 4:
                    ProductList.saveProduct();
                    break;
                case 5:
                    ProductList.Search();
                    break;
                case 6:
                    ProductList.Delete();
                    break;
                case 7:
                    ProductList.sortByName();
                    break;
                case 8:
                    ProductList.q8();
                    break;
                case 9:
                    ProductList.q9();
                    break;
                case 10:
                    System.exit(0);
                    break;
            }

        }

    }

    private static void displayMenu() {
        System.out.println("Product list (8 marks):\n"
                + "1.1.      Load data from file\n"
                + "1.2.      Input & add to the end\n"
                + "1.3.      Display data\n"
                + "1.4.      Save product list to file\n"
                + "1.5.      Search by pcode\n"
                + "1.6.      Delete by pcode\n"
                + "1.7.      Sort by pcode\n"
                + "1.8.      Add after position  k\n"
                + "1.9.      Delete the node after the node having code = xCode");
        System.out.println("10. Exit");
    }
    
}
