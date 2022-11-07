package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


import entity.Enumerators.ContentRating;
import entity.Enumerators.MovieType;
import entity.Enumerators.ShowingStatus;

/**
 * It Represents a Movie and its information 
 * @author OOP SSP1 Group 4
 * @version 8/11/2022
 */
public class Movie implements Serializable {

    /**
     * id for unique movie id
     */
	private int id;
	/**
	 * title for movie name
	 */
    private String title;
    /**
     * synopsis for a summary of the movie
     */
    private String synopsis;
    /**
     * director for the directors of the film
     */
    private String director;
    /**
     * cast for the names of the people who have acted in the movie
     */
    private ArrayList<String> cast;
    /**
     * genres for the type of genre portrayed by the film
     */
    private ArrayList<String> genres;
    /**
     * releaseDate for when the film is going be released
     */
    private LocalDate releaseDate;
    /**
     * endDate for when the film is going to end in the theatre
     */
    private LocalDate endDate;
    /**
     * contentRating is whether the movie is PG, NC17 etc.
     */
    private ContentRating contentRating;
    /**
     * movieType for the type of movie it is
     */
    private MovieType movieType;
    /**
     * reviews for the movie reviews
     */
    private ArrayList<Review> reviews = new ArrayList<Review>();
    /**
     * averageReviewRatings initialized to -1 
     */
    private double averageReviewRating = -1;
    /**
     * ticketSales initialized as 0
     */
    private int ticketSales = 0;
    
    /**
     * Creates a movie with the given attributes
     * By default, a movie will have no reviews and no ticketSales on creation
     * @param id            It is the unique movie id
     * @param title         The name of the movie
     * @param synopsis      The summary of the movie
     * @param director      The directors of the film
     * @param cast          The names of the people who have acted in the movie
     * @param genres        The type of genre portrayed by the film
     * @param releaseDate   The date when the film is going be released
     * @param endDate       The date when the film is going to end in the theatre
     * @param contentRating The content rating of the movie, whether it is PG, NC17 etc.
     * @param movieType     The type of movie
     */
    
    public Movie(int id, String title, String synopsis, String director, ArrayList<String> cast,
            ArrayList<String> genres, LocalDate releaseDate, LocalDate endDate, ContentRating contentRating, MovieType movieType) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.endDate = endDate;
        this.contentRating = contentRating;
        this.movieType = movieType;
    }
    /**
     * String containing a short description about this Movie to return when called
     * Contains movie title, ID, release and end date, showing status, ticket sales
     * and overall review rating
     */
    public String toString() {
        String movieString = "";

        movieString += "Movie: " + title + " (ID: " + id + ")" + " (" + movieType + ")"
                + "\nRelease Date: " + releaseDate.format(InputHandler.getDateFormat()) + ", "
                + "End Date: " + endDate.format((InputHandler.getDateFormat()))
                + "\nShowing Status: " + getShowingStatus()
                + "\nTicket Sales: " + getTicketSales() + ", "
                + "Overall Rating: ";

        if (averageReviewRating < 0) {
            movieString += "NaN"; // No reviews available
        } else
            movieString += String.format("%.2f*", averageReviewRating);

        movieString += "\n=================================================";

        return movieString;
    }
    /**
     * Compare 2 Movie Instances to check if they are identical
     * @return boolean Return true if both movies are identical based on their title, synopsis, director, cast etc. else false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Movie) {
            Movie other = (Movie) obj;
            return this.id == other.getId()
                    && this.title.equals(other.getTitle())
                    && this.synopsis.equals(other.getSynopsis())
                    && this.director.equals(other.getDirector())
                    && this.cast.equals(other.getCast())
                    && this.genres.equals(other.getGenres())
                    && this.releaseDate.equals(other.getReleaseDate())
                    && this.endDate.equals(other.getEndDate())
                    && this.contentRating.equals(other.getContentRating())
                    && this.movieType.equals(other.getMovieType())
                    && this.getShowingStatus().equals(other.getShowingStatus())
                    && this.reviews.equals(other.getReviews())
                    && this.averageReviewRating == other.getAverageReviewRating()
                    && this.ticketSales == other.getTicketSales();
        }
        return false;
    }

    /**
     * Get the unique movieid
     * @return id The unique movie id
     */
    public int getId() {
        return id;
    }
    /**
     * Unique movieid
     * @param id The unique movie id 
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Get the title of the movie
     * @return title The title of the movie
     */
    public String getTitle() {
        return title;
    }
    /**
     * The title of the movie
     * @param title The title of the movie
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Get the brief summary of the movie
     * @return synopsis The brief summary of the movie
     */
    public String getSynopsis() {
        return synopsis;
    }
    /**
     * The brief summary of the movie
     * @param synopsis The brief summary of the movie
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    /**
     * Get the names of the directors of the movies
     * @return director The names of the directors of the movies
     */
    public String getDirector() {
        return director;
    }
    /**
     * The names of the directors of the movies
     * @param director The names of the directors of the movies
     */
    public void setDirector(String director) {
        this.director = director;
    }
    /**
     * Get the cast names
     * @return cast The cast names of the movie
     */
    public ArrayList<String> getCast() {
        return cast;
    }
    /**
     * The cast names
     * @param cast The cast names of the movie
     */
    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }
    /**
     * Get the genres of the movie
     * @return genres The genres of the movie
     */
    public ArrayList<String> getGenres() {
        return genres;
    }
    /**
     * The genres of the movie
     * @param genres The genres of the movie
     */
    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }
    /**
     * The release date of the movie
     * @return release The release date of the movie
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    /**
     * The release date of the movie
     * @param releaseDate The release date of the movie
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    /**
     * Get the end date of the movie as in the last date when the movie will be showed in the theatre
     * @return end date of the movie as in the last date when the movie will be showed in the theatre
     */
    public LocalDate getEndDate() {
        return endDate;
    }
    /**
     * The end date of the movie as in the last date when the movie will be showed in the theatre
     * @param endDate The end date of the movie as in the last date when the movie will be showed in the theatre
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    /**
     * Get the reviews of the movie
     * @return reviews The reviews of the movie
     */

    public ArrayList<Review> getReviews() {
        return reviews;
    }
    /**
     * The reviews of the movie
     * @param reviews The reviews of the movie
     */
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
    /**
     * Get the average review rating of the movie
     * @return averageRevieRating The average rewview rating of the movie
     */
    public double getAverageReviewRating() {
        return averageReviewRating;
    }

    /**  
     *   Average review rating is set to -1 if there are no reviews currently available
     */
    public void setAverageReviewRating() {
        int reviewCount = reviews.size();
        if (reviewCount == 0) {
            this.averageReviewRating = -1;
            return;
        }
        double reviewRating = 0;
        for (Review review : reviews) {
            reviewRating += review.getRating();
        }
        this.averageReviewRating = reviewRating / reviewCount;
    }
    /**
     * Get the total ticket sales of the movie 
     * @return ticketSales The total ticket sales of the movie
     */

    public int getTicketSales() {
        return ticketSales;
    }
    /**
     * The total ticket sales of the movie
     * @param ticketSales The total ticket sales of the movie
     */
    public void setTicketSales(int ticketSales) {
        this.ticketSales = ticketSales;
    }
    /**
     * The the content rating of the movie as in whether its PG, NC17 etc.
     * @return contentRating The content rating of the movie as in whether its PG, NC17 etc. 
     */
    public ContentRating getContentRating() {
        return contentRating;
    }
    /**
     * The content rating of the movie as in whether its PG, NC17 etc.
     * @param contentRating of the movie as in whether its PG, NC17 etc.
     */
    public void setContentRating(ContentRating contentRating) {
        this.contentRating = contentRating;
    }
    /**
     * Get the type of the movie
     * @return movieType The Type of the movie
     */
    public MovieType getMovieType() {
        return movieType;
    }
    /**
     * The type of the movie
     * @param movieType The type of the movie
     */
    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }
    
    /**
     * Query the showing status of the movie
     * Showing status is automatically derived from movie's releaseDate, endDate
     * and the current LocalDate from the system clock
     * Returns "END OF SHOWING" if current date is after movie end date
     * Returns "NOW SHOWING" if current date is after release date and before end date
     * Returns "PREVIEW" if current date is within 7 days before release date
     * Returns "COMING SOON" if current date is more than 7 days before release date
     * @return Showing Status of the movie
     */
    public ShowingStatus getShowingStatus() {
        LocalDate current = LocalDate.now();

        if (current.isAfter(endDate)) {
            return ShowingStatus.END_OF_SHOWING;
        } else if (current.isAfter(releaseDate) && current.isBefore(endDate)) {
            return ShowingStatus.NOW_SHOWING;
        } else {
            // Difference in days from release date to current date (releaseDate - current)
            long daysBetween = ChronoUnit.DAYS.between(current, releaseDate);
            if (daysBetween <= 7) {
                return ShowingStatus.PREVIEW;
            } else {
                return ShowingStatus.COMING_SOON;
            }
        }
    }
}

