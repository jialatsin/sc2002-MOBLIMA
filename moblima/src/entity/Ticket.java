package entity;

import java.io.Serializable;

import entity.Enumerators.Age;

/** Represents a Ticket for a showing. Created when making a booking */
public class Ticket implements Serializable {
    /** Showing assigned to this ticket. */
    private Showing showing = null;
    /** Seat assigned to the owner of this ticket. */
    private Seat seat;
    /** Age category of the owner of this ticket. */
    private Age age;

    /**
     * Creates a Ticket with the given attributes.
     * 
     * @param showing The showing assigned to the ticket.
     * @param seat    The seat assigned to the owner of the ticket.
     * @param age     The age category of the owner of the ticket.
     */
    public Ticket(Showing showing, Seat seat, Age age) {
        this.showing = showing;
        this.seat = seat;
        this.age = age;
    }

    /**
     * Returns a string containing the age category, assigned seat and showing for
     * this ticket.
     * 
     * @return String containing age, seat and showing for this ticket.
     */
    @Override
    public String toString() {
        return age + " Ticket "
                + "\nSeat: " + (char) (seat.getColumn() + 'A') + seat.getRow()
                + "\n" + showing;
    }

    /**
     * Gets the showing for this ticket.
     * 
     * @return Showing for this ticket.
     */
    public Showing getShowing() {
        return showing;
    }

    /**
     * Changes the showing for this ticket.
     * 
     * @param showing The input showing.
     */
    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    /**
     * Gets the age category of the owner of this ticket.
     * 
     * @return age category of the owner of this ticket.
     */
    public Age getAge() {
        return age;
    }

    /**
     * Changes the age category of the owner of this ticket.
     * 
     * @param age The input age category.
     */
    public void setAge(Age age) {
        this.age = age;
    }

    /**
     * Gets the seat assigned to this ticket.
     * 
     * @return Seat assigned to this ticket.
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * Changes the seat assigned to this ticket.
     * 
     * @param seat The input seat.
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

}
