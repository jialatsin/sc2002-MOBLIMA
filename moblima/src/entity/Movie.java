package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import boundary.InputHandler;
import entity.Constants.ContentRating;
import entity.Constants.MovieType;
import entity.Constants.ShowingStatus;

public class Movie implements Serializable {

    // unique ID that identifies the Movie
    private int id;
    private String title;
    private String synopsis;
    private String director;
    private ArrayList<String> cast;
    private ArrayList<String> genres;
    private LocalDate releaseDate;
    private LocalDate endDate;

    private ContentRating contentRating;
    private MovieType movieType;

    private ShowingStatus showingStatus;
    private ArrayList<Review> reviews = new ArrayList<Review>();
    private double averageReviewRating = -1;
    private int ticketSales = 0;

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
            movieString += String.format("%.2f *", averageReviewRating);

        movieString += "\n=================================================";

        return movieString;
    }

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
                    && this.getShowingStatus() == other.getShowingStatus()
                    && this.reviews.equals(other.getReviews())
                    && this.averageReviewRating == other.getAverageReviewRating()
                    && this.ticketSales == other.getTicketSales();
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public double getAverageReviewRating() {
        return averageReviewRating;
    }

    // Average review rating is set to -1 if there are no reviews currently
    // available
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

    public int getTicketSales() {
        return ticketSales;
    }

    public void setTicketSales(int ticketSales) {
        this.ticketSales = ticketSales;
    }

    public ContentRating getContentRating() {
        return contentRating;
    }

    public void setContentRating(ContentRating contentRating) {
        this.contentRating = contentRating;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    // Query the showing status of the movie
    // Showing status is automatically derived from movie's releaseDate, endDate
    // and the current LocalDate from the system clock
    // Returns "END OF SHOWING" if current date is after movie end date
    // Returns "NOW SHOWING" if current date is after release date and before end
    // date
    // Returns "PREVIEW" if current date is within 7 days before release date
    // Returns "COMING SOON" if current date is more than 7 days before release date
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
