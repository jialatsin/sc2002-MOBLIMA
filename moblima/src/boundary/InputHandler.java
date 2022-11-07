package boundary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * It is used to take user input of various primitive or low-level datatypes, and it is implemented for static declaration of Scanner, to prevent multiple Scanners from being created.
 * @author OOP LAB Group 4
 * @version 8/11/2022
 */
public class InputHandler {
    /**
     * Scanner must only be initialized once. 
     */
    private static Scanner sc = new Scanner(System.in);
    /**
     * It is for the date time format
     */
    private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    /**
     * It is for the date format
     */
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Prompts user for an integer input until correct input is given.     * 
     * @return input It returns Valid int 
     */
    public static int scanInt() {
        int input;
        while (true) {
            try {
                input = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input must be of type integer!");
            } finally {
                // Consume end of line token in buffer dangling from int input
                sc.nextLine();
            }
        }
        return input;
    }

    /**
     * Prompts user for an double input until correct input is given.     * 
     * @return input It returns Valid double
     */
    public static double scanDouble() {
        double input;
        while (true) {
            try {
                input = sc.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input must be of type double!");
            } finally {
                // Consume end of line token in buffer dangling from double input
                sc.nextLine();
            }
        }
        return input;
    }

    /**
     * Prompts user for a boolean input until correct input is given.
     * @return input It returns Valid boolean
     */
    public static boolean scanBoolean() {
        boolean input;
        while (true) {
            try {
                input = sc.nextBoolean();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input must be of type boolean (true/false)!");
            } finally {
                // Consume end of line token in buffer dangling from boolean input
                sc.nextLine();
            }
        }
        return input;
    }

    /**
     * Prompts user for an character input until correct input is given.
     * @return input It returns Valid char
     */
    public static char scanChar() {
        String input = "";
        while (true) {
            input = sc.nextLine();
            if (input.length() == 1) {
                break;
            } else {
                System.out.println("Input must be 1 character long!");
            }
        }
        return input.charAt(0);
    }

    /**
     * Prompts user for a string input until correct input is given.
     * @return input It returns Valid string
     */
    public static String scanString() {
        String input = "";
        while (true) {
            input = sc.nextLine();
            if (!input.equals("")) {
                break;
            } else {
                System.out.println("Input must not be empty string!");
            }
        }
        return input;
    }

    /**
     * Prompts user for a mobile number of 8 digits until correct input is given.
     * @return input It returns Valid string which contains a 8 digit mobile number
     */
    public static String scanMobileNumber() {
        String pattern = "\\d{8}";
        String input = "";
        while (true) {
            input = sc.nextLine();
            if (input.matches(pattern)) {
                break;
            } else {
                System.out.println("Input must be a mobile number with 8 digits!");
            }
        }
        return input;
    }

    /**
     * Prompts user for an email address until correct input is given.
     * @return input It returns Valid String containing email address
     */
    public static String scanEmail() {
        String pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String input = "";
        while (true) {
            input = sc.nextLine();
            if (input.matches(pattern)) {
                break;
            } else {
                System.out.println("Input must be of email format!");
            }
        }
        return input;
    }

    /**
     * Prompts user for a datetime of format dd/MM/yyyy HH:mm until correct input is given.
     * 
     * @return input It returns Valid LocalDateTime in the ' dd/MM/yyyy HH:mm ' format
     */
    public static LocalDateTime scanDateTime() {
        String input = "";
        LocalDateTime dateTime;
        while (true) {
            try {
                input = sc.nextLine();
                dateTime = LocalDateTime.parse(input, dateTimeFormat);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Input must be of format 'dd/MM/yyyy HH:mm' (eg. 01/12/2022 23:59)!");
            }
        }
        return dateTime;
    }

    /**
     * Prompts user for a datetime of format dd/MM/yyyy until correct input is given.
     * @return input It returns Valid LocalDateTime in the ' dd/MM/yyyy ' format
     */
    public static LocalDate scanDate() {
        String input = "";
        LocalDate date;
        while (true) {
            try {
                input = sc.nextLine();
                date = LocalDate.parse(input, dateFormat);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Input must be of format 'dd/MM/yyyy'(eg. 01/12/2022)!");
            }
        }
        return date;
    }
    /**
     * Get the date and time in this format ' dd/MM/yyyy HH:mm '
     * @return dateTimeFormat
     */
    public static DateTimeFormatter getDateTimeFormat() {
        return dateTimeFormat;
    }
    /**
     * Get the date in this format ' dd/MM/yyyy '
     * @return dateFormat 
     */
    public static DateTimeFormatter getDateFormat() {
        return dateFormat;
    }
}
