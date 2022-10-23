package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Booking {
    private String transactionID;
    private MovieGoer customer;
    private ArrayList<Ticket> tickets;
    private LocalDateTime transactionTime;
    private Showing showing;
    private double price;

    public Booking(String transactionID, MovieGoer customer, ArrayList<Ticket> tickets, LocalDateTime transactionTime,
            Showing showing, double price) {
        this.transactionID = transactionID;
        this.customer = customer;
        this.tickets = tickets;
        this.transactionTime = transactionTime;
        this.showing = showing;
        this.price = price;
    }
}
