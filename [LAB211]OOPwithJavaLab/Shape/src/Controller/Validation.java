package Controller;

import java.util.Scanner;

public class Validation {

    public static double getFloat(String msg) {
        Scanner sc = new Scanner(System.in);
        double data;
        while (true) {
            try {
                System.out.print(msg);
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Can not blank!");
                } else {
                    data = Float.parseFloat(input);
                    return data;
                }
            } catch (NumberFormatException e) {
                System.out.println("please enter number");
            }
        }
    }

    public static int getChoice(String msg, int min, int max) {
        Scanner sc = new Scanner(System.in);
        int data;
        while (true) {
            try {
                System.out.print(msg);
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Can not blank!");
                } else {
                    data = Integer.parseInt(input);
                    if (data >= min && data <= max) {
                        return data;
                    } else {
                        System.out.println("You must enter between " + min
                                + " to " + max);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter integer number!");
            }

        }
    }
}
