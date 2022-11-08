package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import boundary.InputHandler;

/**
 * It Represents a Showing for a Movie
 * 
 * @author OP SSP1 Lab Group 4
 * @version 30/10/2022
 */
public class Showing implements Serializable {

    /**
     * id for show id
     */
    private int id;
    /**
     * for the seating layout of the movie
     */
    private SeatingLayout seatingAvailability;
    /**
     * for the name of the movie
     */
    private Movie movie;
    /**
     * for the time when the show begins
     */
    private LocalDateTime showTime;
    /**
     * for which cinema the movie is going to be played
     */
    private Cinema cinema;
    /**
     * cineplex for which cineplex the movie is going to be be played
     */
    private Cineplex cineplex;

    /**
     * The current available seating layout for this Showing
     * 
     * @param id                  The unique id of the show
     * @param seatingAvailability The available seats of the movie show
     * @param movie               The movie name
     * @param showtime            The time when the show starts
     * @param cinema              The cinema where movie is going to be played
     * @param cineplex            The cineplex where the movie is going to be be
     *                            played
     */
    public Showing(int id, SeatingLayout seatingAvailability, Movie movie, LocalDateTime showtime, Cinema cinema,
            Cineplex cineplex) {
        this.id = id;
        this.seatingAvailability = seatingAvailability;
        this.movie = movie;
        this.showTime = showtime;
        this.cinema = cinema;
        this.cineplex = cineplex;
    }

    /**
     * String to display the showingid, movie name, showtime, cineplex and the
     * cinema.
     * 
     * @return string to display the showingid, movie name, showtime, cineplex and
     *         the cinema.
     */
    public String toString() {
        return "Showing ID: " + id
                + "\nMovie Title: " + movie.getTitle() + " (ID: " + movie.getId() + ")"
                + "\nShowtime: " + showTime.format(InputHandler.getDateTimeFormat())
                + "\nCineplex: " + cineplex.getName()
                + " (Cinema: " + cinema.getCode() + ", " + cinema.getCinemaClass() + ")"
                + "\n=================================================";
    }

    /**
     * Compare 2 Showing Instances to check if they are identical
     * 
     * @return boolean Return true if both showings are identical based on their
     *         showingid, movie, show time, cinema and cineplex etc, else false
     */
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

    /**
     * Get the unique showid
     * 
     * @return id The unique id of the show
     */
    public int getId() {
        return id;
    }

    /**
     * The unique showid
     * 
     * @param id The unique id of the show
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the availability of the movie-seats
     * 
     * @return seatingAvailability The availability of the movie seats
     */
    public SeatingLayout getSeatingAvailability() {
        return seatingAvailability;
    }

    /**
     * The availability of the movie-seats
     * 
     * @param seatingAvailability The availability of the movie seats
     */
    public void setSeatingAvailability(SeatingLayout seatingAvailability) {
        this.seatingAvailability = seatingAvailability;
    }

    /**
     * Get the name of the movie
     * 
     * @return movie The name of the movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * The movie name
     * 
     * @param movie The name of the movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Get the when the show starts
     * 
     * @return showTime The time when the show starts
     */
    public LocalDateTime getShowTime() {
        return showTime;
    }

    /**
     * The showTime of the movie
     * 
     * @param showTime The showTime of the movie
     */
    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    /**
     * The cinema where movie is going to be shown
     * 
     * @return cinema The cinema where movie is going to be shown
     */
    public Cinema getCinema() {
        return cinema;
    }

    /**
     * The cinema where the movie is going to be shown
     * 
     * @param cinema The cinema where the movie is going to be shown
     */
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    /**
     * The cineplex where the movie is going to be shown
     * 
     * @return cineplex The cineplex where the movie is going to be shown
     */
    public Cineplex getCineplex() {
        return cineplex;
    }

    /**
     * The cineplex where the movie is going to be shown
     * 
     * @param cineplex The cineplex where the movie is going to be shown
     */
    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }
}
