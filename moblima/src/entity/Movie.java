package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import boundary.InputHandler;
import entity.Enumerators.ContentRating;
import entity.Enumerators.MovieType;
import entity.Enumerators.ShowingStatus;

/**
 * Represents a Movie.
 */
public class Movie implements Serializable {

    /** Unique identifier for this movie. */
    private int id;
    /** Title of this movie. */
    private String title;
    /** Brief summary of this movie. */
    private String synopsis;
    /** Director of this movie. */
    private String director;
    /** List of names of people who have acted in this movie. */
    private ArrayList<String> cast;
    /** List of genres portrayed in this movie. */
    private ArrayList<String> genres;
    /** Release date of this movie. */
    private LocalDate releaseDate;
    /** Date when this movie stops showing. */
    private LocalDate endDate;
    /** Content rating of this movie (eg. PG, NC17). */
    private ContentRating contentRating;
    /** Type of this movie (eg. Blockbuster 3D). */
    private MovieType movieType;
    /** List of reviews from moviegoers for this movie. */
    private ArrayList<Review> reviews = new ArrayList<Review>();
    /** Average rating of all reviews for this movie. */
    private double averageReviewRating = -1;
    /** Total tickets sold for this movie. */
    private int ticketSales = 0;

    /**
     * Creates a Movie with the given attributes.
     * By default, a movie will have no reviews and no ticket sales on creation.
     * 
     * @param id            The unique id for the movie.
     * @param title         The name of the movie.
     * @param synopsis      The summary of the movie.
     * @param director      The director of the movie.
     * @param cast          The cast members of the movie.
     * @param genres        The genres of the movie.
     * @param releaseDate   The release date of the movie.
     * @param endDate       The end date of the movie.
     * @param contentRating The content rating of the movie.
     * @param movieType     The type of movie.
     */
    public Movie(int id, String title, String synopsis, String director, ArrayList<String> cast,
            ArrayList<String> genres, LocalDate releaseDate, LocalDate endDate, ContentRating contentRating,
            MovieType movieType) {
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
     * Returns a string containing a brief description about this movie.
     * String contains movie title, ID, release and end date, showing status, ticket
     * sales and overall review rating.
     * 
     * @return String containing a brief description about this movie.
     */
    @Override
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
     * Compares 2 Movie instances to check if they are identical.
     * 
     * @return Returns true if all attributes of both movies are equal, else
     *         false.
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
     * Gets the unique id for this movie.
     * 
     * @return Unique id for this movie.
     */
    public int getId() {
        return id;
    }

    /**
     * Changes the unique id for this movie.
     * 
     * @param id The input id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title for this movie.
     * 
     * @return Title for this movie.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Changes the title for this movie.
     * 
     * @param title The input title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the synopsis for this movie.
     * 
     * @return synopsis for this movie.
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Changes the synopsis for this movie.
     * 
     * @param synopsis The input synopsis.
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Gets the director for this movie.
     * 
     * @return Director for this movie.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Changes the director for this movie.
     * 
     * @param director The input director.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Gets the list of cast members for this movie.
     * 
     * @return List of cast members for this movie.
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     * Changes the list of cast members for this movie.
     * 
     * @param cast The input list of cast members.
     */
    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    /**
     * Gets the list of genres for this movie.
     * 
     * @return List of genres for this movie.
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * Changes the list of genres for this movie.
     * 
     * @param genres The input list of genres.
     */
    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    /**
     * Gets the release date for this movie.
     * 
     * @return Release date for this movie.
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Changes the release date for this movie.
     * 
     * @param releaseDate The input release date.
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Gets the end date for this movie.
     * 
     * @return End date for this movie.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Changes the end date for this movie.
     * 
     * @param endDate The input end date.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the list of reviews for this movie.
     * 
     * @return List of reviews for this movie.
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * Changes the list of reviews for this movie.
     * 
     * @param reviews The input list of reviews.
     */
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Gets the average review rating for this movie.
     * 
     * @return Average review rating for this movie.
     */
    public double getAverageReviewRating() {
        return averageReviewRating;
    }

    /**
     * Calculates the average review rating for this movie.
     * Average review rating is set to -1 if there are no reviews currently
     * available.
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
     * Gets the number of ticket sales for this movie.
     * 
     * @return Number of ticket sales for this movie.
     */
    public int getTicketSales() {
        return ticketSales;
    }

    /**
     * Changes the number of ticket sales for this movie.
     * 
     * @param ticketSales The input number of ticket sales.
     */
    public void setTicketSales(int ticketSales) {
        this.ticketSales = ticketSales;
    }

    /**
     * Gets the content rating for this movie.
     * 
     * @return Content rating for this movie.
     */
    public ContentRating getContentRating() {
        return contentRating;
    }

    /**
     * Changes the content rating for this movie.
     * 
     * @param contentRating The input content rating.
     */
    public void setContentRating(ContentRating contentRating) {
        this.contentRating = contentRating;
    }

    /**
     * Gets the type of this movie.
     * 
     * @return Type of this movie.
     */
    public MovieType getMovieType() {
        return movieType;
    }

    /**
     * Changes the type for this movie.
     * 
     * @param movieType The input movie type.
     */
    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    /**
     * Queries the showing status of the movie. Showing status is automatically
     * derived from movie's releaseDate, endDate and the current LocalDate from the
     * system clock.
     * Returns "END OF SHOWING" if current date is after movie end date, "NOW
     * SHOWING" if current date is after release date and before end date, "PREVIEW"
     * if current date is within 7 days before release date, "COMING SOON" if
     * current date is more than 7 days before release date.
     * 
     * @return Showing status of this movie.
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
