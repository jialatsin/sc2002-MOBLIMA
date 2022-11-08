package boundary;

import control.MainController;

/**
 * Represents the main menu user interface for users to select if they are a
 * moviegoer or admin.
 */
public class MainUI {
    /**
     * Main menu of MOBLIMA that for users to access the application
     * with. Displays 3 options: Movie Goer, Admin, Exit.
     */
    public static void main() {
        MainController.initializeDatabase();

        int selection;
        do {
            System.out.println("\n===== MAIN MENU =====\n"
                    + "1. Movie Goer\n"
                    + "2. Admin\n"
                    + "0. Exit\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1: // Movie Goer
                    MovieGoerUI.main();
                    break;
                case 2: // Admin
                    AdminUI.main();
                    break;
                case 0: // Exit
                    return;
            }
        } while (true);
    }
}
