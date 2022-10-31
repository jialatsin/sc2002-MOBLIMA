package boundary;

import control.MovieController;
import control.ShowingController;

import java.util.*;

import entity.*;

public class MovieGoerUI {
    private static MovieController movieController = new MovieController();
    private static ShowingController showingController = new ShowingController();

    public static void main() {
        int selection;

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
                    SearchMovieUI.main();
                    break;
                case 2: // View Movie Details
                    viewDetails(); // returns all movies with same title in them
                    break;
                case 3: // TODO: Search showings instead of check seat availability?
                    break;
                case 4: // Book Ticket
                    break;
                case 5: // View Booking History
                    break;
                case 6: // List Top 5
                    // Require Read from Database
                    break;
                case 7: // Add Review
                    addReview();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    public static void viewDetails() { // prints all details of a movie
        System.out.print("Input movie title: ");
        String movieTitle = InputHandler.scanString();
        Movie movie = SearchMovieUI.searchMovieObject(movieTitle);
        if (movie == null)
            return;

        System.out.println("\n=================================================");
        System.out.println(
                "\nMovie: " + movie.getTitle()
                        + "\nid: " + movie.getId()
                        + "\nSynopsis: " + movie.getSynopsis()
                        + "\nDirector: " + movie.getDirector()
                        + "\nCast: ");
        for (String j : movie.getCast())
            System.out.print(j + ", ");
        System.out.print("\nGenres: ");
        for (String genre : movie.getGenres())
            System.out.print(genre + ", ");
        System.out.println("\nRelease Date: " + movie.getReleaseDate()
                + "\nContent Rating: " + movie.getContentRating()
                + "\nMovie Type: " + movie.getMovieType()
                + "\nShowing Status: " + movie.getShowingStatus());
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

        System.out.println("=================================================");
    }

    public static void searchShowing() {
        Cineplex cineplex = UserHandler.getCineplexFromUser();
        System.out.printf("Input movie title: ");
        String title = InputHandler.scanString();
        Movie movie = SearchMovieUI.searchMovieObject(title);
        if (movie == null) {
            System.out.println("Movie does not exist in Movie database!");
            return;
        }
        // TODO:
        SearchShowingUI.listAll(cineplex, movie); // List all showings for user to pick one

        System.out.println("Input movie showing id:");
        int showingId = InputHandler.scanInt();
        Showing showing = showingController.getShowingById(showingId);
    }

    public static void checkSeatAvailability(Showing showing) {
        if (showing == null) {
            System.out.println("Movie showing does not exist in Showing database!");
            return;
        }
        SeatingLayout seatingAvailability = showing.getSeatingAvailablity();
        System.out.println(seatingAvailability);
    }

    public static void bookTicket(Movie movie) {
        // TODO
    }

    // TODO: Refactor into movie controller
    public static void addReview() {
        System.out.printf("Input movie title: ");
        String movieTitle = InputHandler.scanString();
        Movie movie = SearchMovieUI.searchMovieObject(movieTitle);
        if (movie == null)
            return;

        System.out.printf("Rate " + movie.getTitle() + " from 1-5: ");
        int rating = InputHandler.scanInt();
        System.out.println("Input your reviews for " + movie.getTitle() + ": ");
        String userReview = InputHandler.scanString();
        Review newReview = new Review(rating, userReview); // create new review

        ArrayList<Review> reviewList = movie.getReviews(); // retrieve exisiting reviewList
        reviewList.add(newReview);
        movie.setReviews(reviewList); // update reviewList
        movie.setAverageReviewRating();

        boolean updated = movieController.updateMovieObject(movie);
        if (updated == true) {
            System.out.println("Review added");
        } else
            System.out.println("False");
    }
}