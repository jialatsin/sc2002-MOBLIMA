package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

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

    private ContentRating contentRating;
    private MovieType movieType;

    private ShowingStatus showingStatus = ShowingStatus.COMING_SOON;
    private ArrayList<Review> reviews = new ArrayList<Review>();
    private double averageReviewRating = -1;
    private int ticketSales = 0;

    public Movie(int id, String title, String synopsis, String director, ArrayList<String> cast,
            ArrayList<String> genres, LocalDate releaseDate, ContentRating contentRating, MovieType movieType) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.contentRating = contentRating;
        this.movieType = movieType;
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", synopsis=" + synopsis + ", director=" + director + ", cast="
                + cast + ", genres=" + genres + ", releaseDate=" + releaseDate + ", contentRating=" + contentRating
                + ", movieType=" + movieType + ", showingStatus=" + showingStatus + ", reviews=" + reviews
                + ", reviewRating=" + averageReviewRating + ", ticketSales=" + ticketSales + "]";
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
                    && this.contentRating.equals(other.getContentRating())
                    && this.movieType.equals(other.getMovieType())
                    && this.showingStatus.equals(other.getShowingStatus())
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

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public double getAverageReviewRating() {
        return averageReviewRating;
    }

    // Calculates average review rating and updates reviewRating
    // TODO: Overall reviewer rating will only be displayed if there are more than
    // ONE individual rating, else “NA” is displayed
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

    public ShowingStatus getShowingStatus() {
        return showingStatus;
    }

    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }
}
