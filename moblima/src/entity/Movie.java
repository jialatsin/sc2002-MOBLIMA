package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

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

    public Movie(int id, String title, String synopsis, String director, ArrayList<String> cast,
            ArrayList<String> genres, LocalDate releaseDate, ContentRating contentRating, MovieType movieType,
            ShowingStatus showingStatus) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.reviews = new ArrayList<Review>();
        this.reviewRating = 0;
        this.ticketSales = 0;
        this.contentRating = contentRating;
        this.movieType = movieType;
        this.showingStatus = showingStatus;
    }
}
