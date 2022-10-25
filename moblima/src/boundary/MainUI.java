package boundary;

import control.MainController;

public class MainUI {
    public static void main() {
        MainController.start();

        int selection;
        do {
            System.out.println("\n===== MAIN MENU =====\n"
                    + "1. Movie Goer\n"
                    + "2. Admin\n"
                    + "0. Exit\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    MovieGoerUI.main();
                    break;
                case 2:
                    AdminUI.main();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }
}
