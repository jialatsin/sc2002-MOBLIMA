package control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entity.*;
import entity.Enumerators.ContentRating;
import entity.Enumerators.MovieType;

/**
 * Represents a MovieController which contains the logic for handling Movie
 * data.
 */
public class MovieController extends DatabaseController<Movie> {
    /**
     * Creates a MovieController with the file path of the movie database file
     * to access.
     */
    public MovieController() {
        super(MainController.FILEPATH_MOVIE);
    }

    /**
     * Returns a movie by searching with its movie id in the movie database.
     * 
     * @param id Id of movie to be searched.
     * @return Returns movie with matching id if found in database, else null.
     */
    public Movie findMovie(int id) {
        ArrayList<Movie> movies = readFromDatabase();
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Returns a list of movies by searching with titles containing matching
     * keywords in the movie database.
     * 
     * @param title Title of movie to be searched.
     * @return Returns list of matching movies if found in database, else null.
     */
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

    /**
     * Deletes movie by searching with the given movie id in the movie database.
     * 
     * @param id Id of movie to be deleted.
     * @return Returns true if movie is successfully deleted, else false.
     */
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

    /**
     * Defined for readability and easier referencing of Movie attributes.
     * In the order of selection options provided to user when updating movie
     * attributes.
     */
    public enum MovieAttribute {
        /** Unique identifier for the movie. */
        ID,
        /** Title of the movie. */
        TITLE,
        /** Synopsis of the movie. */
        SYNOPSIS,
        /** Director of the movie. */
        DIRECTOR,
        /** List of cast members for the movie. */
        CAST,
        /** List of genres for the movie. */
        GENRES,
        /** Release date of the movie. */
        RELEASE_DATE,
        /** Date when the movie stops showing. */
        END_DATE,
        /** Content rating of the movie (eg. PG, NC17). */
        CONTENT_RATING,
        /** Type of the movie (eg. Blockbuster 3D). */
        MOVIE_TYPE,
        /** Total tickets sold for the movie. */
        TICKET_SALES;

        /**
         * Returns the mapping of the user input selection to the attribute enumerator.
         * User selection starts from 1, but enumerator counting starts from 0.
         * 
         * @param i Input user selection.
         * @return Returns the mapping of the user input selection to the attribute
         *         enumerator.
         */
        public static MovieAttribute get(int i) {
            return values()[i - 1]; // User selection starts from 1, but enum counting starts from 0
        }
    }

    /**
     * Updates the selected movie's entry in the movie database with the given new
     * attribute.
     * 
     * @param movie             Movie to be updated.
     * @param attribute         Movie attribute to be updated.
     * @param newAttributeValue New attribute value.
     */
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

    /**
     * Adds the given review to the given movie's entry and updates the movie's
     * average rating in the movie database.
     * 
     * @param movie  Selected movie to be updated.
     * @param review New review to be added for the movie.
     */
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

    /**
     * Sorts the given list of movies in descending order based on its
     * averageReviewRating attribute.
     * 
     * @param movies List of movies to be sorted.
     * @return Returns a list of movies sorted by rating.
     */
    public ArrayList<Movie> sortByRating(ArrayList<Movie> movies) {
        Collections.sort(movies, Collections.reverseOrder(new Comparator<Movie>() {
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
        }));
        return movies;
    }

    /**
     * Sorts the given list of movies in descending order based on its
     * ticketSales attribute.
     * 
     * @param movies List of movies to be sorted.
     * @return Returns a list of movies sorted by ticket sales.
     */
    public ArrayList<Movie> sortByTicketSales(ArrayList<Movie> movies) {
        Collections.sort(movies, Collections.reverseOrder(new Comparator<Movie>() {
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
        }));
        return movies;
    }
}
