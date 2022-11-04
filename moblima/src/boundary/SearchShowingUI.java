package boundary;

import java.time.LocalDateTime;
import java.util.ArrayList;

import control.ShowingController;
import entity.Cineplex;
import entity.Movie;
import entity.Showing;
import entity.Enumerators.ShowingStatus;
import entity.Enumerators.User;

/**
 * User interface to search for Showings, used by both Admin and MovieGoer.
 * An Admin can see all the Showings available in the Showing database,
 * but a MovieGoer can only see Showings of Movies that are currently in
 * "Preview" or "Now Showing" and with showtimes that have not passed the
 * current datetime.
 */
public class SearchShowingUI {
    private static ShowingController showingController = new ShowingController();

    /** Menu for Search/List Showings. */
    public static void main(User user) {
        do {
            System.out.println("===== SEARCH/LIST SHOWING =====\n"
                    + "1. Search By Showing ID\n"
                    + "2. Search By Cineplex\n"
                    + "3. Search By Movie Title\n"
                    + "4. List All Showings\n"
                    + "0. Return\n");
            int choice = InputHandler.scanInt();
            switch (choice) {
                case 1: // Search by Showing ID
                    searchShowingById(user);
                    break;
                case 2: // Search by Cineplex
                    searchShowingByCineplex(user);
                    break;
                case 3: // Search by Movie Title
                    searchShowingByMovie(user);
                    break;
                case 4: // List All Showings
                    listAllShowings(user);
                    break;
                case 0: // Return
                    return;
                default:
                    System.out.println("Wrong input");
            }
        } while (true);
    }

    /**
     * Prompts user for a Showing ID and prints the showing found in Showing
     * database with matching ID.
     * A MovieGoer can only see the Showing if it is "Preview" or "Now Showing" and
     * with showtime that have not passed the current datetime,
     * but an Admin can see the Showing irregardless of ShowingStatus and showTime
     * if it exists in the database.
     * 
     * @param user User (Admin/MovieGoer) that calls this method
     */
    public static void searchShowingById(User user) {
        int id = UserHandler.getIdFromUser();
        Showing showing = showingController.findShowing(id);
        if (showing == null) {
            System.out.println("No showing with ID " + id + " found!");
            return;
        }
        // Moviegoer only can view showings with "Preview" or "Now Showing" status and
        // showtime have not already passed current time
        if (user.equals(User.MOVIEGOER)
                && (!showing.getMovie().getShowingStatus().equals(ShowingStatus.NOW_SHOWING)
                        && !showing.getMovie().getShowingStatus().equals(ShowingStatus.PREVIEW)
                        || showing.getShowTime().isBefore(LocalDateTime.now()))) {
            System.out.println("Movie showing is currently not available for viewing!");
            return;
        }
        System.out.println("=================================================");
        System.out.println(showing);
        System.out.println();
    }

    /**
     * Prints a list of all the Showings in the Showing database.
     * An Admin can see all the Showings available in the Showing database,
     * but a MovieGoer can only see Showings of Movies that are currently in
     * "Preview" or "Now Showing" and with showtimes that have not passed the
     * current datetime.
     * 
     * @param user User (Admin/MovieGoer) that calls this method
     */
    public static void listAllShowings(User user) {
        ArrayList<Showing> showings = showingController.readFromDatabase();
        if (showings.isEmpty()) {
            System.out.println("No showings exist in Showing database!");
            return;
        }

        ArrayList<Showing> showingsResult = new ArrayList<Showing>();
        for (Showing showing : showings) {
            // Moviegoer only can view showings with "Preview" or "Now Showing" status and
            // showtime have not already passed current time
            if (user.equals(User.MOVIEGOER)
                    && (!showing.getMovie().getShowingStatus().equals(ShowingStatus.NOW_SHOWING)
                            && !showing.getMovie().getShowingStatus().equals(ShowingStatus.PREVIEW)
                            || showing.getShowTime().isBefore(LocalDateTime.now()))) {
                continue;
            }
            showingsResult.add(showing);
        }

        if (showingsResult.isEmpty()) {
            System.out.println("\nNo showings are currently available!");
            return;
        }

        System.out.println("=================================================");
        for (Showing showing : showingsResult) {
            System.out.println(showing);
        }
        System.out.println();
    }

    /**
     * Prompts user for a Cineplex and prints a list of all showings found in
     * Showing database with matching Cineplex.
     * A MovieGoer can only see the Showing if it is "Preview" or "Now Showing" and
     * with showtime that have not passed the current datetime,
     * but an Admin can see the Showing irregardless of ShowingStatus and showTime
     * if it exists in the database.
     * 
     * @param user User (Admin/MovieGoer) that calls this method
     */
    public static void searchShowingByCineplex(User user) {
        Cineplex cineplex = UserHandler.getCineplexFromUser();
        ArrayList<Showing> showings = showingController.findShowings(cineplex);
        if (showings == null) {
            System.out.println("No showings are currently available at " + cineplex.getName() + "!");
            return;
        }

        ArrayList<Showing> showingsResult = new ArrayList<Showing>();
        for (Showing showing : showings) {
            // Moviegoer only can view showings with "Preview" or "Now Showing" status and
            // showtime have not already passed current time
            if (user.equals(User.MOVIEGOER)
                    && (!showing.getMovie().getShowingStatus().equals(ShowingStatus.NOW_SHOWING)
                            && !showing.getMovie().getShowingStatus().equals(ShowingStatus.PREVIEW)
                            || showing.getShowTime().isBefore(LocalDateTime.now()))) {
                continue;
            }
            showingsResult.add(showing);
        }

        if (showingsResult.isEmpty()) {
            System.out.println("No showings are currently available at " + cineplex.getName() + "!");
            return;
        }

        System.out.println("=================================================");
        for (Showing showing : showingsResult) {
            System.out.println(showing);
        }
        System.out.println();
    }

    /**
     * Prompts user for a Movie and prints a list of all showings found in
     * Showing database with matching Movie.
     * A MovieGoer can only see the Showing if it is "Preview" or "Now Showing" and
     * with showtime that have not passed the current datetime,
     * but an Admin can see the Showing irregardless of ShowingStatus and showTime
     * if it exists in the database.
     * 
     * @param user User (Admin/MovieGoer) that calls this method
     */
    public static void searchShowingByMovie(User user) {
        Movie movie = UserHandler.getMovieByTitleFromUser();
        if (movie == null) {
            return;
        }

        ArrayList<Showing> showings = showingController.findShowings(movie);
        if (showings == null) {
            System.out.println("No showings of " + movie.getTitle() + " are currently available!");
            return;
        }

        ArrayList<Showing> showingsResult = new ArrayList<Showing>();
        for (Showing showing : showings) {
            // Moviegoer only can view showings with "Preview" or "Now Showing" status and
            // showtime have not already passed current time
            if (user.equals(User.MOVIEGOER)
                    && (!showing.getMovie().getShowingStatus().equals(ShowingStatus.NOW_SHOWING)
                            && !showing.getMovie().getShowingStatus().equals(ShowingStatus.PREVIEW)
                            || showing.getShowTime().isBefore(LocalDateTime.now()))) {
                continue;
            }
            showingsResult.add(showing);
        }

        if (showingsResult.isEmpty()) {
            System.out.println("No showings of " + movie.getTitle() + " are currently available!");
            return;
        }

        System.out.println("=================================================");
        for (Showing showing : showingsResult) {
            System.out.println(showing);
        }
        System.out.println();
    }
}
