package boundary;

import control.BookingController;
import control.MovieController;

import java.util.*;

import entity.*;
import entity.Constants.User;

public class MovieGoerUI {
    private static MovieController movieController = new MovieController();
    private static BookingController bookingController = new BookingController();

    public static void main() {
        int selection;

        do {
            System.out.println("\n===== MOVIE GOER =====\n"
                    + "1. Search/List Movies\n"
                    + "2. View Movie Details\n"
                    + "3. Search/List Showings\n"
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
                    viewMovieDetails(); // returns all movies with same title in them
                    break;
                case 3: // Search/List Showings
                    SearchShowingUI.main(User.MOVIEGOER);
                    break;
                case 4: // Book Ticket
                    BookingUI.main();
                    break;
                case 5: // View Booking History
                    viewBookingHistory();
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

    // Prints full movie details of a movie
    public static void viewMovieDetails() {
        Movie movie = UserHandler.getMovieByTitleFromUser();
        if (movie == null)
            return;

        System.out.print("\n=================================================");
        System.out.println(
                "\nMovie: " + movie.getTitle()
                        + "\nID: " + movie.getId()
                        + "\nSynopsis: " + movie.getSynopsis()
                        + "\nDirector: " + movie.getDirector()
                        + "\nCast: ");
        for (String castMember : movie.getCast())
            System.out.print(castMember + ", ");
        System.out.print("\nGenres: ");
        for (String genre : movie.getGenres())
            System.out.print(genre + ", ");
        System.out.println("\nRelease Date: " + movie.getReleaseDate()
                + "\nEnd Date: " + movie.getEndDate()
                + "\nContent Rating: " + movie.getContentRating()
                + "\nMovie Type: " + movie.getMovieType()
                + "\nShowing Status: " + movie.getShowingStatus());

        double averageReviewRating = movie.getAverageReviewRating();
        if (averageReviewRating < 0) {
            System.out.println("\nAverage Review Rating: NaN"); // No reviews available
        } else
            System.out.println("\nAverage Review Rating: " + movie.getAverageReviewRating() + "*");

        // Print reviews
        ArrayList<Review> reviews = movie.getReviews();
        System.out.println("Reviews: ");
        if (reviews.isEmpty()) {
            System.out.println("No reviews found!");
        } else {
            int i = 1;
            for (Review review : reviews) {
                System.out.println(i++ + ": " + review);
            }
        }
        System.out.println("=================================================");
    }

    public static void addReview() {
        Movie movie = UserHandler.getMovieByTitleFromUser();
        if (movie == null)
            return;

        // Prompt user to review movie
        System.out.printf("Rate " + movie.getTitle() + " from 1-5: ");
        int rating = InputHandler.scanInt();
        System.out.println("Input your reviews for " + movie.getTitle() + ": ");
        String reviewText = InputHandler.scanString();

        Review newReview = new Review(rating, reviewText);
        movieController.addReviewToMovie(movie, newReview);
        System.out.println("Added review to " + movie.getTitle() + "!");
    }

    public static void viewBookingHistory() {
        System.out.println("\nEnter email to view booking history for:");
        String email = InputHandler.scanEmail();

        ArrayList<Booking> bookings = bookingController.findBookings(email);
        if (bookings == null) {
            System.out.println("No bookings exist in Booking database!");
            return;
        }
        System.out.println("=================================================");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
        System.out.println();
    }
}