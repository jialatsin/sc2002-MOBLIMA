package entity;

import java.io.Serializable;

import entity.Constants.Age;

public class Ticket implements Serializable {

    private Seat seat;
    private Age age;

    public Ticket(Seat seat, Age age) {
        this.seat = seat;
        this.age = age;
    }

    public Seat getSeat() {
        return seat;
    }

    public Age getAge() {
        return age;
    }
}
