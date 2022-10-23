package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Showing implements Serializable {
    private int id;
    private SeatingLayout seatingAvailablity;
    private Movie movie;
    private LocalDateTime showtime;
    private Cinema cinema;
    private Cineplex cineplex;

    public Showing(int id, SeatingLayout seatingAvailablity, Movie movie, LocalDateTime showtime, Cinema cinema,
            Cineplex cineplex) {
        this.id = id;
        this.seatingAvailablity = seatingAvailablity;
        this.movie = movie;
        this.showtime = showtime;
        this.cinema = cinema;
        this.cineplex = cineplex;
    }
}
