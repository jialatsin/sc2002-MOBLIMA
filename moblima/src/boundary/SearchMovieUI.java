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
                    System.out.printf("Input movie title: ");
                    String title = InputHandler.scanString();
                    Movie movieObject = searchMovieObject(title);
                    if (movieObject == null)
                        break;
                    printMovieObject(movieObject);
                    break;

                case 2:
                    movieController.listAll();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong input");
            }
        } while (true);
    }

    public static Movie searchMovieObject(String movieTitle) {
        ArrayList<Movie> movieList = movieController.readFromDatabase();
        ArrayList<Movie> requestedMovieList = new ArrayList<Movie>();
        int movieFound = 0;
        for (Movie i : movieList) {
            if ((movieTitle.toLowerCase()).compareTo(i.getTitle().toLowerCase()) == 0) { // Searches for all movies with
                                                                                         // same name
                requestedMovieList.add(i);
                movieFound = 1;
            }
        }
        if (movieFound == 0) {
            System.out.println("Movie not found"); // Returns if no movie is found with given title
            return null;
        }

        else { // Prints all movie IDs for users to select from
            printMovieObject(requestedMovieList);

            System.out.print("Input movie ID from results above: ");
            int movieID = InputHandler.scanInt();
            for (Movie movieObject : requestedMovieList) {
                if (movieObject.getId() == movieID) { // search for movie using ID
                    return movieObject;
                }
            }
            System.out.println("Wrong ID inputted");
            return null;
        }
    }

    public static void printMovieObject(ArrayList<Movie> requestedMovieList) { // Print all movies given movieList
        for (Movie movie : requestedMovieList) {
            System.out.printf("\n================================================="
                    + "\nMovie: " + movie.getTitle()
                    + "\nMovie ID: " + movie.getId()
                    + "\nRelease Date: " + movie.getReleaseDate()
                    + "\nGenres: ");
            for (String k : movie.getGenres()) {
                System.out.printf(k + ", ");
            }
            System.out.print("\nShowing Status: " + movie.getShowingStatus());
        }
        System.out.println("\n=================================================\n");
    }

    public static void printMovieObject(Movie movie) { // Overloaded method to print out one given movie
        System.out.printf("\n================================================="
                + "\nMovie: " + movie.getTitle()
                + "\nMovie ID: " + movie.getId()
                + "\nRelease Date: " + movie.getReleaseDate()
                + "\nGenres: ");
        for (String k : movie.getGenres()) {
            System.out.printf(k + ", ");
        }
        System.out.println("\n"
                + "Showing Status: " + movie.getShowingStatus()
                + "\n=================================================");
    }

}
