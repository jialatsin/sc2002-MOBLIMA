package control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entity.*;
import entity.Constants.ContentRating;
import entity.Constants.MovieType;

public class MovieController extends DatabaseController<Movie> {
    public MovieController() {
        super(MainController.FILEPATH_MOVIE);
    }

    // Search for a movie by its movie id in the Movie database
    // Returns the matching movie, returns null if movie not found
    public Movie findMovie(int id) {
        ArrayList<Movie> movies = readFromDatabase();
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    // Search for movies with titles with matching keywords in the Movie database
    // Returns an ArrayList of matching movies, returns null if no movie found
    public ArrayList<Movie> findMovies(String title) {
        ArrayList<Movie> movies = readFromDatabase();
        ArrayList<Movie> moviesResult = new ArrayList<Movie>();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                moviesResult.add(movie);
            }
        }

        if (moviesResult.isEmpty()) {
            return null;
        }
        return moviesResult;
    }

    // Search for and deletes movie with movie id in the Movie database
    // Returns true on successful deletion
    public boolean deleteMovie(int id) {
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

    // In the order of selection options provided to user when updating movie
    // attributes
    public enum MovieAttribute {
        ID, TITLE, SYNOPSIS, DIRECTOR, CAST, GENRES, RELEASE_DATE, END_DATE, CONTENT_RATING, MOVIE_TYPE, TICKET_SALES;

        public static MovieAttribute get(int i) {
            return values()[i - 1]; // User selection starts from 1, but enum counting starts from 0
        }
    }

    // Updates the selected movie's entry in Movie database with the new attribute
    @SuppressWarnings("unchecked")
    public void updateMovieAttribute(Movie movie, MovieAttribute attribute, Object newAttributeValue) {
        ArrayList<Movie> movies = readFromDatabase();
        int movieIndexInDatabase = movies.indexOf(movie);

        switch (attribute) {
            case ID:
                movie.setId((int) newAttributeValue);
                break;
            case TITLE:
                movie.setTitle((String) newAttributeValue);
                break;
            case SYNOPSIS:
                movie.setSynopsis((String) newAttributeValue);
                break;
            case DIRECTOR:
                movie.setDirector((String) newAttributeValue);
                break;
            case CAST:
                movie.setCast((ArrayList<String>) newAttributeValue);
                break;
            case GENRES:
                movie.setGenres((ArrayList<String>) newAttributeValue);
                break;
            case RELEASE_DATE:
                movie.setReleaseDate((LocalDate) newAttributeValue);
                break;
            case END_DATE:
                movie.setEndDate((LocalDate) newAttributeValue);
                break;
            case CONTENT_RATING:
                movie.setContentRating((ContentRating) newAttributeValue);
                break;
            case MOVIE_TYPE:
                movie.setMovieType((MovieType) newAttributeValue);
                break;
            case TICKET_SALES:
                movie.setTicketSales((int) newAttributeValue);
                break;
        }
        movies.set(movieIndexInDatabase, movie);
        overwriteDatabase(movies);
    }

    // Adds the given review to selected movie's entry in Movie database
    public void addReviewToMovie(Movie movie, Review review) {
        ArrayList<Movie> movies = readFromDatabase();
        int movieIndexInDatabase = movies.indexOf(movie);

        // Append new review to existing list of reviews
        ArrayList<Review> reviews = movie.getReviews();
        reviews.add(review);

        // Update movie average rating and reviews
        movie.setReviews(reviews);
        movie.setAverageReviewRating();

        // Updates the selected movie's entry in Movie database with new list of reviews
        movies.set(movieIndexInDatabase, movie);
        overwriteDatabase(movies);
    }

    // Sorts given ArrayList of movies based on its averageReviewRating attribute
    public ArrayList<Movie> sortByRating(ArrayList<Movie> movies) {
        Collections.sort(movies, new Comparator<Movie>() {
            public int compare(Movie a, Movie b) {
                double aRating = a.getAverageReviewRating();
                double bRating = b.getAverageReviewRating();
                if (aRating < bRating) {
                    return -1;
                } else if (aRating > bRating) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        return movies;
    }

    // Sorts given ArrayList of movies based on its ticketSales attribute
    public ArrayList<Movie> sortByTicketSales(ArrayList<Movie> movies) {
        Collections.sort(movies, new Comparator<Movie>() {
            public int compare(Movie a, Movie b) {
                int aTicketSales = a.getTicketSales();
                int bTicketSales = b.getTicketSales();
                if (aTicketSales < bTicketSales) {
                    return -1;
                } else if (aTicketSales > bTicketSales) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        return movies;
    }
}
