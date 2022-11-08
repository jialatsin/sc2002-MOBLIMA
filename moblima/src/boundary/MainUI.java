package boundary;

import control.MainController;

/**
 * It is a user interface for user to select if they are a MovieGoer or Admin.
 * @author OOP LAB Group 4
 * @version 8/11/2022
 */
public class MainUI {
    /** 
     * The main Menu of MOBLIMA displaying 3 options which are if the user is a movie-goer, admin or if he/she wishes to exit. 
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
