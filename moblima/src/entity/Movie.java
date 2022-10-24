package entity;

import java.io.Serializable;
import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {
    public enum ContentRating {
        G, PG, PG13, R, NC17
    };

    public enum MovieType {
        BLOCKBUSTER, TWO_D, THREE_D
    };

    public enum ShowingStatus {
        COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING
    };

    private int id;
    private String title;
    private String synopsis;
    private String director;
    private ArrayList<String> cast;
    private ArrayList<String> genres;
    private LocalDate releaseDate;

    private ArrayList<Review> reviews;
    private double reviewRating;
    private int ticketSales;

    private ContentRating contentRating;
    private MovieType movieType;
    private ShowingStatus showingStatus;

    public Movie(int id, String title, String synopsis, String director, ArrayList<String> cast2,
            ArrayList<String> genres2, LocalDate releaseDate, ContentRating contentRating, MovieType movieType,
            ShowingStatus showingStatus) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast2;
        this.genres = genres2;
        this.releaseDate = releaseDate;
        this.reviews = new ArrayList<Review>();
        this.reviewRating = 0;
        this.ticketSales = 0;
        this.contentRating = contentRating;
        this.movieType = movieType;
        this.showingStatus = showingStatus;
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

    public double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(double reviewRating) {
        this.reviewRating = reviewRating;
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
