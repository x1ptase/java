package data;

import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Inputter {
    private static final Scanner sc = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * Reads a string input without validation.
     *
     * @param message Prompt message
     * @return Trimmed input string
     */
    public static String getString(String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }

    /**
     * Reads a string input and validates it against a given regex pattern.
     *
     * @param message Prompt message
     * @param pattern Regex pattern for validation
     * @return Valid input string
     */
    public static String getStringWithPattern(String message, String pattern) {
        String input;
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (Acceptable.isValid(input, pattern)) {
                return input;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        } while (true);
    }

    /**
     * Reads an integer input.
     *
     * @param message Prompt message
     * @return Valid integer input
     */
    public static int getInt(String message) {
        int number;
        while (true) {
            try {
                System.out.print(message);
                number = Integer.parseInt(sc.nextLine().trim());
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    /**
     * Reads a double input.
     *
     * @param message Prompt message
     * @return Valid double input
     */
    public static double getDouble(String message) {
        double number;
        while (true) {
            try {
                System.out.print(message);
                number = Double.parseDouble(sc.nextLine().trim());
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a decimal number.");
            }
        }
    }

    /**
     * Reads a date input and validates it as a future date.
     *
     * @param message Prompt message
     * @return Valid future date
     */
    public static Date getFutureDate(String message) {
        Date date;
        while (true) {
            try {
                System.out.print(message);
                date = dateFormat.parse(sc.nextLine().trim());
                if (date.after(new Date())) return date;
                else System.out.println("Date must be in the future.");
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter in dd/MM/yyyy.");
            }
        }
    }


}
