package entity;

import java.io.Serializable;

public class Ticket implements Serializable {
    public enum Age {
        ADULT, SENIOR, CHILD
    };

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
