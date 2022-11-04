package boundary;

import control.MainController;

/* Main menu user interface for user to select if they are a MovieGoer or Admin */
public class MainUI {
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
