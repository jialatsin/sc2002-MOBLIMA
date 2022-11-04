package boundary;

import java.util.ArrayList;

import control.MovieController;
import entity.Movie;

/** User interface to list the top Movies, used by both Admin and MovieGoer. */
public class ListTopMovies {
    private static MovieController movieController = new MovieController();

    /** Menu for List Top Movies. */
    public static void main() {
        int selection;
        do {
            System.out.println("\n===== LIST TOP MOVIES =====\n"
                    + "1. List Top 5 By Ticket Sales\n"
                    + "2. List Top 5 By Overall Review Rating\n"
                    + "0. Logout to Main Menu\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1: // List Top 5 By Ticket Sales
                    listTopMoviesBySales(5);
                    break;
                case 2: // List Top 5 By Overall Review Rating
                    listTopMoviesByRating(5);
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    /**
     * Sorts and prints the top movies in Movie database by their
     * averageReviewRating, with the cutoff determined by movieCount.
     * 
     * @param movieCount The cutoff number to be ranked (eg. movieCount = 5 for Top
     *                   5 Movies)
     */
    private static void listTopMoviesByRating(int movieCount) {
        ArrayList<Movie> movies = movieController.readFromDatabase();
        if (movies.isEmpty()) {
            System.out.println("No movies exist in Movie database!");
            return;
        }

        movies = movieController.sortByRating(movies);

        System.out.println("=================================================");
        // Print all movies in database if there are less movies than the ranking cutoff
        if (movies.size() < movieCount) {
            for (Movie movie : movies)
                System.out.println(movie);
        }
        // Print top movies from the ranking cutoff
        else {
            for (int i = 0; i < movieCount; i++) {
                System.out.println(movies.get(i));
            }
        }
        System.out.println();
    }

    /**
     * Sorts and prints the top movies in Movie database by their
     * ticketSales, with the cutoff determined by movieCount.
     * 
     * @param movieCount The cutoff number to be ranked (eg. movieCount = 5 for Top
     *                   5 Movies)
     */
    private static void listTopMoviesBySales(int movieCount) {
        ArrayList<Movie> movies = movieController.readFromDatabase();
        if (movies.isEmpty()) {
            System.out.println("No movies exist in Movie database!");
            return;
        }

        movies = movieController.sortByTicketSales(movies);

        System.out.println("=================================================");
        // Print all movies in database if there are less movies than the ranking cutoff
        if (movies.size() < movieCount) {
            for (Movie movie : movies)
                System.out.println(movie);
        }
        // Print top movies from the ranking cutoff
        else {
            for (int i = 0; i < movieCount; i++) {
                System.out.println(movies.get(i));
            }
        }
        System.out.println();
    }

}
