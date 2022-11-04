package boundary;

import java.util.ArrayList;

import control.MovieController;
import entity.Movie;

/** User interface to search for Movies, used by both Admin and MovieGoer. */
public class SearchMovieUI {
    private static MovieController movieController = new MovieController();

    /** Menu for Search/List Movies. */
    public static void main() {
        do {
            System.out.println("===== SEARCH/LIST MOVIE =====\n"
                    + "1. Search By Title\n"
                    + "2. List All Movies\n"
                    + "0. Return\n");
            int choice = InputHandler.scanInt();
            switch (choice) {
                case 1:
                    searchByTitle();
                    break;
                case 2:
                    listAllMovies();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong input");
            }
        } while (true);
    }

    /**
     * Prompts user for a movie title and prints a list of all movies found in Movie
     * database with matching title.
     */
    public static void searchByTitle() {
        String title = UserHandler.getTitleFromUser();
        ArrayList<Movie> movies = movieController.findMovies(title);
        if (movies == null) {
            System.out.println("Movie not found in Movie database!");
            return;
        }
        System.out.println("=================================================");
        for (Movie m : movies) {
            System.out.println(m);
        }
        System.out.println("\n");
    }

    /** Prints a list of all the movies in the Movie database. */
    public static void listAllMovies() {
        ArrayList<Movie> movies = movieController.readFromDatabase();
        if (movies.isEmpty()) {
            System.out.println("No movies exist in Movie database!");
            return;
        }
        System.out.println("=================================================");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        System.out.println("\n");
    }
}
