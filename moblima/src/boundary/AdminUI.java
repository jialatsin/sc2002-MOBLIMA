package boundary;

import control.AdminController;

/**
 * Represents the main menu user interface for an Admin, which can only be
 * accessed by logging in with an admin account.
 * An Admin can create, update, delete movie listings and movie showings,
 * configure system settings, and list top 5 ranking movies.
 */
public class AdminUI {
    /** The admin controller to be referenced. */
    private static AdminController adminController = new AdminController();

    /**
     * Main menu for an admin for all possible admin actions.
     * Displays 5 options: Create/Update/Remove Movie Listing,
     * Create/Update/Remove Movie Showings, Configure System Settings, List Top 5
     * Ranking Movies, Logout to Main Menu.
     */
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
                case 1: // Create/Update/Remove Movie Listing
                    CRUDMovieListingUI.main();
                    break;
                case 2: // Create/Update/Remove Movie Showings
                    CRUDMovieShowingUI.main();
                    break;
                case 3: // Configure System Settings
                    SystemConfigUI.main();
                    break;
                case 4: // List Top 5 Ranking Movies
                    ListTopMovies.main();
                    break;
                case 0: // Logout to Main Menu
                    return;
            }
        } while (true);
    }

    /**
     * Prompts user to login as admin with username and password.
     * 
     * @return Returns true if the user has been successfuly validated as an admin,
     *         else false.
     */
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