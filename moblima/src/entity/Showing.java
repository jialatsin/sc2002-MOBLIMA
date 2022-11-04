package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import boundary.InputHandler;

/* Represents a Showing for a Movie */
public class Showing implements Serializable {
    private int id;
    /* The current available seating layout for this Showing */
    private SeatingLayout seatingAvailability;
    private Movie movie;
    private LocalDateTime showTime;
    private Cinema cinema;
    private Cineplex cineplex;

    public Showing(int id, SeatingLayout seatingAvailability, Movie movie, LocalDateTime showtime, Cinema cinema,
            Cineplex cineplex) {
        this.id = id;
        this.seatingAvailability = seatingAvailability;
        this.movie = movie;
        this.showTime = showtime;
        this.cinema = cinema;
        this.cineplex = cineplex;
    }

    public String toString() {
        return "Showing ID: " + id
                + "\nMovie Title: " + movie.getTitle() + " (ID: " + movie.getId() + ")"
                + "\nShowtime: " + showTime.format(InputHandler.getDateTimeFormat())
                + "\nCineplex: " + cineplex.getName()
                + " (Cinema: " + cinema.getCode() + ", " + cinema.getCinemaClass() + ")"
                + "\n=================================================";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Showing) {
            Showing other = (Showing) obj;
            return this.id == other.getId()
                    && this.seatingAvailability.equals(other.getSeatingAvailability())
                    && this.movie.equals(other.getMovie())
                    && this.showTime.equals(other.getShowTime())
                    && this.cinema.equals(other.getCinema())
                    && this.cineplex.equals(other.getCineplex());
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SeatingLayout getSeatingAvailability() {
        return seatingAvailability;
    }

    public void setSeatingAvailability(SeatingLayout seatingAvailability) {
        this.seatingAvailability = seatingAvailability;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Cineplex getCineplex() {
        return cineplex;
    }

    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }
}
