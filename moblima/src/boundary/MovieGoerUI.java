package boundary;

import control.MovieSearchController;

public class MovieGoerUI {
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

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    SearchMovie();
                    break;
                case 2:
                    viewDetails();
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

    public static void SearchMovie() {
        do {
            System.out.println("===== SEARCH/LIST MOVIE =====");
            System.out.println("1. Search By Title");
            System.out.println("2. List All Movies");
            System.out.println("3. Return");
            int choice = InputHandler.scanInt();
            switch (choice) {
                case 1:
                    System.out.printf("Input movie title: ");
                    String title = InputHandler.scanString();
                    MovieSearchController.searchTitle(title);
                    break;
                case 2:
                    MovieSearchController.listAll();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Wrong input");
            }
        } while (true);
    }

    public static void viewDetails() {
        System.out.printf("Input movie title: ");
        String title = InputHandler.scanString();
        MovieSearchController.fullMovieDetails(title);
    }
}