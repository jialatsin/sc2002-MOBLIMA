package boundary;

import control.MovieController;
import java.util.*;

import entity.Movie;
import entity.Review;
import boundary.SeatsUI;

public class MovieGoerUI {
    private static MovieController movieController = new MovieController();

    public static void main() {
        int selection, movieID;
        String movieTitle;
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
                    System.out.print("Input movie title: ");
                    ArrayList<Movie> requestedMovieList = new ArrayList<Movie>();
                    movieTitle = InputHandler.scanString();
                    Movie movieObject = searchMovieObject(movieTitle); // returns all movies with same title in them
                    break;
                case 3: // Check Seat Availabilty
                    SeatsUI.main();
                    break;
                case 4: // Book Ticket
                    // System.out.printf("Input movie title: ");
                    // String title = InputHandler.scanString();
                    // movieObject = searchMovieObjects(title);
                    // if (movieObject == null) // checks if movie exists
                    // break;
                    // bookTicket(movieObject); // TODO
                    // break;
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
                    Movie newMovie = addReview(movieObject);  //creates new movie block to update reviews
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
                    Movie movieObject = searchMovieObject(title);
                    if (movieObject == null)
                        break;
                    printMovieObject(movieObject);
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
                    viewDetails(movieObject);
                    return movieObject;
                }
            }
            System.out.println("Wrong ID inputted");
            return null;
        }
    }

    public static void printMovieObject(ArrayList<Movie> requestedMovieList) {  //Print all movies given movieList
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

    public static void printMovieObject(Movie movie) { //Overloaded method to print out one given movie
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

    public static boolean updateMovieObject(Movie updatedMovie) { // method that overwrites database everytime a movie
                                                                  // has an update
        ArrayList<Movie> MovieList = movieController.readFromDatabase();
        for (int i = 0; i < MovieList.size(); i++) {
            if (updatedMovie.getId() == MovieList.get(i).getId()) {
                MovieList.set(i, updatedMovie);
                movieController.overwriteDatabase(MovieList);
                return true;
            }
        }
        return false;
    }

    public static void listAll() { // Method from SeachMovie() to list all movies in db
        ArrayList<Movie> MovieList = movieController.readFromDatabase();
        System.out.println();
        printMovieObject(MovieList);
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
                + "\n");
        double averageReviewRating = movie.getAverageReviewRating();
        if (averageReviewRating < 0) {
            System.out.println("\nAverage Review Rating: NaN");
        } else
        System.out.println("\nAverage Review Rating: " + movie.getAverageReviewRating() + "*");
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