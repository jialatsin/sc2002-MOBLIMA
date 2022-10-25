package boundary;

import control.MovieSearchController;
import control.ReviewController;

public class MovieGoerUI {
    public static void main() {
        int selection;
        do {
            System.out.println("\n===== MOVIE GOER =====\n"
                    + "1. Search/List Movies\n"
                    + "2. View Movie Details\n"
                    + "3. Check Seat Availabilty\n"
                    + "4. Book and Purchase Ticket\n"
                    + "5. View Booking History\n"
                    + "6. List Top 5 Ranking Movies\n"
                    + "7. Add Review\n"
                    + "8. Return to Main Menu\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    SearchMovie();
                    break;
                case 2:
                    ViewDetails();
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
                    AddReview();
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

    public static void ViewDetails() {
        System.out.printf("Input movie title: ");
        String title = InputHandler.scanString();
        MovieSearchController.fullMovieDetails(title);
    }

    public static void AddReview() {
        System.out.printf("Input movie title to review: ");
        String title = InputHandler.scanString();
        ReviewController.addReview(title);
    }
}