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
        ListSong songList = new ListSong();
        while (true) {
            displayMenu();
            int option = GetInput.getInt("Enter option: ", "Option must be digit", 1, 8);
            switch (option) {
                case 1:
                    songList.q1();
                    break;
                case 2:
                    songList.q2();
                    break;
                case 3:
                    songList.q3();
                    break;
                case 4:
                    songList.q4();
                    break;
                case 5:
                    songList.q5();
                    break;
                case 6:
                    songList.q6();
                    break;
                case 7:
                    songList.q7();
                    break;
                case 8:
                    System.exit(0);
                    break;

            }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Question 1");
        System.out.println("2. Question 2");
        System.out.println("3. Question 3");
        System.out.println("4. Question 4");
        System.out.println("5. Question 5");
        System.out.println("6. Question 6");
        System.out.println("7. Question 7");
        System.out.println("8. Exit");
    }
}
