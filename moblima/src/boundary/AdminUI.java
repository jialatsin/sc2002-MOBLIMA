package boundary;

import control.AdminController;

/**
 * The Admin UI contains the main menu user interface for an Admin, which can only be accessed by logging in with admin account, an Admin can create, update, delete movie listings and movie showings, configure system settings, list top 5 ranking movies.
 * @author OOP LAB Group 4
 * @version 8/11/2022
 */
public class AdminUI {
	/** 
	 * The admin controller 
	 */
    private static AdminController adminController = new AdminController();
    /** 
     * The main Menu for Admin, it displays 5 options, some of them are creating/updating/removing movie listings and showings, configuring system settings.
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
    // Since this is private, its javadocs will be generated on choosing the Private option only. 
    /** 
     *
     * It prompts user to login as Admin with username and password.
     * @return boolean It returns true if user has been verified based on the username and password entered, else false.
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
