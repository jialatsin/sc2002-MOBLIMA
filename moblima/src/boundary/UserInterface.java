package boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class UserInterface {
    public static Scanner sc = new Scanner(System.in);

    public static int scanInt() {
        int input;
        while (true) {
            try {
                input = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input!");
            }
            sc.nextLine();
        }
        return input;
    }

    public static String scanString() {
        String input = "";
        while (true) {
            if (!input.equals("")) {
                input = sc.next();
                break;
            } else {
                System.out.println("Wrong input!");
            }
            sc.nextLine();
        }
        return input;
    }
}
