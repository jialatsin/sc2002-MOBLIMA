package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import boundary.InputHandler;

/* Represents a Booking made by a movieGoer for a movie Showing */
public class Booking implements Serializable {
    /**
     * Transaction ID in the format of XXXYYYYMMDDhhmm,
     * where XXX is the cinema code, YYYYDDMMhhmm is the datetime
     */
    private String transactionID;
    private MovieGoer movieGoer;
    private ArrayList<Ticket> tickets;
    private LocalDateTime transactionTime;
    private Showing showing;
    private double price;

    public Booking(String transactionID, MovieGoer movieGoer, ArrayList<Ticket> tickets, LocalDateTime transactionTime,
            Showing showing, double price) {
        this.transactionID = transactionID;
        this.movieGoer = movieGoer;
        this.tickets = tickets;
        this.transactionTime = transactionTime;
        this.showing = showing;
        this.price = price;
    }

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

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public MovieGoer getMovieGoer() {
        return movieGoer;
    }

    public void setMovieGoer(MovieGoer movieGoer) {
        this.movieGoer = movieGoer;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
