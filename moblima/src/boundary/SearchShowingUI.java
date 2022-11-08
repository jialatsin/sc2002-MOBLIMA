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
 * Represents the user interface to search for showings, used by both admin and
 * moviegoer.
 * An admin can see all the showings available in the showing database,
 * but a moviegoer can only see showings of movies that are currently in
 * "Preview" or "Now Showing" and with showtimes that have not passed the
 * current datetime.
 */
public class SearchShowingUI {
    /** The showing controller to be referenced. */
    private static ShowingController showingController = new ShowingController();

    /**
     * Main menu for searching showings. Displays 5 options:
     * Search By Showing ID, Search By Cineplex, Search By Movie Title, List All
     * Showings, Return.
     * 
     * @param user The user (admin/moviegoer) that calls this method.
     */
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
     * Prompts user for a showing id and prints the showing found in the showing
     * database with matching id.
     * A moviegoer can only see the showing if it is "Preview" or "Now Showing" and
     * with showtime that have not passed the current datetime, but an admin can see
     * the showing irregardless of its showing status and showtime if it exists in
     * the database.
     * 
     * @param user The user (admin/moviegoer) that calls this method.
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
     * Prints a list of all the showings in the showing database.
     * An admin can see all the showings available in the showing database,
     * but a moviegoer can only see showings of movies that are currently in
     * "Preview" or "Now Showing" and with showtimes that have not passed the
     * current datetime.
     * 
     * @param user The user (admin/moviegoer) that calls this method.
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
     * Prompts user for a cineplex and prints a list of all showings found in the
     * showing database with matching cineplex.
     * A moviegoer can only see the showing if it is "Preview" or "Now Showing" and
     * with showtime that have not passed the current datetime,
     * but an admin can see the showing irregardless of its showing status and
     * showtime if it exists in the database.
     * 
     * @param user The user (admin/moviegoer) that calls this method.
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
     * Prompts user for a movie and prints a list of all showings found in the
     * showing database with matching movie.
     * A moviegoer can only see the showing if it is "Preview" or "Now Showing" and
     * with showtime that have not passed the current datetime,
     * but an admin can see the showing irregardless of its showing status and
     * showtime if it exists in the database.
     * 
     * @param user The user (admin/moviegoer) that calls this method.
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
