package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import boundary.InputHandler;

/** Represents the Booking made by a moviegoer for a movie showing. */
public class Booking implements Serializable {
    /**
     * Unique transaction identifier of this booking in the format of
     * XXXYYYYMMDDhhmm.
     * XXX refers to the cinema code, YYYYDDMMhhmm refers to the datetime.
     */
    private String transactionID;
    /** Moviegoer that made this booking. */
    private MovieGoer movieGoer;
    /** List of movie tickets assigned to this booking. */
    private ArrayList<Ticket> tickets;
    /** Time when this booking's transaction was completed. */
    private LocalDateTime transactionTime;
    /** Movie showing that this booking is for. */
    private Showing showing;
    /** Price paid for the movie tickets in this booking. */
    private double price;

    /**
     * Creates a Booking with the given attributes.
     * 
     * @param transactionID   The transaction ID of the booking.
     * @param movieGoer       The moviegoer that made the booking.
     * @param tickets         The list of movie tickets booked.
     * @param transactionTime The time when the transaction was completed.
     * @param showing         The movie showing that the booking is for.
     * @param price           The price paid for the movie tickets in the booking.
     */
    public Booking(String transactionID, MovieGoer movieGoer, ArrayList<Ticket> tickets, LocalDateTime transactionTime,
            Showing showing, double price) {
        this.transactionID = transactionID;
        this.movieGoer = movieGoer;
        this.tickets = tickets;
        this.transactionTime = transactionTime;
        this.showing = showing;
        this.price = price;
    }

    /**
     * Returns a string containing the booking details.
     * The string includes the transaction ID, moviegoer's email, movie title, movie
     * ID, movie type, showtime, cineplex, cinema, cinema class and price.
     * 
     * @return String containing this booking's details.
     */
    @Override
    public String toString() {
        return "Transaction ID: " + transactionID
                + "\nEmail: " + movieGoer.getEmail()
                + "\nMovie Title: " + showing.getMovie().getTitle()
                + " (ID: " + showing.getMovie().getId() + ")"
                + " (" + showing.getMovie().getMovieType() + ")"
                + "\nShowtime: " + showing.getShowTime().format(InputHandler.getDateTimeFormat())
                + "\nCineplex: " + showing.getCineplex().getName()
                + " (Cinema: " + showing.getCinema().getCode() + ", " + showing.getCinema().getCinemaClass() + ")"
                + "\nPrice: " + price
                + "\n=================================================";
    }

    /**
     * Gets the transaction ID of this booking.
     * 
     * @return String containing this booking's transaction ID.
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Changes the transaction ID of this booking.
     * 
     * @param transactionID The input transaction ID.
     */
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * Gets the moviegoer that made this booking.
     * 
     * @return Moviegoer that made this booking.
     */
    public MovieGoer getMovieGoer() {
        return movieGoer;
    }

    /**
     * Changes the moviegoer assigned to this booking.
     * 
     * @param movieGoer The input moviegoer.
     */
    public void setMovieGoer(MovieGoer movieGoer) {
        this.movieGoer = movieGoer;
    }

    /**
     * Gets the list of movie tickets assigned to this booking.
     * 
     * @return List of tickets for this booking.
     */
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Changes the list of movie tickets assigned to this booking.
     * 
     * @param tickets The input list of movie tickets.
     */
    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    /**
     * Gets the transaction time when this booking was completed.
     * 
     * @return Transaction time when this booking was completed.
     */
    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    /**
     * Changes the transaction time of this booking.
     * 
     * @param transactionTime The input transation time.
     */
    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    /**
     * Gets the movie showing that this booking is for.
     * 
     * @return Showing assigned to this booking.
     */
    public Showing getShowing() {
        return showing;
    }

    /**
     * Changes the movie showing assigned to this booking.
     * 
     * @param showing The input showing.
     */
    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    /**
     * Gets the price paid for the movie tickets in this booking.
     * 
     * @return Price paid for the movie tickets in this booking.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the price paid for the movie tickets in this booking.
     * 
     * @param price The input price of the movie tickets.
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
