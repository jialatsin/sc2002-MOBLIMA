package control;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entity.*;
import entity.Enumerators.ContentRating;
import entity.Enumerators.MovieType;
/**
 * It Represents the Movie Controller 
 * @author OOP SSP1 Lab Group 4
 * @version 8/11/2022
 */
public class MovieController extends DatabaseController<Movie> {
	/** 
     * File path name of movie database file to access. 
     */
	public MovieController() {
        super(MainController.FILEPATH_MOVIE);
    }

    /**
     * Search for a movie by its movie id in the Movie database.
     * @param id The Movie id 
     * @return movie If successful, it returns the movie by matching its unique movieid with an id in the database, else null value is returned
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
     * Search for movies with titles with matching keywords in the Movie database.
     * @param title The movie title
     * @return moviesResult  Return an array list of Movies if found, else null
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
     * Delete the movie in the Database file, based on the movie id attribute passed
     * @param id The movie id
     * @return boolean It returns true on successful deletion of movie in the database file, based on the movie id attribute passed
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
     * It is defined for readability and easier referencing of Movie attributes in the order of selection options provided to user when updating movie.
     * attributes.
     * @author OOP SSP1 Lab Group 4
     * @version 8/11/2022
     */
    public enum MovieAttribute {
    	/**
    	 * The Movie ID 
    	 */
        ID,
        /**
    	 * The title of the movie
    	 */
        TITLE, 
        /**
    	 * A brief summary of the movie
    	 */
        SYNOPSIS,
        /**
    	 * The names of the directors of the movie
    	 */
        DIRECTOR,
    	 /**
     	 * The names of the cast members of the movie
     	 */
        CAST,
     	/**
     	 * The genres of the movie
     	 */
     	 GENRES,
     	 /**
     	 * The release date of the movie
     	 */
     	 RELEASE_DATE,
     	/**
     	 * The end date of the movie, as in from when the movie will stop being shown in the cinema
     	 */
     	 END_DATE,
     	/**
     	 * The content rating of the movie as in whether it is PG, NC17 etc.
     	 */
     	 CONTENT_RATING,
     	 /**
     	 *  The type of movie as in whether it is Regular 2D, Blockbuster 3D etc.
     	 */
     	 MOVIE_TYPE,
     	/**
     	 * The ticket sales of the movie
     	 */
     	 TICKET_SALES;
    	/**
    	 * CHECK THIS AGAIN
    	 * @param i To store the user selection 
    	 * @return values The values generated according to the chosen i. 
    	 */
        public static MovieAttribute get(int i) {
            return values()[i - 1]; // User selection starts from 1, but enum counting starts from 0
        }
    }

    /**
     * It updates the selected movie's entry in Movie database with the new attribute.
     * @param movie             The name of the selected movie
     * @param attribute         The movie attribute to be updated
     * @param newAttributeValue The new attribute value
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
     * It adds the given Review to the given Movie's entry and updates the movie's average rating in Movie database.
     * @param movie  The name of the selected movi
     * @param review The New review to be added for the movie 
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
     * It sorts given list of movies in descending order based on its averageReviewRating attribute.
     * @param movies The array list of movies
     * @return movies it returns an sorted arraylist of movies 
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
     * It sorts given list of movies in descending order based on its ticketSales attribute.
     * @param movies The array list of movies
     * @return movies It returns an sorted arraylist of movies in descending order based on its ticketSales attribute.
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
