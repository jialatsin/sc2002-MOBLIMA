package boundary;

import java.util.ArrayList;

import control.ShowingController;
import entity.Cineplex;
import entity.Movie;
import entity.Showing;
import entity.Constants.ShowingStatus;
import entity.Constants.User;

public class SearchShowingUI {
    private static ShowingController showingController = new ShowingController();

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
                case 1:
                    searchShowingById(user);
                    break;
                case 2:
                    searchShowingByCineplex(user);
                    break;
                case 3:
                    searchShowingByMovie(user);
                    break;
                case 4:
                    listAllShowings(user);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong input");
            }
        } while (true);
    }

    public static void searchShowingById(User user) {
        int id = UserHandler.getIdFromUser();
        Showing showing = showingController.findShowing(id);
        if (showing == null) {
            System.out.println("No showing with ID " + id + " found!");
            return;
        }
        // Moviegoer only can view showings with "Preview" or "Now Showing" status
        if (user.equals(User.MOVIEGOER) && !showing.getMovie().getShowingStatus().equals(ShowingStatus.NOW_SHOWING)
                && !showing.getMovie().getShowingStatus().equals(ShowingStatus.PREVIEW)) {
            System.out.println("Movie showing is not available for viewing");
            return;
        }
        System.out.println("=================================================");
        System.out.println(showing);
        System.out.println();
    }

    // Print all showings in database
    public static void listAllShowings(User user) {
        ArrayList<Showing> showings = showingController.readFromDatabase();
        if (showings.isEmpty()) {
            System.out.println("No showings exist in Showing database!");
            return;
        }
        System.out.println("=================================================");
        for (Showing showing : showings) {
            // Moviegoer only can view showings with "Preview" or "Now Showing" status
            if (user.equals(User.MOVIEGOER) && !showing.getMovie().getShowingStatus().equals(ShowingStatus.NOW_SHOWING)
                    && showing.getMovie().getShowingStatus().equals(ShowingStatus.PREVIEW)) {
                continue;
            }
            System.out.println(showing);
        }
        System.out.println();
    }

    public static void searchShowingByCineplex(User user) {
        Cineplex cineplex = UserHandler.getCineplexFromUser();
        ArrayList<Showing> showings = showingController.findShowings(cineplex);
        if (showings == null) {
            System.out.println("No showings are found at " + cineplex.getName() + "!");
            return;
        }
        for (Showing showing : showings) {
            if (user.equals(User.MOVIEGOER) && !showing.getMovie().getShowingStatus().equals(ShowingStatus.NOW_SHOWING)
                    && !showing.getMovie().getShowingStatus().equals(ShowingStatus.PREVIEW)) {
                System.out.println("Movie showing is not available for viewing");
                return;
            }
            System.out.println(showing);
        }
        System.out.println();
    }

    public static void searchShowingByMovie(User user) {
        Movie movie = UserHandler.getMovieByTitleFromUser();
        if (movie == null) {
            return;
        }
        ArrayList<Showing> showings = showingController.findShowings(movie);
        if (showings == null) {
            System.out.println("No showings of " + movie.getTitle() + " is found!");
            return;
        }
        for (Showing showing : showings) {
            if (user.equals(User.MOVIEGOER) && !showing.getMovie().getShowingStatus().equals(ShowingStatus.NOW_SHOWING)
                    && !showing.getMovie().getShowingStatus().equals(ShowingStatus.PREVIEW)) {
                System.out.println("Movie showing is not available for viewing");
                return;
            }
            System.out.println(showing);
        }
        System.out.println();
    }
}
