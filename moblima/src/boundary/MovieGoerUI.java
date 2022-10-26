package boundary;

import control.MovieController;
import java.util.*;

import entity.Movie;
import entity.Review;
import boundary.SeatsUI;

public class MovieGoerUI {
    private static MovieController movieController = new MovieController();

    public static void main() {
        int selection;
        String movieTitle;
        Movie movieObject;
        boolean updated;
        do {
            System.out.println("\n===== MOVIE GOER =====\n"
                    + "1. Search/List Movies\n"
                    + "2. View Movie Details\n"
                    + "3. Check Seat Availabilty\n"
                    + "4. Book and Purchase Ticket\n"
                    + "5. View Booking History\n"
                    + "6. List Top 5 Ranking Movies\n"
                    + "7. Add Review\n"
                    + "0. Return to Main Menu\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1: // Search/List Movies
                    searchMovie();
                    break;
                case 2: // View Movie Details
                    System.out.printf("Input movie title: ");
                    movieTitle = InputHandler.scanString();
                    movieObject = searchMovieObject(movieTitle);
                    if (movieObject == null)
                        break;
                    viewDetails(movieObject);
                    break;
                case 3: // Check Seat Availabilty
                    SeatsUI.main();
                    break;
                case 4: // Book Ticket
                    System.out.printf("Input movie title: ");
                    String title = InputHandler.scanString();
                    movieObject = searchMovieObject(title);
                    if (movieObject == null) // checks if movie exists
                        break;
                    bookTicket(movieObject); // TODO
                    break;
                case 5: // View Booking History
                    break;
                case 6: // List Top 5
                    //Require Read from Database
                    break;
                case 7: // Add Review
                    System.out.printf("Input movie title: ");
                    movieTitle = InputHandler.scanString();
                    movieObject = searchMovieObject(movieTitle);
                    if (movieObject == null)
                        break;
                    Movie newMovie = addReview(movieObject);
                    updated = updateMovieObject(newMovie);
                    if (updated == true) {
                        System.out.println("Review added");
                    } else
                        System.out.println("False");
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    public static void searchMovie() { // another UI that is pretty unnecessary but we'll keep it to prevent nested
                                       // switchcases
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
                    Movie mov = searchMovieObject(title);
                    if (mov == null)
                        break;
                    searchTitle(mov);
                    break;
                case 2:
                    listAll();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong input");
            }
        } while (true);
    }

    public static Movie searchMovieObject(String movieTitle) { // searches and returns requested movie object
        ArrayList<Movie> MovieList = movieController.readFromDatabase();
        for (Movie i : MovieList) {
            if ((movieTitle.toLowerCase()).compareTo(i.getTitle().toLowerCase()) == 0) {
                return i;
            }
        }
        System.out.println("Movie not found");
        return null;

    }

    // TODO: Use ID instead for handling data. There may be more than 1 movie with
    // the same name!
    public static boolean updateMovieObject(Movie updatedMovie) { // method that overwrites database everytime a movie
                                                                  // has an update
        ArrayList<Movie> MovieList = movieController.readFromDatabase();
        for (int i = 0; i < MovieList.size(); i++) {
            if ((updatedMovie.getTitle().toLowerCase()).compareTo(MovieList.get(i).getTitle().toLowerCase()) == 0) {
                MovieList.set(i, updatedMovie);
                movieController.overwriteDatabase(MovieList);
                return true;
            }
        }
        return false;
    }

    public static void searchTitle(Movie movie) { // Method from SeachMovie() to find movie based on title
        System.out.printf("\n================================================="
                + "\nMovie: " + movie.getTitle()
                + "\nRelease Date: " + movie.getReleaseDate()
                + "\nGenres: ");
        for (String k : movie.getGenres()) {
            System.out.printf(k + ", ");
        }
        System.out.printf("\n"
                + "Showing Status: " + movie.getShowingStatus()
                + "\n=================================================\n");
    }

    public static void listAll() { // Method from SeachMovie() to list all movies in db
        ArrayList<Movie> MovieList = movieController.readFromDatabase();
        System.out.println();
        for (Movie movie : MovieList) {
            System.out.printf("\n================================================="
                    + "\nMovie: " + movie.getTitle()
                    + "\nRelease Date: " + movie.getReleaseDate()
                    + "\nGenres: ");
            for (String k : movie.getGenres())
                System.out.printf(k + ", ");
            System.out.printf("\n"
                    + "Showing Status: " + movie.getShowingStatus());
        }
        System.out.println("\n=================================================\n");
    }

    public static void viewDetails(Movie movie) { // prints all details of a movie
        System.out.println("\n================================================="
                + "\nMovie: " + movie.getTitle()
                + "\nid: " + movie.getId()
                + "\nSynopsis: " + movie.getSynopsis()
                + "\nDirector: " + movie.getDirector()
                + "\nCast: ");
        for (String j : movie.getCast())
            System.out.print(j + ", ");
        System.out.print("\n"
                + "\nGenres: ");
        for (String genre : movie.getGenres())
            System.out.print(genre + ", ");
        System.out.println("\n"
                + "\nRelease Date: " + movie.getReleaseDate()
                + "\nContent Rating: " + movie.getContentRating()
                + "\nMovie Type: " + movie.getMovieType()
                + "\nShowing Status: " + movie.getShowingStatus()
                + "\n"
                + "\nAverage Review Rating: " + movie.getAverageReviewRating() + "*");
        // print reviews
        int numReviews = movie.getReviews().size(), reviewNum = 1;
        if (numReviews > 0)
            System.out.println("Reviews: ");
        ArrayList<Review> reviewList = movie.getReviews();
        for (Review review : reviewList) {
            System.out.println(reviewNum + ": " + review);
            reviewNum++;
        }

        System.out.println("=================================================\n");
    }

    public static void bookTicket(Movie movie) {
        // TODO: generate showings
    }

    public static Movie addReview(Movie movie) {
        System.out.printf("Rate " + movie.getTitle() + " from 1-5: ");
        int rating = InputHandler.scanInt();
        System.out.println("Input your reviews for " + movie.getTitle() + ": ");
        String userReview = InputHandler.scanString();
        Review newReview = new Review(rating, userReview); // create new review

        ArrayList<Review> reviewList = movie.getReviews(); // retrieve exisiting reviewList
        reviewList.add(newReview);
        movie.setReviews(reviewList); // update reviewList
        movie.setAverageReviewRating();
        return movie;
    }
}