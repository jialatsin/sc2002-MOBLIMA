package boundary;

import control.AdminController;

public class AdminUI {
    private static AdminController adminController = new AdminController();

    public static void main() {
        boolean isLoggedIn = login();
        if (!isLoggedIn) {
            return;
        }

        int selection;
        do {
            System.out.println("\n===== ADMIN =====\n"
                    + "1. Create/Update/Remove Movie Listing\n"
                    + "2. Create/Update/Remove Movie Showings\n"
                    + "3. Configure System Settings\n"
                    + "4. List Top 5 Ranking Movies\n"
                    + "0. Logout to Main Menu\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    CRUDMovieListingUI.main();
                    break;
                case 2:
                    CRUDMovieShowingUI.main();
                    break;
                case 3:
                    SystemConfigUI.main();
                    break;
                case 4:
                    ListTopMovies.main();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    private static boolean login() {
        boolean isLoggedIn = false;
        System.out.println("===== ADMIN LOGIN =====");
        System.out.println("Enter username:");
        String usernameInput = InputHandler.scanString();
        System.out.println("Enter password:");
        String passwordInput = InputHandler.scanString();

        isLoggedIn = adminController.loginValidation(usernameInput, passwordInput);
        if (isLoggedIn) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Login failed!");
            return false;
        }
    }
}