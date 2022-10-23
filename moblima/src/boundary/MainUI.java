package boundary;

import control.MainController;

public class MainUI extends UserInterface {
    public static void main() {
        MainController.start();

        int selection;
        do {
            System.out.println("===== MAIN MENU =====");
            System.out.println("1. Movie Goer");
            System.out.println("2. Admin");
            System.out.println("3. Exit");

            selection = scanInt();
            switch (selection) {
                case 1:
                    MovieGoerUI.main();
                    break;
                case 2:
                    AdminUI.main();
                    break;
                case 3:
                    return;
            }
        } while (true);
    }
}
