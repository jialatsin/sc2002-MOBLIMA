package entity;

import java.io.Serializable;

import entity.Enumerators.Age;

/* Represents a Ticket for a Showing */
/* Created when making a booking */
public class Ticket implements Serializable {
    private Showing showing = null;
    /* Seat assigned to the moviegoer */
    private Seat seat;
    /* Age of the moviegoer */
    private Age age;

    public Ticket(Showing showing, Seat seat, Age age) {
        this.showing = showing;
        this.seat = seat;
        this.age = age;
    }

    @Override
    public String toString() {
        return age + " Ticket "
                + "\nSeat: " + (char) (seat.getColumn() + 'A') + seat.getRow()
                + "\n" + showing;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

}
