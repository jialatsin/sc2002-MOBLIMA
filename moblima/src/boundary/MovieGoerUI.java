package boundary;

import control.MovieController;
import java.util.*;

import entity.Movie;
import entity.Review;

public class MovieGoerUI {
    private static MovieController movieController = new MovieController();

    public static void main() {
        int selection;
        String movieTitle;
        Movie mov;
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
                    Movie newMovie = AddReview(mov);
                    updated = UpdateMovieObject(newMovie);
                    if (updated == true) {
                        System.out.println("Review added");
                    } else System.out.println("False");
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    public static void SearchMovie() {
        do {
            System.out.println("===== SEARCH/LIST MOVIE =====");
            System.out.println("1. Search By Title");
            System.out.println("2. List All Movies");
            System.out.println("0. Return");
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
                case 0:
                    return;
                default:
                    System.out.println("Wrong input");
            }
        } while (true);
    }

    public static Movie SearchMovieObject(String movieTitle) {
        ArrayList<Movie> MovieList = movieController.readFromDatabase();
        for (Movie i : MovieList) {
            if ((movieTitle.toLowerCase()).compareTo(i.getTitle().toLowerCase()) == 0) {
                return i;
            }
        }
        System.out.println("Movie not found");
        return null;

    }

    public static boolean UpdateMovieObject(Movie updatedMovie) {
        ArrayList<Movie> MovieList = movieController.readFromDatabase();
        for (int i=0; i<MovieList.size(); i++) {
            if ((updatedMovie.getTitle().toLowerCase()).compareTo(MovieList.get(i).getTitle().toLowerCase()) == 0) {
                MovieList.set(i, updatedMovie);
                movieController.overwriteDatabase(MovieList);
                return true;
            }
        }
        return false;
    }

    public static void SearchTitle(Movie movie) { // Method from SeachMovie() to find movie based on title
        System.out.println("\n=================================================");
        System.out.println("Movie: " + movie.getTitle());
        System.out.println("Release Date: " + movie.getReleaseDate());
        System.out.printf("Genres: ");
        for (String k : movie.getGenres())
            System.out.printf(k + ", ");
        System.out.printf("\n");
        System.out.println("Showing Status: " + movie.getShowingStatus());
        System.out.println("=================================================\n");
    }

    public static void ListAll() { // Method from SeachMovie() to list all movies in db
        ArrayList<Movie> MovieList = movieController.readFromDatabase();
        System.out.println();
        for (Movie movie : MovieList) {
            System.out.println("=================================================");
            System.out.println("Movie: " + movie.getTitle());
            System.out.println("Release Date: " + movie.getReleaseDate());
            System.out.printf("Genres: ");
            for (String k : movie.getGenres())
                System.out.printf(k + ", ");
            System.out.printf("\n");
            System.out.println("Showing Status: " + movie.getShowingStatus());
        }
        System.out.println("=================================================\n");
    }

    public static void ViewDetails(Movie movie) {
        System.out.println("\n=================================================");
        System.out.println("Movie: " + movie.getTitle());
        System.out.println("id: " + movie.getId());
        System.out.println("Synopsis: " + movie.getSynopsis());
        System.out.println("Director: " + movie.getDirector());
        System.out.printf("Cast: ");
        for (String j : movie.getCast())
            System.out.printf(j + ", ");
        System.out.printf("\n");
        System.out.printf("Genres: ");
        for (String genre : movie.getGenres())
            System.out.printf(genre + ", ");
        System.out.printf("\n");
        System.out.println("Release Date: " + movie.getReleaseDate());
        System.out.println("Content Rating: " + movie.getContentRating());
        System.out.println("Movie Type: " + movie.getMovieType());
        System.out.println("Showing Status: " + movie.getShowingStatus());
        System.out.println();
        System.out.println("Average Review Rating: " + movie.getAverageReviewRating() + "*");
        //print reviews
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

    public static Movie AddReview(Movie movie) {
        System.out.printf("Rate " + movie.getTitle() + " from 1-5: ");
        int rating = InputHandler.scanInt();
        System.out.println("Input your reviews for " + movie.getTitle() + ": ");
        String userReview = InputHandler.scanString();
        Review newReview = new Review(rating, userReview); // create new review
        ArrayList<Review> reviewList = movie.getReviews(); // retrieve exisiting reviewList
        reviewList.add(newReview);
        movie.setReviews(reviewList); // update reviewList

        // update averageReviewRating
        float totalReviewRating = 0;
        int numReviews = reviewList.size();
        for (Review review : reviewList) {
            totalReviewRating += review.getRating();
        }
        movie.setAverageReviewRating(totalReviewRating / numReviews);
        return movie;
    }
}