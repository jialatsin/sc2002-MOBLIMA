package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import boundary.InputHandler;

public class Showing implements Serializable {
    private int id;
    private SeatingLayout seatingAvailablity;
    private Movie movie;
    private LocalDateTime showTime;
    private Cinema cinema;
    private Cineplex cineplex;

    public Showing(int id, SeatingLayout seatingAvailablity, Movie movie, LocalDateTime showtime, Cinema cinema,
            Cineplex cineplex) {
        this.id = id;
        this.seatingAvailablity = seatingAvailablity;
        this.movie = movie;
        this.showTime = showtime;
        this.cinema = cinema;
        this.cineplex = cineplex;
    }

    public String toString() {
        return "\nShowing ID: " + id
                + "\nMovie Title: " + movie.getTitle() + " (ID: " + movie.getId() + ")"
                + "\nShowtime: " + showTime.format(InputHandler.getDateTimeFormat())
                + "\nCineplex: " + cineplex.getName()
                + " (Cinema: " + cinema.getCode() + ", " + cinema.getCinemaClass() + ")"
                + "\nAvailable Seats: " + seatingAvailablity.getAvailableSeatsCount()
                + "\n=================================================";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Showing) {
            Showing other = (Showing) obj;
            return this.id == other.getId()
                    && this.seatingAvailablity.equals(other.getSeatingAvailablity())
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

    public SeatingLayout getSeatingAvailablity() {
        return seatingAvailablity;
    }

    public void setSeatingAvailablity(SeatingLayout seatingAvailablity) {
        this.seatingAvailablity = seatingAvailablity;
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
