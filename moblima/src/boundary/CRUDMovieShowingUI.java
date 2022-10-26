package boundary;

import java.time.LocalDate;

import java.util.*;
import control.ShowingController;
import entity.*;
import entity.Constants.CinemaClass;

public class CRUDMovieShowingUI {
    private static ShowingController showingController = new ShowingController();

    public static void main() {
        int selection;
        do {
            System.out.println("\n===== MOVIE SHOWING =====\n"
                    + "1. Create Movie Showing\n"
                    + "2. Update Movie Showing\n"
                    + "3. Remove Movie Showing\n"
                    + "4. Search/List Showings\n"
                    + "0. Return to Admin Menu\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    createMovieShowing();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4: // List all Showings
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    private static void createMovieShowing() {
        int n; // Temporary counter
        System.out.println("\nCREATING A MOVIE SHOWING...");

        System.out.println("\nEnter showing ID:");
        int id = InputHandler.scanInt();
        
        SeatingLayout seatingLayout = new SeatingLayout(10, 10);

        System.out.println("\nEnter movie title:");
        String movieTitle = InputHandler.scanString();
        Movie movie = MovieGoerUI.searchMovieObject(movieTitle);

        System.out.println("\nEnter show timing:");
        LocalDate showTime = InputHandler.scanDate();

        System.out.println("\n Enter cinema code:");
        String cinemaCode = InputHandler.scanString();
        System.out.println("Enter cinema class:");
        CinemaClass cinemaClass = null;
        do {
            System.out.println("\nSelect cinema class:\n"
                    + "1. Platinum Class\n"
                    + "2. Gold Class\n"
                    + "3. Standard\n"
                    );

            n = InputHandler.scanInt();
            switch (n) {
                case 1:
                    cinemaClass = CinemaClass.PLATINUM;
                    break;
                case 2:
                    cinemaClass = CinemaClass.GOLD;
                    break;
                case 3:
                    cinemaClass = CinemaClass.STANDARD;
                    break;
            }
        } while (n < 1 || n > 3);
        // Cinema cinema = new Cinema(cinemaCode, seatingLayout, cinemaClass);

        // System.out.println("\nEnter cineplex name:");
        // String cineplexName = InputHandler.scanString();
        // System.out.println("\nEnter cineplex location:");
        // String cineplexLocation = InputHandler.scanString();
        // System.out.println("\nEnter cineplex Vendor:");
        // String cineplexVendor = InputHandler.scanString();
        // Cineplex cineplex = new Cineplex(cineplexName, cineplexLocation, cineplexVendor, null)




        // ShowingController.addToDatabase(
        //         new Showing(id, seatingLayout, movie, showTime, cinema, cineplex)));

        System.out.println(movie.getTitle() + "at showtime" + id + ") added to Showtime database!");
    }
}
