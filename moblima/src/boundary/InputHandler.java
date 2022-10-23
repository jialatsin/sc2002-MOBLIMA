package boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    // Scanner must only be initialized once
    private static Scanner sc = new Scanner(System.in);

    public static int scanInt() {
        int input;
        while (true) {
            try {
                input = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input!");
            } finally {
                sc.nextLine();
            }
        }
        return input;
    }

    public static String scanString() {
        String input = "";
        while (true) {
            input = sc.nextLine();
            if (!input.equals("")) {
                break;
            } else {
                System.out.println("Input must not be empty!");
            }
        }
        return input;
    }
}
