package boundary;

public class AdminUI extends UserInterface {
    public static void main() {
        // TODO: Handle Login
        System.out.println("===== ADMIN LOGIN =====");

        int selection;
        do {
            System.out.println("===== ADMIN =====");
            System.out.println("1. Create/Update/Remove Movie Listing");
            System.out.println("2. Create/Update/Remove Movie Showings");
            System.out.println("3. Configure System Settings");
            System.out.println("4. Return to Main Menu");

            selection = scanInt();
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
