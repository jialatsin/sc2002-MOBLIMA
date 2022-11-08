package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import boundary.InputHandler;

/** Represents a Showing for a movie. */
public class Showing implements Serializable {
    /** Unique identifier for this showing. */
    private int id;
    /** Current available seating layout for this showing. */
    private SeatingLayout seatingAvailability;
    /** Movie being shown for this showing. */
    private Movie movie;
    /** Time when this showing begins. */
    private LocalDateTime showTime;
    /** Cinema where this showing is being held. */
    private Cinema cinema;
    /** Cineplex where this showing is being held. */
    private Cineplex cineplex;

    /**
     * Creates a showing with the given attributes.
     * 
     * @param id                  The unique id of the showing.
     * @param seatingAvailability The seating availability of the showing.
     * @param movie               The movie being shown.
     * @param showtime            The time when the showing starts.
     * @param cinema              The cinema where the showing is being held.
     * @param cineplex            The cineplex where the showing is being held.
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
     * Returns a string containing a brief description about this showing.
     * String contains showing ID, movie title, movie ID, showtime, cineplex name,
     * cinema code and cinema class.
     * 
     * @return String containing a brief description about this movie.
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
     * Compares 2 Showing instances to check if they are identical.
     * 
     * @return Returns true if all attributes of both showings are equal, else
     *         false.
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
     * Gets the unique id for this showing.
     * 
     * @return Unique id for this showing.
     */
    public int getId() {
        return id;
    }

    /**
     * Changes the unique id for this showing.
     * 
     * @param id The input id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the seating availability for this showing.
     * 
     * @return Seating availability for this showing.
     */
    public SeatingLayout getSeatingAvailability() {
        return seatingAvailability;
    }

    /**
     * Changes the seating availability for this showing.
     * 
     * @param seatingAvailability The input seating availability.
     */
    public void setSeatingAvailability(SeatingLayout seatingAvailability) {
        this.seatingAvailability = seatingAvailability;
    }

    /**
     * Gets the movie for this showing.
     * 
     * @return Movie for this showing.
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Changes the movie for this showing.
     * 
     * @param movie The input movie.
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Gets the showtime for this showing.
     * 
     * @return Showtime for this showing.
     */
    public LocalDateTime getShowTime() {
        return showTime;
    }

    /**
     * Changes the showtime for this showing.
     * 
     * @param showTime The input showtime.
     */
    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    /**
     * Gets the cinema for this showing.
     * 
     * @return Cinema for this showing.
     */
    public Cinema getCinema() {
        return cinema;
    }

    /**
     * Changes the cinema for this showing.
     * 
     * @param cinema The input cinema.
     */
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    /**
     * Gets the cineplex for this showing.
     * 
     * @return Cineplex for this showing.
     */
    public Cineplex getCineplex() {
        return cineplex;
    }

    /**
     * Changes the cineplex for this showing.
     * 
     * @param cineplex The input cineplex.
     */
    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }
}
