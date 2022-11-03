package boundary;

import java.util.ArrayList;

import control.MovieController;
import entity.Movie;

public class ListTopMovies {
    private static MovieController movieController = new MovieController();

    public static void main() {
        int selection;
        do {
            System.out.println("\n===== LIST TOP MOVIES =====\n"
                    + "1. List Top 5 By Ticket Sales\n"
                    + "2. List Top 5 By Overall Review Rating\n"
                    + "0. Logout to Main Menu\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    listTopMoviesBySales(5);
                    break;
                case 2:
                    listTopMoviesByRating(5);
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

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
