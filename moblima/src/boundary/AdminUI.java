package boundary;

import entity.Admin;

public class AdminUI {
    public static void main() {
        // TODO: Better login
        Admin admin = new Admin("admin", "bofa");
        boolean isLoggedIn = false;

        System.out.println("===== ADMIN LOGIN =====");
        do {
            System.out.println("Enter username:");
            String usernameInput = InputHandler.scanString();
            System.out.println("Enter password:");
            String passwordInput = InputHandler.scanString();
            if (usernameInput.equals(admin.getUsername()) &&
                    passwordInput.equals(admin.getPassword())) {
                isLoggedIn = true;
                System.out.println("Login successful!");
            } else {
                System.out.println("Failed to login!");
            }
        } while (!isLoggedIn);

        int selection;
        do {
            System.out.println("===== ADMIN =====");
            System.out.println("1. Create/Update/Remove Movie Listing");
            System.out.println("2. Create/Update/Remove Movie Showings");
            System.out.println("3. Configure System Settings");
            System.out.println("4. Return to Main Menu");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    return;
            }
        } while (true);
    }

}
