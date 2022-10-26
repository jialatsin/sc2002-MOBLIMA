package control;

import java.util.ArrayList;

import entity.*;

public class MovieController extends DatabaseController<Movie> {
    public MovieController() {
        super(MainController.FILEPATH_MOVIE);
    }

    public Movie getMovieById(int id) {
        ArrayList<Movie> movies = readFromDatabase();
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    // Returns true on successful deletion
    public boolean deleteMovieById(int id) {
        ArrayList<Movie> movies = readFromDatabase();
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                movies.remove(movie);
                overwriteDatabase(movies);
                return true;
            }
        }
        return false;
    }

    // Returns true on successful addition of review
    public boolean addReviewToMovie(Review review, Movie movie) {
        ArrayList<Movie> movies = readFromDatabase();
        for (Movie m : movies) {
            if (m.equals(movie)) {
                ArrayList<Review> reviews = movie.getReviews();
                reviews.add(review);
                movie.setReviews(reviews);
                movie.setAverageReviewRating();
                movies.set(movies.indexOf(m), movie);
                overwriteDatabase(movies);
                return true;
            }
        }
        return false;
    }
}