package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import boundary.InputHandler;

/**
 * It represents a Booking made by a movieGoer for a movie showing 
 * @author OOP SSP1 Lab Group 4
 * @version 30/10/2022
 */
public class Booking implements Serializable {
    /**
     * It displays the transactionID of the payment
     */
	private String transactionID;
	/**
	 * The movie-goer's name
	 */
    private MovieGoer movieGoer;
    /**
     * The tickets of the movie
     */
    private ArrayList<Ticket> tickets;
    /**
     * The time when the transaction was completed
     */
    private LocalDateTime transactionTime;
    /**
     * The time when the movie begins
     */
    private Showing showing;
    /**
     * It displays the price of the ticket booking
     */
    private double price;
    /**
     * Creates a Booking with these given attributes. 
     * Transaction ID in the format of XXXYYYYMMDDhhmm,
     * where XXX is the cinema code, YYYYDDMMhhmm is the datetime
     * @param transactionID    The transactionID of the payment
     * @param movieGoer        The movie-goer's name
     * @param tickets          The tickets of the movie
     * @param transactionTime  The time when the transaction was completed
     * @param showing          The time when the movie begins
     * @param price            The price of the ticket booking
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
    	 * String to return ticket details such as showtime, cineplex, price and transaction ID etc. 
    	 * @return String displaying ticket details such as showtime, cineplex, price and transaction ID etc. 
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
         * Get the transactionID of the payment 
         * @return transactionID The TransactionID of payment done
         */
        public String getTransactionID() {
            return transactionID;
        }
        /**
         * The TransactionID of payment done
         * @param transactionID The TransactionID of payment 
         */
        public void setTransactionID(String transactionID) {
            this.transactionID = transactionID;
        }
        /**
         * Get the name of movie-goer
         * @return movieGoers The name of the movie-goer
         */
        public MovieGoer getMovieGoer() {
            return movieGoer;
        }
        /**
         * The name of movie-goer
         * @param movieGoer The name of movie-goer
         */
        public void setMovieGoer(MovieGoer movieGoer) {
            this.movieGoer = movieGoer;
        }
        /**
         * Get the tickets of the movie
         * @return tickets The tickets of the movie
         */
        public ArrayList<Ticket> getTickets() {
            return tickets;
        }
        /**
         * The tickets of the movie
         * @param tickets The tickets of the movie
         */
        public void setTickets(ArrayList<Ticket> tickets) {
            this.tickets = tickets;
        }
        /**
         * Get the transaction time when the movie-goer bought the tickets
         * @return transactiontime The transaction time when the movie-goer bought the tickets
         */
        public LocalDateTime getTransactionTime() {
            return transactionTime;
        }
        /**
         * The time of transaction of the ticket done by the movie-goer
         * @param transactionTime The time when the transaction of the ticket was done by the movie-goer
         */
        public void setTransactionTime(LocalDateTime transactionTime) {
            this.transactionTime = transactionTime;
        }
        /**
         * Get the time when the movie begins
         * @return showing The showing time of the movie
         */
        public Showing getShowing() {
            return showing;
        }
        /**
         * The showing time of the movie
         * @param showing The showing time of the movie
         */
        public void setShowing(Showing showing) {
            this.showing = showing;
        }
        /**
         * Get the price of the movie tickets
         * @return price The price of the movie-tickets
         */
        public double getPrice() {
            return price;
        }
        /**
         * The price of the movie tickets
         * @param price The price of the movie tickets
         */
        public void setPrice(double price) {
            this.price = price;
        }
}

