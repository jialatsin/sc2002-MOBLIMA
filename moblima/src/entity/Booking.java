package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Booking implements Serializable {
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
}
