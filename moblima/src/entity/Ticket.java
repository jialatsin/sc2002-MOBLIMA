package entity;

import java.io.Serializable;

import entity.Enumerators.Age;
import boundary.InputHandler;
/**
* Represents a Ticket for a Showing
* Created when making a booking 
* @author OOP SSP1 Lab Group 4
* @version 07/11/2022
*/
public class Ticket implements Serializable {
    /**
     * showing is null 
     */
	private Showing showing = null;
	/**
	 * Seat assigned to the moviegoer
	 */
    private Seat seat;
    /**
     * Age of the moviegoer
     */
    private Age age;
    /**
     * 
     * @param showing The showing is initialized to null
     * @param seat    The seat assigned to the moviegoer
     * @param age     The age of the moviegoer
     */
    public Ticket(Showing showing, Seat seat, Age age) {
        this.showing = showing;
        this.seat = seat;
        this.age = age;
    }
    /**
     * String to display age, ticket and seat of the movie-goer
     * @return string to display age, ticket and seat of the movie-goer
     */
    @Override
    public String toString() {
        return age + " Ticket "
                + "\nSeat: " + (char) (seat.getColumn() + 'A') + seat.getRow()
                + "\n" + showing;
    }
    /**
     * Get the show time of the movie
     * @return showing The show time of the movie
     */
    public Showing getShowing() {
        return showing;
    }
    /**
     * The show time of the movie
     * @param showing The show time of the movie
     */
    public void setShowing(Showing showing) {
        this.showing = showing;
    }
    /**
     * Get the age of the movie-goer
     * @return age The age of the movie-goer
     */
    public Age getAge() {
        return age;
    }
    /**
     * The age of the movie-goer
     * @param age of the movie-goer
     */
    public void setAge(Age age) {
        this.age = age;
    }
    /**
     * Get the seat of the movie-goer
     * @return seat The seat of the movie-goer
     */
    public Seat getSeat() {
        return seat;
    }
    /**
     * The seat of the movie-goer
     * @param seat The seat of the movie-goer
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

}
