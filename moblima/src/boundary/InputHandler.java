package boundary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Handle user input of various low-level datatypes
public class InputHandler {
    // Scanner must only be initialized once
    private static Scanner sc = new Scanner(System.in);

    private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

    public static DateTimeFormatter getDateTimeFormat() {
        return dateTimeFormat;
    }

    public static DateTimeFormatter getDateFormat() {
        return dateFormat;
    }
}
