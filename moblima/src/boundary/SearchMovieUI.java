package boundary;

import java.util.ArrayList;

import control.MovieController;
import entity.Movie;

public class SearchMovieUI {
    private static MovieController movieController = new MovieController();

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

    // Prints all movies found in database with matching title
    public static void searchByTitle() {
        String title = UserHandler.getTitleFromUser();
        ArrayList<Movie> movies = movieController.findMovies(title);
        if (movies == null) {
            System.out.println("Movie not found in Movie database!");
            return;
        }
        for (Movie m : movies) {
            System.out.println(m);
        }
    }

    // Prints all movies in database
    public static void listAllMovies() {
        ArrayList<Movie> movies = movieController.readFromDatabase();
        if (movies.isEmpty()) {
            System.out.println("No movies exist in Movie database!");
            return;
        }
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}
