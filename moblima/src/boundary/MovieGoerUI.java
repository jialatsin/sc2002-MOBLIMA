package boundary;

public class MovieGoerUI extends UserInterface {
    public static void main() {
        int selection;
        do {
            System.out.println("===== MOVIE GOER =====");
            System.out.println("1. Search/List Movie");
            System.out.println("2. View Movie Details");
            System.out.println("3. Check Seat Availabilty");
            System.out.println("4. Book and Purchase Ticket");
            System.out.println("5. View Booking History");
            System.out.println("6. List Top 5 Ranking Movies");
            System.out.println("7. Add Review");
            System.out.println("8. Return to Main Menu");

            selection = scanInt();
            switch (selection) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    return;
            }
        } while (true);
    }
}