package boundary;

import control.MovieController;
import java.util.*;
import entity.Movie;
import entity.Review;

public class MovieGoerUI {
    public static void main() {
        int selection;
        String movieTitle;
        Movie mov;
        do {
            System.out.println("\n===== MOVIE GOER =====\n"
                    + "1. Search/List Movies\n"
                    + "2. View Movie Details\n"
                    + "3. Check Seat Availabilty\n"
                    + "4. Book and Purchase Ticket\n"
                    + "5. View Booking History\n"
                    + "6. List Top 5 Ranking Movies\n"
                    + "7. Add Review\n"
                    + "8. Return to Main Menu\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    SearchMovie();
                    break;
                case 2:
                    System.out.printf("Input movie title: ");
                    movieTitle = InputHandler.scanString();
                    mov = SearchMovieObject(movieTitle);
                    if (mov == null)
                        break;
                    ViewDetails(mov);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.out.printf("Input movie title: ");
                    movieTitle = InputHandler.scanString();
                    mov = SearchMovieObject(movieTitle);
                    if (mov == null)
                        break;
                    AddReview(mov);
                    break;
                case 8:
                    return;
            }
        } while (true);
    }

    public static void SearchMovie() {
        do {
            System.out.println("===== SEARCH/LIST MOVIE =====");
            System.out.println("1. Search By Title");
            System.out.println("2. List All Movies");
            System.out.println("3. Return");
            int choice = InputHandler.scanInt();
            switch (choice) {
                case 1:
                    System.out.printf("Input movie title: ");
                    String title = InputHandler.scanString();
                    Movie mov = SearchMovieObject(title);
                    if (mov == null)
                        break;
                    SearchTitle(mov);
                    break;
                case 2:
                    ListAll();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Wrong input");
            }
        } while (true);
    }

    public static Movie SearchMovieObject(String movieTitle) {
        ArrayList<Movie> MovieList = MovieController.readFromDatabase();
        for (Movie i : MovieList) {
            if ((movieTitle.toLowerCase()).compareTo(i.getTitle().toLowerCase()) == 0) {
                return i;
            }
        }
        System.out.println("Movie not found");
        return null;

    }

    public static void SearchTitle(Movie i) { // Method from SeachMovie() to find movie based on title
        System.out.println("\n=================================================");
        System.out.println("Movie: " + i.getTitle());
        System.out.println("Release Date: " + i.getReleaseDate());
        System.out.printf("Genres: ");
        for (String k : i.getGenres())
            System.out.printf(k + ", ");
        System.out.printf("\n");
        System.out.println("Showing Status: " + i.getShowingStatus());
        System.out.println("=================================================\n");
    }

    public static void ListAll() { // Method from SeachMovie() to list all movies in db
        ArrayList<Movie> MovieList = MovieController.readFromDatabase();
        System.out.println();
        for (Movie i : MovieList) {
            System.out.println("=================================================");
            System.out.println("Movie: " + i.getTitle());
            System.out.println("Release Date: " + i.getReleaseDate());
            System.out.printf("Genres: ");
            for (String k : i.getGenres())
                System.out.printf(k + ", ");
            System.out.printf("\n");
            System.out.println("Showing Status: " + i.getShowingStatus());
        }
        System.out.println("=================================================\n");
    }

    public static void ViewDetails(Movie i) {
        System.out.println("\n=================================================");
        System.out.println("Movie: " + i.getTitle());
        System.out.println("id: " + i.getId());
        System.out.println("Synopsis: " + i.getSynopsis());
        System.out.println("Director: " + i.getDirector());
        System.out.printf("Cast: ");
        for (String j : i.getCast())
            System.out.printf(j + ", ");
        System.out.printf("\n");
        System.out.printf("Genres: ");
        for (String k : i.getGenres())
            System.out.printf(k + ", ");
        System.out.printf("\n");
        System.out.println("Release Date: " + i.getReleaseDate());
        System.out.println("Content Rating: " + i.getContentRating());
        System.out.println("Movie Type: " + i.getMovieType());
        System.out.println("Showing Status: " + i.getShowingStatus());
        System.out.println();
        System.out.println("Average Review Rating: " + i.getAverageReviewRating() + "★");
        //print reviews
        int numReviews = i.getReviews().size(), j = 1;
        if (numReviews > 0)
            System.out.println("Reviews: ");
        ArrayList<Review> reviewList = i.getReviews();
        for (Review R : reviewList) {
            System.out.println("Review " + j + R);
            j++;
        }

        System.out.println("=================================================\n");
    }

    public static void AddReview(Movie i) {
        System.out.printf("Rate " + i.getTitle() + " from 1-5: ");
        int rating = InputHandler.scanInt();
        System.out.println("Input your reviews for " + i.getTitle() + ": ");
        String review = InputHandler.scanString();
        Review newReview = new Review(rating, review); // create new review
        ArrayList<Review> reviewList = i.getReviews(); // retrieve exisiting reviewList
        reviewList.add(newReview);
        i.setReviews(reviewList); // update reviewList

        // update averageReviewRating
        float totalReviewRating = 0;
        int numReviews = reviewList.size();
        for (Review j : reviewList) {
            totalReviewRating += j.getRating();
        }
        i.setAverageReviewRating(totalReviewRating / numReviews);
    }
}